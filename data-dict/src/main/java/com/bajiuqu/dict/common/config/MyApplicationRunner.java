package com.bajiuqu.dict.common.entity.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 小艺小艺
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private DictionaryInit dictionaryInit;

    @Override
    public void run(ApplicationArguments args) {
        log.warn("==========> 数据字典服务启动");
        String startDateTime = DateUtil.formatDateTime(new Date());
        log.warn("==========> 数据字典存放 Redis 开始: " + startDateTime);

        // 初始化数据字典
        dictionaryInit.init();

        String endDateTime = DateUtil.formatDateTime(new Date());
        log.warn("==========> 数据字典存放 Redis 结束: " + endDateTime);

    }

}
