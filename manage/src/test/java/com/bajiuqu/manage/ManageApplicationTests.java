package com.bajiuqu.manage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ManageApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    // 专门用来操作字符串的对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        try {
            ArrayList<Object> list = new ArrayList<>();
            int capacity = getCapacity(list);
            System.out.println("这个ArrayList的容量为：" + capacity + " size：" + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCapacity(ArrayList arrayList) {
        try {
            for (Field item : ArrayList.class.getDeclaredFields()) {
                /**
                 * item: serialVersionUID
                 * item: DEFAULT_CAPACITY
                 * item: EMPTY_ELEMENTDATA
                 * item: DEFAULTCAPACITY_EMPTY_ELEMENTDATA
                 * item: elementData
                 * item: size
                 * item: MAX_ARRAY_SIZE
                 */
                System.out.println("item: " + item.getName());
            }

            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            return ((Object[]) elementDataField.get(arrayList)).length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Test
    void redisTest() {

        ValueOperations<String, String> opsString = stringRedisTemplate.opsForValue();

        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();

        valueOperations.set(6, "6");

        RedisOperations operations = valueOperations.getOperations();


    }

}
