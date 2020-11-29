package com.roy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * description：
 * @mapperScan同事扫描多个包，一个是自定义包，一个是逆向工程生成的包
 * author：dingyawu
 * date：created in 21:36 2020/11/27
 * history:
 */
@SpringBootApplication
@MapperScan({"com.roy.mapper","com.roy.autoMapper"})
public class APP {

	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

}
