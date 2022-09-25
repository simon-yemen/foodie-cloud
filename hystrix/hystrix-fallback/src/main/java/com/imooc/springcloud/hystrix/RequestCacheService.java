package com.imooc.springcloud.hystrix;

import com.imooc.springcloud.Friend;
import com.imooc.springcloud.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 半仙.
 */
@Slf4j
@Service
public class RequestCacheService {

    // 这里注入Myservice，注入IService的话由于存在有两个IService的类型会报错
    @Autowired
    private MyService service;


    @CacheResult // 开启缓存
    @HystrixCommand(commandKey = "cacheKey") // 配置方法的commandKey，可以替代Feign.configKey()方法获取的key，更加的方便
    public Friend requestCache(@CacheKey String name) {
        log.info("request cache " + name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = service.sayHiPost(friend);
        log.info("after requesting cache " + name);
        return friend;
    }

}
