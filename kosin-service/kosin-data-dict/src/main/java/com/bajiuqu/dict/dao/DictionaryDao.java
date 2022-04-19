package com.bajiuqu.dict.dao;

import com.bajiuqu.dict.entity.DictionarysDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author 小艺小艺
 */
@Repository
@Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
public interface DictionaryDao extends Mapper<DictionarysDO> {

}
