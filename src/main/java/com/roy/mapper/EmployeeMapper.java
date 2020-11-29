package com.roy.mapper;

import com.roy.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

	Employee getEmpById(Integer id);

    Integer addEmp(Employee employee);

    Integer updateEmp(Employee employee);

    Integer deleteEmp(Integer id);

    Employee getEmpByIdAndlastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpByIdByMap(Map<String, Object> map);

    List<Map<String, Object>> getEmpsByLastNameLike(String lastName);

	/**
	 * 返回一条记录的map：key就是列名，值就是对应的值
	 * @param id
	 * @return
	 */
	Map<String, Object> getEmpByIdReturnMap(Integer id);

	/**
	 * 多条记录封装成map，key：这条记录的主键，value：记录封装后的javaBean
	 * @mapKey:告诉mybatis封装这个map的时候使用哪个属性作为key ,值是javaBean的属性
	 * @param lastName
	 * @return
	 */
	@MapKey("id")
	//@MapKey("lastName")
	Map<Integer, Employee> getEmpsByLastNameLikeReturnMap(String lastName);
}
