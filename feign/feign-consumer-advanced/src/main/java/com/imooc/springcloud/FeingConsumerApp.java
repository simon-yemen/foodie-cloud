package com.imooc.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by 半仙.
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FeingConsumerApp {

    /*
        推荐使用此种方法解决由于包不同不能扫描实例的问题
        坑：
        1、这里如果包名相同的话，会注册两个IService类型的实例
        f版本通过加primary = true解决，表示MyIservice优先级高
        2、G版本无法通过上述办法解决，启动会报错，需要在配置文件中配置可以实例重载
     */
//    @FeignClient(value = "xxx",primary = true)
//    public interface  MyIservice extends IService{
//
//    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(FeingConsumerApp.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
