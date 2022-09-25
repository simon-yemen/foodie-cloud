package com.imooc.springcloud;

import com.imooc.springcloud.hystrix.RequestCacheService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 半仙.
 */
@RestController
public class Controller {

    @Autowired
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback() {
        return myService.error();
    }

    @GetMapping("/timeout")
    public String timeout(Integer timeout) {
        return myService.retry(timeout);
    }


    /**
     * 注解方式配置降级逻辑
     * @param timeout
     * @return
     */
    @GetMapping("/timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback", // 配置降级方法
            commandProperties = {
                    // 配置触发降级的条件：超时或者异常
               @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="3000")
            }
    )
    public String timeout2(Integer timeout) {
        // @FeignClient注解了Myservice类并且配置了"一揽子"降级逻辑，
        // 如果配置文件中配置的全局的超时时间小于上述的时间的还会优先进入Myservice配置的降级逻辑中
        return myService.retry(timeout);
    }

    public String timeoutFallback(Integer timeout) {
        return "success";
    }

    @GetMapping("/cache")
    public Friend cache(String name) {
        /*
            初始化一个hystrix上下文，@Cleanup时lombok提供的，会编译成try-cache-finally结构
            最终会在finally中关闭这个上下文
         */
        @Cleanup HystrixRequestContext context =
                HystrixRequestContext.initializeContext();

        Friend friend = requestCacheService.requestCache(name);
        friend = requestCacheService.requestCache(name);
        return friend;
    }

}
