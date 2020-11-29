package com.roy.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * description：
 * @Intercepts 注解标记这是一个拦截器,其中可以指定多个@Signature
 * @Signature 指定该拦截器拦截的是四大对象中的哪个方法
 *      type：拦截器的四大对象的类型
 *      method：拦截器的方法，方法名
 *      args：入参的类型，可以是多个，根据方法的参数指定，以此来区分方法的重载
 *
 * intercept方法：最终会拦截的方法，最重要的一个方法。
 * plugin方法：返回一个代理对象，如果没有特殊要求，直接使用Mybatis的工具类Plugin返回即可。
 * setProperties：设置一些属性，不重要。
 *
 *
 * author：dingyawu
 * date：created in 16:20 2020/11/28
 * history:
 */
@Intercepts({@Signature(type = ParameterHandler.class,method ="setParameters",args = {PreparedStatement.class})})
public class ParameterInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截器执行："+invocation.getTarget());
        //目标对象
        Object target = invocation.getTarget();
        //获取目标对象中所有属性的值，因为ParameterHandler使用的是DefaultParameterHandler，因此里面的所有的属性都封装在其中
        MetaObject metaObject = SystemMetaObject.forObject(target);
        //使用xxx.xxx.xx的方式可以层层获取属性值，这里获取的是mappedStatement中的id值
        String value = (String) metaObject.getValue("mappedStatement.id");
        //如果是指定的查询方法
        if ("com.roy.mapper.EmployeeMapper.getEmpById".equals(value)){
            //设置参数的值是admin_1，即是设置id=admin_1，因为这里只有一个参数，可以这么设置，如果有多个需要需要循环
            metaObject.setValue("parameterObject", 5);
        }
        //执行目标方法
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        //如果没有特殊定制，直接使用Plugin这个工具类返回一个代理对象即可
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}