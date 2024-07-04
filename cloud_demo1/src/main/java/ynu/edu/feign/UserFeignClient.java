package ynu.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ynu.edu.entity.CommonResult;

//指定调用的服务名，在yml文件中:spring.application.name: provider-server
@FeignClient(name="provide-serve")
public interface UserFeignClient {
    //配置需要调用的挂号服务接口。与UserController中的方法定义一致
    @GetMapping("/user/getUserById/{userId}")
    public CommonResult getUserById(@PathVariable("userId") Integer userId);
}
