package com.imooc.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient会生成一个代理类，真正法器远程调用的是这个代理类
 * eureka-client 为远程调用的目标serviceId
 */
@FeignClient("eureka-client")
public interface IService {

    @GetMapping("/sayHi")
    String sayHi();

}
