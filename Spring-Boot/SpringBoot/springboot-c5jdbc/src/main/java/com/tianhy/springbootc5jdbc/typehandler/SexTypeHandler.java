package com.tianhy.springbootc5jdbc.typehandler;

import com.tianhy.springbootc5jdbc.enumeration.SexEnum;
import org.apache.ibatis.type.*;
//import org.apache.ibatis.type.*;

import java.sql.*;

/**
 * {@link}
 *
 * @Desc: Mybatis类型转换
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@MappedJdbcTypes(JdbcType.INTEGER) //声明JdbcType为Integer类型
@MappedTypes(value = SexEnum.class) //声明JavaType为枚举类型
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getId());
    }


    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        //通过列名
        int anInt = rs.getInt(columnName);
        if (anInt != 1 && anInt != 2) {
            return null;
        }
        return SexEnum.getSexEnumById(anInt);
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        //通过下标
        int anInt = rs.getInt(columnIndex);
        if (anInt != 1 && anInt != 2) {
            return null;
        }
        return SexEnum.getSexEnumById(anInt);
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        //通过存储过程
        int anInt = cs.getInt(columnIndex);
        if (anInt != 1 && anInt != 2) {
            return null;
        }
        return SexEnum.getSexEnumById(anInt);
    }
}
