package ynu.edu.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ynu.edu.entity.CommonResult;
import ynu.edu.entity.User;
import ynu.edu.feign.UserFeignClient;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    //注入Fegin接口（@EnableFeignClients自动扫描@FeignClient注解）
    @Resource
    private UserFeignClient userFeignClient;
    @GetMapping("/getUserById/{userId}")
//    @CircuitBreaker(name = "backendA",fallbackMethod = "getUserByIdDown")
//    @Retry(name = "retry2",fallbackMethod = "getUserByIdDown")
    @RateLimiter(name = "rate1")
    public CommonResult getUserById(@PathVariable("userId") Integer userId){ //使用Fegin接口进行服务调用
//        return userFeignClient.getUserById(userId);
        try {
            return userFeignClient.getUserById(userId);
        } catch (RequestNotPermitted ex) {
            String message = "当前程序异常火爆，请稍后再试";
            System.out.println(message);
            return new CommonResult(429, message, null); // 使用 HTTP 状态码 429 Too Many Requests
        }
    }


    public CommonResult getUserByIdDown(Integer userId,Throwable throwable){
        throwable.printStackTrace();
        String message = "获取用户"+userId+"当前功能异常火爆";
        System.out.println(message);
        return new CommonResult(400,message,new User());
    }
    public CommonResult getUserByIdDown(Integer userId, SQLDataException throwable){
        throwable.printStackTrace();
        String message = "请联系管理员，当前数据处理异常";
        System.out.println(message);
        return new CommonResult(400,message,new User());
    }
}
