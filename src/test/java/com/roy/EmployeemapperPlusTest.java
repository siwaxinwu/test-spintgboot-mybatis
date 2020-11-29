package com.roy;

import com.roy.bean.Dept;
import com.roy.bean.DeptPlus;
import com.roy.bean.EmpPlusDept;
import com.roy.bean.Employee;
import com.roy.mapper.DepartmentMapper;
import com.roy.mapper.EmployeeMapper;
import com.roy.mapper.EmployeeDynamicSQLMapper;
import com.roy.mapper.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * description：
 * 对于单表的操作demo
 * author：dingyawu
 * date：created in 21:36 2020/11/27
 * history:
 */
@SpringBootTest
class EmployeemapperPlusTest {

	@Autowired
	EmployeeMapper mapper;

	@Autowired
	EmployeePlusMapper employeePlusMapper;

	@Autowired
	DepartmentMapper departmentMapper;

	@Autowired
	EmployeeDynamicSQLMapper employeeDynamicSQLMapper;

	/**
	 * 开发的注意事项
	 * @throws IOException
	 */
	@Test
	public void testGetEmpById() throws IOException {
		Employee emp = employeePlusMapper.getEmpById(22);
		/*dao层当你查不到数据的时候，给你返回null，所以在代码层面要做判断*/
		/*System.out.println(emp.getId());*/
		System.out.println(emp);
	}

	@Test
	public void testGetEmpByIdStep() throws IOException {
		EmpPlusDept result = employeePlusMapper.getEmpByIdStep(1);
		System.out.println(result);
	}



	@Test
	public void testGetDeptByIdPlus() throws IOException {
		DeptPlus deptByIdPlus = departmentMapper.getDeptByIdPlus(1);
		System.out.println(deptByIdPlus);
	}


	@Test
	public void testGetDeptByIdStep() throws IOException {
		DeptPlus deptByIdStep = departmentMapper.getDeptByIdStep(1);
		System.out.println(deptByIdStep.getId());
	}


	@Test
	public void testGetEmpByConditionIf() throws IOException {
		Employee employee = new Employee();
		//employee.setId(1);
		employee.setLastName("%e%");
		employee.setEmail("jerry@sina.com");
		employee.setGender("1");
		List<Employee> result = employeeDynamicSQLMapper.getEmpByConditionIf(employee);
		System.out.println(result);
	}

	@Test
	public void testGetEmpsByConditionTrim() throws IOException {
		Employee employee = new Employee();
		//employee.setId(1);
		employee.setLastName("%e%");
		employee.setEmail("jerry@sina.com");
		employee.setGender("1");
		List<Employee> result = employeeDynamicSQLMapper.getEmpsByConditionTrim(employee);
		System.out.println(result);
	}

	@Test
	public void testGetEmpsByConditionChoose() throws IOException {
		Employee employee = new Employee();
		//employee.setId(1);
		employee.setLastName("%e%");
		employee.setEmail("jerry@sina.com");
		employee.setGender("1");
		List<Employee> result = employeeDynamicSQLMapper.getEmpsByConditionChoose(employee);
		System.out.println(result);
	}

	@Test
	public void testUpdateEmp() throws IOException {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setLastName("roy");
		employee.setEmail("jerry@sina.com");
		employee.setGender("1");
		employeeDynamicSQLMapper.updateEmp(employee);
	}


	@Test
	public void testGetEmpsByConditionForeach() throws IOException {
		List<Employee> result = employeeDynamicSQLMapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
		System.out.println(result);
	}


	@Test
	public void testAddEmps() throws IOException {
		ArrayList<EmpPlusDept> list = new ArrayList<>();
		list.add(new EmpPlusDept(null, "roy113","roy.sina.com", "1", new Dept(1,"人事部")));
		list.add(new EmpPlusDept(null, "roy114","roy11.sina.com", "0", new Dept(2,"组织部")));
		employeeDynamicSQLMapper.addEmps(list);
	}

}
