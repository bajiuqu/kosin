package com.bajiuqu.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosApplication {

    public static void main(String[] args) {
        log.info("nacos-server 开始启动");
        SpringApplication.run(NacosApplication.class, args);
        log.info("nacos-server 启动完成");
    }

}
