package com.u.mybatisassembly.service;


import com.u.mybatisassembly.modle.BaseModle;

import java.io.Serializable;

/**
 * @author 2u
 * @param <T>
 */
public interface BaseService<T extends BaseModle<T> ,PK extends Serializable>  {

    /**
     * 新增(不会将序列生成的ID,注入)
     * 效率较save(T t)高
     * @param t
     */
    void create(T t);
}
