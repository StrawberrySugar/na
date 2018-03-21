package com.zizaihome.api.db.model;

import java.io.Serializable;
import java.sql.ResultSet;


public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认构造方法
     */
    public BaseModel() {

    }

    public abstract BaseModel getModelByRs(ResultSet rs);

    /**
     * 获取额外的一些属性
     * 
     * @param rs
     * @return
     */
    public BaseModel getModelExtByRs(ResultSet rs) {
        return this;
    }
}
