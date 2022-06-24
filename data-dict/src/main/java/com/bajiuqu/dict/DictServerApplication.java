package com.bajiuqu.dict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 小艺小艺
 */
@MapperScan(value = {"com.bajiuqu.**.dao", "com.bajiuqu.**.repository"})
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class DictServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictServerApplication.class, args);
    }

}
