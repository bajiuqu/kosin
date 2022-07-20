package com.bajiuqu.common.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * @author 王瑞敏
 * @date 2022-07-20
 * @describe 数据拷贝工具类
 */
public class XyBeanUtils {

    private static Logger logger = LoggerFactory.getLogger(XyBeanUtils.class);

    /**
     * @param source：源数据
     * @param clazz：目标数据
     * @author 李衡
     * @data 20180723
     * @describe 数据拷贝
     */
    public synchronized static <S, T> T copyProperties(S source, Class<T> clazz) {
        Assert.notNull(clazz, "clazz must not be null");
        if (source == null) {
            logger.debug("copyProperties-source-null->" + clazz);
            return null;
        }
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
            return null;
        }
    }

    /**
     * @param source：源数据集合
     * @param target：目录数据集合
     * @param clazz：目标数据类型
     * @author 李衡
     * @data 20180723
     * @describe 集合内数据拷贝
     */
    public synchronized static <S, T> void copyCollection(Collection<S> source, Collection<T> target, Class<T> clazz) {
        Assert.notNull(source, "source must not be null");
        Assert.notNull(target, "target must not be null");
        try {
            for (Iterator<S> iterator = source.iterator(); iterator.hasNext(); ) {
                S s = (S) iterator.next();
                T t = clazz.newInstance();
                BeanUtils.copyProperties(s, t);
                target.add(t);
            }
        } catch (Exception e) {
            logger.info("CopyCollectionException -> " + e);
        }
    }

    /**
     * 数据拷贝 忽略数据源(source)中值为null的属性
     *
     * @param source
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if (source == null) {
            logger.debug("copyProperties-source-null");
        }
        try {
            BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
        }
    }

    /**
     * 获取属性为null的属性名
     *
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将源对象中非空属性的值替换到属性名相同的目标对象。
     *
     * @param source
     * @param target
     */
    public synchronized static void copyNoNullProperties(Object source, Object target) {
        Assert.notNull(target, "clazz must not be null");
        try {
            HashMap<String, Object> hashMap = getNoNullProperties(source);
            BeanWrapper srcBean = new BeanWrapperImpl(target);
            srcBean.setPropertyValues(hashMap);
        } catch (Exception e) {
            logger.info("CopyPropertiesException -> " + e);
        }
    }

    /**
     * @param source 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    private static HashMap<String, Object> getNoNullProperties(Object source) {
        BeanWrapper srcBean = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        HashMap<String, Object> hashMap = new HashMap<>();
        for (PropertyDescriptor p : pds) {
            Object value = srcBean.getPropertyValue(p.getName());
            if (value != null) {
                hashMap.put(p.getName(), value);
            }
        }
        return hashMap;
    }

    /**
     * Copy page t.
     *
     * @param <S>    the type parameter 源数据类型
     * @param <T>    the type parameter DTO实体类
     * @param source the source
     * @param clazz  the clazz
     * @return the t
     */
    public synchronized static <S, T> Page<T> copyPage(Page<S> source, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        if (ObjectUtils.equals(source, null) || source.getContent().size() <= 0) {
            return new PageImpl<T>(list, source.getPageable(), source.getTotalPages());
        }
        List<S> content = source.getContent();

        for (S s : content) {
            T t = XyBeanUtils.copyProperties(s, clazz);
            list.add(t);
        }
        return new PageImpl<T>(list, source.getPageable(), source.getTotalElements());
    }

}
