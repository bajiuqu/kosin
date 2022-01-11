package com.bajiuqu.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 注册中心服务
 *
 * @author 小艺小艺
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosApplication {

    public static void main(String[] args) {

        SpringApplication.run(NacosApplication.class, args);
    }

}
