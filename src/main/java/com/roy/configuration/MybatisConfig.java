package com.roy.configuration;

import com.roy.interceptor.ParameterInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description：
 * author：dingyawu
 * date：created in 16:30 2020/11/28
 * history:
 */
@Configuration
public class MybatisConfig{

    /**
     * @Bean ： 该注解用于向容器中注入一个Bean
     * 注入Interceptor[]这个Bean
     * @return
     */
    @Bean
    public Interceptor[] interceptors(){
        //创建ParameterInterceptor这个插件
        ParameterInterceptor parameterInterceptor = new ParameterInterceptor();
        //放入数组返回
        return new Interceptor[]{parameterInterceptor};
    }
}