package com.bajiuqu.manage.config;

import com.bajiuqu.manage.system.user.vo.UserVO;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

/**
 * @author 小艺小艺
 * @date 2022-02-09 16:54:45
 * @Description 配置文件属性值
 */
@Order
@Data
@Component(value = "PropertiesConfig")
@ConfigurationProperties(prefix = "manager")
@Validated
@ToString
public class PropertiesConfig {

    private String domainName;

    private String filePath;

    @Email
    private String email;

}
