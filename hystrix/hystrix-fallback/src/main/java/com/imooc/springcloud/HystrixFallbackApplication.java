package com.imooc.springcloud;

import feign.Feign;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by 半仙.
 */
@EnableDiscoveryClient
@SpringBootApplication
// 要远程调用
@EnableFeignClients
// 开启断路器
@EnableCircuitBreaker
public class HystrixFallbackApplication {

    public static void main(String[] args) throws NoSuchMethodException {
        new SpringApplicationBuilder(HystrixFallbackApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);

        // 获取方法签名，用于在配置文件中配置方法的超时时间
        // hystrix.command.MyService#retry(int).execution.isolation.thread.timeoutInMilliseconds=3000
//        System.out.println(Feign.configKey(MyService.class,
//                MyService.class.getMethod("retry", int.class)));
    }

}
