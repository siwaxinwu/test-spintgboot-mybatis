package com.roy;

import com.roy.bean.Employee;
import com.roy.mapper.EmployeeMapper;
import com.roy.mapper.MemeberMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * description：
 * 对于单表的操作demo
 * author：dingyawu
 * date：created in 21:36 2020/11/27
 * history:
 */
@SpringBootTest
class EmployeemapperTest {

	@Autowired
	EmployeeMapper mapper;
	@Test
	public void testAdd() {
		Employee emp = new Employee(null, "tom", "jack@sina.com", "0");
		Integer result = mapper.addEmp(emp);
		System.out.println("插入对象：" + emp + "结果：" + result);
	}

	@Test
	public void testGetEmpById() {
		Employee emp = mapper.getEmpById(2);
		System.out.println("查询方法：getEmpById 结果：" + emp);
	}

	@Test
	public void testUpdateEmp() {
		Employee emp = new Employee(1, "jerry", "jerry@sina.com", "1");
		Integer result = mapper.updateEmp(emp);
		System.out.println("方法updateEmp更新对象：" + emp + "结果：" + result);
	}

	@Test
	public void testDeleteEmp() {
		Integer result = mapper.deleteEmp(1);
	}

	@Test
	public void testGetEmpByIdAndlastName() {
		Employee emp = mapper.getEmpByIdAndlastName(3, "tom");
		System.out.println(emp);
	}

	@Test
	public void testGetEmpByIdByMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", 4);
		map.put("lastName", "jerry");
		Employee emp = mapper.getEmpByIdByMap(map);
		System.out.println(emp);
	}

	@Test
	public void testGetEmpsByLastNameLike() {
		List<Map<String, Object>> result = mapper.getEmpsByLastNameLike("jerry");
		result.stream().forEach(System.out:: println);
	}

	@Test
	public void testGetEmpByIdReturnMap() {
		Map<String, Object> result = mapper.getEmpByIdReturnMap(2);
		System.out.println(result);
	}


	@Test
	public void testGetEmpsByLastNameLikeReturnMap() {
		Map<Integer, Employee> result = mapper.getEmpsByLastNameLikeReturnMap("jerry");
		System.out.println(result);
	}
}
