package com.roy;

import com.roy.bean.*;
import com.roy.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description：
 * 对于单表的操作demo
 * author：dingyawu
 * date：created in 21:36 2020/11/27
 * history:
 */
@SpringBootTest
class EmpPlusGenderTest {

	@Autowired
	EmpPlusGenderMapper empPlusGenderMapper;



	@Test
	public void testGetEmpById1() throws IOException {
		System.out.println(empPlusGenderMapper.getEmpById1(2));
	}

	@Test
	public void testInsertEmp() throws IOException {
		EmpPlusGender emp = new EmpPlusGender();
		emp.setLastName("jack").setGender("男").setBirth(LocalDateTime.now());
		System.out.println(empPlusGenderMapper.insertEmp(emp));
	}


}
