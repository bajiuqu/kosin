package com.bajiuqu.myuser.common.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    /**
     * 协议
     */
    private String schema;
    /**
     * 主机IP
     */
    private String address;
    /**
     * 连接超时时间
     */
    private Integer connecttimeout;
    /**
     * socket 连接超时时间
     */
    private Integer sockettimeout;
    /**
     * 连接请求超时时间
     */
    private Integer connectionrequesttimeout;
    /**
     * 最大连接数
     */
    private Integer maxconnecttotal;
    /**
     * 每条路线的最大连接数
     */
    private Integer maxconnectperroute;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        log.warn("连接 ElasticSearch 主机: {}", address);
//
//        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
//        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
//
//        RestClientBuilder builder = RestClient.builder(init(address));
//        // 异步连接延时配置
//        builder.setRequestConfigCallback(requestConfigCallback -> {
//            requestConfigCallback.setConnectTimeout(connecttimeout);
//            requestConfigCallback.setSocketTimeout(sockettimeout);
//            requestConfigCallback.setConnectionRequestTimeout(connectionrequesttimeout);
//            return requestConfigCallback;
//        });
//        // 异步连接数配置
//        builder.setHttpClientConfigCallback(httpClientConfigCallback -> {
//            httpClientConfigCallback.setMaxConnTotal(maxconnecttotal);
//            httpClientConfigCallback.setMaxConnPerRoute(maxconnectperroute);
//            return httpClientConfigCallback;
//        });
//        return new RestHighLevelClient(builder);
//    }

    private HttpHost[] init(String list) {
        List<HttpHost> result = new ArrayList<>();
        if (!list.contains(",")) {
            String[] hostPort = list.split(":");
            HttpHost httpHost = new HttpHost(hostPort[0], Integer.parseInt(hostPort[1]), schema);
            result.add(httpHost);
        } else {
            String[] hosts = list.split(",");
            for (String host : hosts) {
                String[] hostPort = host.split(":");
                HttpHost httpHost = new HttpHost(hostPort[0], Integer.parseInt(hostPort[1]), schema);
                result.add(httpHost);
            }
        }

        HttpHost[] arr = new HttpHost[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        log.warn("连接 ElasticSearch 主机: {}", address);
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(address)
                .withBasicAuth(username, password)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
