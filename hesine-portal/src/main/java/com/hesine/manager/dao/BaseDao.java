package com.hesine.manager.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 15/1/8
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao<T> {
    /**
     * 根据id获得对象
     * @param id
     * @return
     */
    public T get(@Param("id") Long id);

    /**
     * 列表
     * @param parameters
     * @return
     */
    public List<T> list(@Param("parameters") Map<String, Object> parameters);

    /**
     * 保存
     * @param t
     */
    public long save(@Param("t") T t);

    /**
     * 更新
     * @param t
     * @return
     */
    public int update(@Param("t") T t);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Long id);

}
