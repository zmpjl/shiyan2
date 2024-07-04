package ynu.edu.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomThreeTimeLoadBalancerConfig {

    @Bean
    ReactorLoadBalancer<ServiceInstance> threeTimeLoadBalancer(Environment envir, LoadBalancerClientFactory lbf) {
        String name = envir.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new ThreeTimeLoadBalancer(lbf.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
