package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ynu.edu.rule.CustomThreeTimeLoadBalancerConfig;
import ynu.edu.rule.CustomerLoadBalanceConfig;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name = "provide-serve",configuration = CustomThreeTimeLoadBalancerConfig.class)
public class ConsumerApplication {

    /**
     * 注解
     * @return
     */
/*    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
