package com.tianhy.springbootc5jdbc.convert;

import com.tianhy.springbootc5jdbc.enumeration.SexEnum;

import javax.persistence.AttributeConverter;

/**
 * {@link}
 *
 * @Desc: JPA 性别转换器
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
public class SexConvert implements AttributeConverter<SexEnum,Integer> {

    //枚举转为数据库列
    @Override
    public Integer convertToDatabaseColumn(SexEnum sexEnum) {
        return sexEnum.getId();
    }

    //数据库列转换为枚举
    @Override
    public SexEnum convertToEntityAttribute(Integer integer) {
        return SexEnum.getSexEnumById(integer);
    }
}
