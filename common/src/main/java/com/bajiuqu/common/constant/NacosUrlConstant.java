package com.bajiuqu.common.constant;

/**
 * Nacos 配置中心，OpenAPI
 *
 * @author ruimi
 * @date 20220726 0924
 */
public class NacosUrlConstant {

    /**
     * 使用协议
     */
    public static final String SCHEMA = "http://";

    /**
     * 使用协议
     */
    public static final String ENCRYPTION_PROTOCOL = "https://";

    /**
     * 获取配置信息
     * <p>
     *
     * @method GET
     * @parameter tenant 命名空间ID
     * @parameter dataId data-id
     * @parameter group 分组
     */
    public static final String GET_CONFIG = "/nacos/v1/cs/configs";

    /**
     * 监听配置
     * <p>
     *
     * @method POST
     * @parameter Listening-Configs=dataId^2Group^2contentMD5^2tenant^1 或者 Listening-Configs=dataId^2Group^2contentMD5^1
     */
    public static final String LISTENER_CONFIG = "/nacos/v1/cs/configs/listener";

    /**
     * 发布配置
     */
    public static final String RELEASE_CONFIG = "/nacos/v1/cs/configs";

}
