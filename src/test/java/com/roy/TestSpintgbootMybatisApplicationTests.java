package com.roy;

import com.roy.mapper.MemeberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestSpintgbootMybatisApplicationTests {

	@Autowired
	MemeberMapper memeberMapper;
	@Test
	void contextLoads() {
		System.out.println(memeberMapper.queryMemberByid(1));
	}

}
