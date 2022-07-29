package com.bajiuqu.myuser;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import com.bajiuqu.common.constant.NacosUrlConstant;
import com.bajiuqu.common.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class MyuserApplicationTest {

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Value("${spring.cloud.nacos.config.shared-dataids}")
    private String sharedDataids;

    private final static String GROUP = "DEFAULT_GROUP";

    private static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        // 默认缓存限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    private List<String> dataIdCommon() {
        List<String> dataidList = new ArrayList<>();
        if (sharedDataids.contains(",")) {
            String[] split = sharedDataids.split(",");
            dataidList = Arrays.stream(split).collect(Collectors.toList());
        }
        return dataidList;
    }

    @Autowired
    private RestHighLevelClient highLevelClient;

    /**
     * ElasticSearch 测试
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        // 创建request。
        Map<String, Object> jsonMap = new HashMap<>();
        // field_01、field_02为字段名，value_01、value_02为对应的值。
        jsonMap.put("{field_01}", "{value_01}");
        jsonMap.put("{field_02}", "{value_02}");
        //index_name为索引名称；type_name为类型名称,7.0及以上版本必须为_doc；doc_id为文档的id。
        IndexRequest indexRequest = new IndexRequest("{index_name}", "_doc", "{doc_id}").source(jsonMap);

        // 同步执行，并使用自定义RequestOptions（COMMON_OPTIONS）。
        IndexResponse indexResponse = highLevelClient.index(indexRequest, COMMON_OPTIONS);

        long version = indexResponse.getVersion();

        System.out.println("Index document successfully! " + version);
        //index_name为索引名称；type_name为类型名称,7.0及以上版本必须为_doc；doc_id为文档的id。与以上创建索引的名称和id相同。
        DeleteRequest request = new DeleteRequest("{index_name}", "_doc", "{doc_id}");
        DeleteResponse deleteResponse = highLevelClient.delete(request, COMMON_OPTIONS);

        System.out.println("Delete document successfully! \n" + deleteResponse.toString() + "\n" + deleteResponse.status());

        highLevelClient.close();
    }

    /**
     * 获取 Nacos 配置
     */
    @Test
    public void getConfig() {

        List<String> dataidList = dataIdCommon();

        String url = NacosUrlConstant.SCHEMA + serverAddr + NacosUrlConstant.GET_CONFIG + "?tenant=dev&dataId=" + dataidList.get(0) + "&group=" + GROUP;
        HttpResponse execute = HttpRequest.get(url).execute();

        String body = execute.body();
        log.warn("body: {}", body);
    }

    /**
     * 监听 Nacos 配置
     */
    @Test
    public void configListener() {

        List<String> dataidList = dataIdCommon();

        StringBuffer responseBody = new StringBuffer();
        for (String dataId : dataidList) {

            String requestBody = "";
            if (dataId.contains("myuser")) {

                requestBody = "Listening-Configs=" + dataId + "^2" + GROUP + "^2" + "42df3ca369999bdb803e02229465384d^2" + active + "^1";
            } else {

                requestBody = "Listening-Configs=" + dataId + "^2" + GROUP + active + "^1";
            }

            String url = NacosUrlConstant.SCHEMA + serverAddr + NacosUrlConstant.LISTENER_CONFIG;
            HttpResponse httpResponse = HttpRequest.post(url).header("Long-Pulling-Timeout", "30000").body(requestBody).execute();

            JSONObject httpResponseJson = new JSONObject();
            httpResponseJson.put("status", httpResponse.getStatus());
            httpResponseJson.put("body", httpResponse.body());
            responseBody.append(httpResponseJson.toJSONString());
        }

        log.warn("responseBody: {}", responseBody);
    }

    /**
     * 发布配置
     */
    @Test
    public void releaseConfig() {

        List<String> dataidList = dataIdCommon();

        StringBuffer responseBody = new StringBuffer();
        for (String dataId : dataidList) {

            String url =  NacosUrlConstant.SCHEMA + serverAddr + NacosUrlConstant.RELEASE_CONFIG;
            String content = "spring:\n" +
                    "  redis:\n" +
                    "    port: 6379\n" +
                    "    host: 39.105.132.152\n" +
                    "    password: KKLw@6h6aHF\n" +
                    "    timeout: 50000\n" +
                    "    database: 3\n" +
                    "    lettuce:\n" +
                    "      pool:\n" +
                    "        max-wait: 2000\n" +
                    "        max-active: 100\n" +
                    "        max-idle: 50\n" +
                    "        min-idle: 10\n" +
                    "  jedis: timeout: 50000";
            String requestBody = "tenant=" + active + "&dataId=" + dataId + "&group=" + GROUP + "&content=" + content + "&type=yaml";
            HttpResponse httpResponse = HttpRequest.post(url).body(requestBody).execute();

            JSONObject httpResponseJson = new JSONObject();
            httpResponseJson.put("status", httpResponse.getStatus());
            httpResponseJson.put("body", httpResponse.body());
            responseBody.append(httpResponseJson.toJSONString());
        }

        log.warn("responseBody: {}", responseBody);
    }



}
