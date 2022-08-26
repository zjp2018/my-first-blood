package com.mybatisplus.MetaObjectHandler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler extends MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("name", metaObject);
        if (fieldValue == null) {
            System.out.println("*******插入操作 满足填充条件*********");
            setFieldValByName("last_name", "weiyunhui", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("name", metaObject);
        if (fieldValue == null) {
            System.out.println("*******修改操作 满足填充条件*********");
            setFieldValByName("last_name", "weiyh", metaObject);
        }
    }
}
