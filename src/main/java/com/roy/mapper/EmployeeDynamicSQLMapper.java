package com.roy.mapper;

import com.roy.bean.EmpPlusDept;
import com.roy.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description：
 * author：dingyawu
 * date：created in 19:12 2020/9/1
 * history:
 */
public interface EmployeeDynamicSQLMapper {

    List<Employee> getEmpByConditionIf(Employee emp);

    List<Employee> getEmpsByConditionTrim(Employee emp);

    List<Employee> getEmpsByConditionChoose(Employee emp);

    void updateEmp(Employee emp);


    /*
    * 如果这个地方我不标注@Param("ids"),那么后边collection的属性选择就是list
    *  如果是数组的话就传collection="array"
    * */
    List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

     void addEmps(@Param("emps") List<EmpPlusDept> emps);


}
