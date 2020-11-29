package com.roy.mapper;

import com.roy.bean.Dept;
import com.roy.bean.DeptPlus;
import com.roy.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description：
 * author：dingyawu
 * date：created in 16:00 2020/8/29
 * history:
 */
public interface DepartmentMapper {
    Dept getDeptById(Integer id);

    DeptPlus getDeptByIdPlus(Integer id);

    DeptPlus getDeptByIdStep(Integer id);

    List<Employee> getempsByDeptId(@Param("deptId") Integer id);

    List<Employee> getEmpsTestInnerParameter(Employee employee);
}
