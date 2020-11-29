package com.roy.handler;

import com.alibaba.druid.util.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description：
 *
 * author：dingyawu
 * date：created in 21:54 2020/11/28
 * history:
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(String.class)
public class GenderTypeHandler extends BaseTypeHandler {

    //设置参数，这里将Java的String类型转换为JDBC的Integer类型
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, StringUtils.equals(parameter.toString(),"男")? 1 : 2);
    }

    //以下三个参数都是将查询的结果转换
    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName)==1?"男":"女";
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex)==1?"男":"女";
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex)==1?"男":"女";
    }
}
