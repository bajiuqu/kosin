package com.bajiuqu.dict.common.entity.config;

import com.alibaba.fastjson2.JSON;
import com.bajiuqu.dict.common.constant.RedisConstant;
import com.bajiuqu.dict.common.entity.DictionarysDO;
import com.bajiuqu.dict.dao.DictionaryDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DictionaryInit {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DictionaryDao dictRepository;

    public void init() {

        Example example = new Example(DictionarysDO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("groupId", "PM001");

        List<DictionarysDO> dictDOS = dictRepository.selectByExample(example);

        if (!CollectionUtils.isEmpty(dictDOS)) {

            Map<String, List<DictionarysDO>> group = dictDOS.stream().collect(Collectors.groupingBy(DictionarysDO::getGroupId));

            for (Map.Entry<String, List<DictionarysDO>> entry : group.entrySet()) {

                String groupId = entry.getKey();

                List<DictionarysDO> dictList = entry.getValue();

                Object value = redisTemplate.opsForValue().get(RedisConstant.GROUP_REDIS_KEY_PRE + groupId);

                if (value != null) {

                    redisTemplate.delete(RedisConstant.GROUP_REDIS_KEY_PRE + groupId);
                }

                Map<String, String> entity = new HashMap<>();

                for (DictionarysDO item : dictList) {

                    entity.put(item.getDictCode(), item.getDictName());
                }

                redisTemplate.opsForValue().set(RedisConstant.GROUP_REDIS_KEY_PRE + groupId, JSON.toJSONString(entity));
            }
        }
    }

}
