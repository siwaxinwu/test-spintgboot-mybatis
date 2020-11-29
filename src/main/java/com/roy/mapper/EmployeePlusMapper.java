package com.roy.mapper;

import com.roy.bean.EmpPlusDept;
import com.roy.bean.Employee;


/**
 * description：
 * author：dingyawu
 * date：created in 14:11 2020/8/29
 * history:
 */
public interface EmployeePlusMapper {

    Employee getEmpById(Integer id);

    EmpPlusDept getEmpAndDept(Integer id);

    /**
     * 分布查询、懒加载
     * @param id
     * @return
     */
    EmpPlusDept getEmpByIdStep(Integer id);
}
