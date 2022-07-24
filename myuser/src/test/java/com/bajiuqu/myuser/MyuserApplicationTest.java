package com.bajiuqu.myuser;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyuserApplicationTest {

    private static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();

        // 默认缓存限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(
                new HttpAsyncResponseConsumerFactory
                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    @Autowired
    private RestHighLevelClient highLevelClient;

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

}
