package com.roy.mapper;

import com.roy.bean.EmpPlusGender;
import com.roy.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * description：
 * 主要用来测试Typehandler的实现类，从jdbc到java类型的映射
 *
 * author：dingyawu
 * date：created in 21:46 2020/11/28
 * history:
 */

public interface EmpPlusGenderMapper {

    EmpPlusGender getEmpById1(Integer id);

    Integer insertEmp(EmpPlusGender empPlusGender);



}