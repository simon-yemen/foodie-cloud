package com.imooc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 半仙.
 */
@RestController
@Slf4j
public class Controller {

    /**
     * 这里的IService可以直接Autowired的属于巧合，
     * 因为调用方和被调用方的包名相同，可以被包扫描到
     * @EnableFeignClients默认扫描主类下的feign客户端类
     *
     *
     * 当调用方和被调用方包名不同时：
     * 1、@EnableFeignClients中指定被调用方的包
     * 2、添加自定义的feignclient继承被调用的feignclient，并添加注解，如主类中注释
     */
    @Autowired
    private IService service;

    @GetMapping("/sayHi")
    public String sayHi() {
        return service.sayHi();
    }

//    @PostMapping("/sayHi")
//    public Friend sayHi2() {
//        Friend friend = new Friend();
//        friend.setName("test");
//        return service.sayHiPost(friend);
//    }
//
//    @GetMapping("/retry")
//    public String retry(Integer timeout) {
//        return service.retry(timeout);
//    }

}
