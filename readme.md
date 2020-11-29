#此项目springboot整合mybatis，用于演示mybatis进阶
#运用逆向工程生成代码，所有包以auto开头
#在原有项目test—Mybatis的基础上集成springboot进行mybatis进阶的学习
#TypeHandler的实现类的演示----------com.roy.handler.GenderTypeHandler,实现功能：将数据库的int类型自动映射到POJO的string类型，对应的表tbl_emp_plus
#IDEA插件之Mybatis Log plugin的使用

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL 、存储过程以及高级映射。
MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis
可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO （Plain Old
Java Objects，普通老式 Java 对象）为数据库中的记录。

Mybatis 启动包依赖，此处导入的是 SpringBoot 和 Mybatis 整合启动器的依赖，点击
去可以看到，这个启动包依赖了mybatis 和mybatis-spring （Mybatis 和 Spring 整
合的 Jar 包），因此使用 SpringBoot 之后只需要导入这个启动器的依赖即可。


## xml文件放置在/src/main/resource/mapper/文件夹下
mybatis.mapper-locations=classpath*:/mapper/**/*.xml
@Mapper
该注解标注在 Mybatis 的interface 类上，SpringBoot 启动之后会扫描后会自动生成代理对象
@MapperScan 和@Mapper 这两个注解千万不要重复使用。

@ConditionalOnMissingBean 这个注解的意思就是当IOC容器中没有SqlSessionFac
tory 这个Bean对象这个配置才会生效; applyConfiguration(factory) 这行代码就是
创建一个org.apache.ibatis.session.Configuration 赋值给SqlSessionFactoryB
ean 。源码分析到这，应该很清楚了，无非就是自己在容器中创建一个SqlSessionFa
ctory ，然后设置属性即可，如下代码：

注意：如果对SqlSessionFactory 没有特殊定制，不介意重写，因为这会自动覆盖自动配置类中的配置。

三种映射的方式，分别是别名映射，驼峰映射、resultMap 映射
where 元素只会在子元素返回任何内容的情况下才插入 WHERE 子句。而且，若子句的开头为 AND 或 OR ， where 元素也会将它们去除。
使用 set+if 标签修改后，如果某项为 null 则不进行更新，而是保持数据库原值。

多个参数可以使用实体类封装，此时对应的key 就是属性名称，注意一定要有get方法。
cn.cb.demo.dao.UserMapper.selectList 这个id 已经存在了，导致创建sqlSessionFactory 失败。
这个StrictMap 不允许有重复的key ，而存入的key 就是id 。因此Mapper中的方法不能重载。
一行简单的调用到底如何找到对应的SQL呢？其实就是根据id 从Map<String, MappedStatement> mappedStatements 中查找对应的MappedStatement 。

插件的实现其实很简单，只需要实现Mybatis提供的Interceptor这个接口即可，源码如下：
public interface Interceptor {
  //拦截的方法
  Object intercept(Invocation invocation) throws Throwable;
  //返回拦截器的代理对象
  Object plugin(Object target);
  //设置一些属性
  void setProperties(Properties properties);
}

自定义插件需要用到两个注解，分别是@Intercepts和@Signature。
@Intercepts：标注在实现类上，表示这个类是一个插件的实现类。
@Signature：作为@Intercepts的属性，表示需要增强Mybatis的某些组件中的某些方法（可以指定多个）。常用的属性如下：
Class<?> type()：指定哪个组件（Executor、ParameterHandler、ResultSetHandler、StatementHandler）
String method()：指定增强组件中的哪个方法，直接写方法名称。
Class<?>[] args()：方法中的参数，必须一一对应，可以写多个；这个属性非常重用，区分重载方法。
Myabtis的六剑客
其实Mybatis的底层源码和Spring比起来还是非常容易读懂的，作者将其中六个重要的接口抽离出来称之为Mybatis的六剑客，
分别是SqlSession、Executor、StatementHandler、ParameterHandler、ResultSetHandler、TypeHandler
SqlSession是Myabtis中的核心API，主要用来执行crud命令，获取映射，管理事务。它包含了所有执行语句、提交或回滚事务以及获取映射器实例的方法。
语句执行方法
这些方法被用来执行定义在 SQL 映射 XML 文件中的 SELECT、INSERT、UPDATE 和 DELETE 语句。你可以通过名字快速了解它们的作用，
每一方法都接受语句的 ID 以及参数对象，参数可以是原始类型（支持自动装箱或包装类）、JavaBean、POJO 或 Map。
在Mybatis中有三个实现类，分别是DefaultSqlSession，SqlSessionManager、SqlSessionTemplate，其中重要的就是DefaultSqlSession，
这个后面讲到Mybatis执行源码的时候会一一分析。
在与SpringBoot整合时，Mybatis的启动器配置类会默认注入一个SqlSessionTemplate，源码如下：
  @Bean
  @ConditionalOnMissingBean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) 
    //根据执行器的类型创建不同的执行器，默认CachingExecutor
    ExecutorType executorType = this.properties.getExecutorType();
    if (executorType != null) {
      return new SqlSessionTemplate(sqlSessionFactory, executorType);
    } else {
      return new SqlSessionTemplate(sqlSessionFactory);
    }
  }

Executor
Mybatis的执行器，是Mybatis的调度核心，负责SQL语句的生成和缓存的维护，SqlSession中的crud方法实际上都是调用执行器中的对应方法执行。
CachingExecutor
这个比较有名了，二级缓存的维护类，与SpringBoot整合默认创建的就是这个家伙。下面来看一下如何走的二级缓存，源码如下：
SimpleExecutor
这个类像个直男，最简单的一个执行器，就是根据对应的SQL执行，不会做一些额外的操作。
BatchExecutor
通过批量操作来优化性能。通常需要注意的是批量更新操作，由于内部有缓存的实现，使用完成后记得调用flushStatements来清除缓存。
ReuseExecutor
可重用的执行器，重用的对象是Statement，也就是说该执行器会缓存同一个sql的Statement，省去Statement的重新创建，优化性能。
内部的实现是通过一个HashMap来维护Statement对象的。由于当前Map只在该session中有效，所以使用完成后记得调用flushStatements来清除Map。
显而易见，SpringBoot中默认创建的是CachingExecutor，因为默认的cacheEnabled的值为true。

ParameterHandler
ParameterHandler在Mybatis中负责将sql中的占位符替换为真正的参数，它是一个接口，有且只有一个实现类DefaultParameterHandler。
setParameters是处理参数最核心的方法。这里不再详细的讲，后面会讲到。

TypeHandler
这位大神应该都听说过，也都自定义过吧，简单的说就是在预编译设置参数和取出结果的时候将Java类型和JDBC的类型进行相应的转换。
当然，Mybatis内置了很多默认的类型处理器，基本够用，除非有特殊的定制，我们才会去自定义，
比如需要将Java对象以JSON字符串的形式存入数据库，此时就可以自定义一个类型处理器。
很简单的东西，此处就不再详细的讲了，后面会单独出一篇如何自定义类型处理器的文章。

ResultSetHandler
结果处理器，负责将JDBC返回的ResultSet结果集对象转换成List类型的集合或者Cursor。
具体实现类就是DefaultResultSetHandler，其实现的步骤就是将Statement执行后的结果集，
按照Mapper文件中配置的ResultType或ResultMap来封装成对应的对象，最后将封装的对象返回。
源码及其复杂，尤其是其中对嵌套查询的解析，这里只做个了解，后续会专门写一篇文章介绍。


类型处理器这个接口其实很简单，总共四个方法，一个方法将入参的Java类型的数据转换为JDBC类型，三个方法将返回结果转换为Java类型。源码如下：
public interface TypeHandler<T> {
  //设置参数，java类型转换为jdbc类型
  void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
  //将查询的结果转换为java类型
  T getResult(ResultSet rs, String columnName) throws SQLException;

  T getResult(ResultSet rs, int columnIndex) throws SQLException;

  T getResult(CallableStatement cs, int columnIndex) throws SQLException;
}
实际应用开发中的难免会有一些需求要自定义一个TypeHandler，比如这样一个需求：前端传来的年龄是男,女，
但是数据库定义的字段却是int类型（1男2女）。此时可以自定义一个年龄的类型处理器，进行转换。
自定义的方式有两种，一种是实现TypeHandler这个接口，另一个就是继承BaseTypeHandler这个便捷的抽象类。
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(String.class)
public class GenderTypeHandler extends BaseTypeHandler {

    //设置参数，这里将Java的String类型转换为JDBC的Integer类型
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, StringUtils.equals(parameter.toString(),"男")?1:2);
    }

    //以下三个参数都是将查询的结果转换
    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName)==1?"男":"女";
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex)==1?"男":"女";
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex)==1?"男":"女";
    }
}
这里涉及到两个注解，如下：
@MappedTypes：指定与其关联的 Java 类型列表。如果在 javaType 属性中也同时指定，则注解上的配置将被忽略。
@MappedJdbcTypes：指定与其关联的 JDBC 类型列表。如果在 jdbcType 属性中也同时指定，则注解上的配置将被忽略。
设置自定义的Typehandler所在的包，启动的时候会自动扫描配置到Mybatis中
mybatis.type-handlers-package=cn.cb.demo.typehandler

入参如何转换？
这个肯定是发生在设置参数的过程中，详细的代码在PreparedStatementHandler中的parameterize()方法中，这个方法就是设置参数的方法。源码如下：
 @Override
  public void parameterize(Statement statement) throws SQLException {
    //实际调用的是DefaultParameterHandler
    parameterHandler.setParameters((PreparedStatement) statement);
  }
 
 
单个参数： mybatis不会做特殊处理,用#{参数名}取出参数值 ，大括号里面写什么都是可以的

多个参数：mybatis会做特殊处理，多个参数会被封装成一个map，
封装规则：key：param1...paramN,或者参数的索引，value才是我们传入的参数值
命名参数：明确的指定封装map的key，@param

如果多个参数正好是我们业务数据的模型，可以直接传入POJO
#{属性名}就可以取出POJO的属性值

如果多个参数不是业务模型中的数据，没有对应的pojo，为了方便我们也可以直接传入map
#{key}就可以取出对应value

如果多个参数不是业务模型中的数据，但是经常使用，推荐编写一个TO
————————————————————————————————————————————————————————————————————————————————————
思考：取值方式
public Employee getEmp(@Param("id") Integer id, String lastName);
取值：#{id}或者#{param1}, #{param2}

public Employee getEmp(Integer id, Employee employee);
取值：#{param1}, #{param2.lastName}才能取出lastName，param2代表employee

public Employee getEmp(Integer id, @Param("emp") Employee employee);
取值：#{param1}, #{emp.lastName}或者#{param2.lastName}

特别注意：如果是Collection、List、Set、Array数据类型，也会把他们封装在map中
对应的key如下：Collection(collection)、List(list)、Array(array)
public Employee getEmp(List<Integer> ids);
取第一个id值：#{list[0]}
————————————————————————————————————————————————————————————————————————————————————
结合源码分析参数的封装

    ParamNameResolver这个类负责解析参数封装map的，里面有个names的map集合属性
    names {0=id, 1=lastName}
        获取每个标了@param注解参数的value值：id和lastname，赋值给names
        每次解析一个参数给map中保存信息：（key：参数索引，value：name的值）
        name的值
            标了注解就是@param注解的值
            没标注解全局配置useActualParamName(jdk1.8) name = 参数名
            没有全局配置项就是name = map.size()
    遍历names集合，封装到param集合中，names的value作为key，names集合的key作为取值参考
    如果有些key是1，2 ,替换成param1，param2
————————————————————————————————————————————————————————————————————————————————————
参数值的获取
#{}：可以获取map中的值或者pojo属性的值
${}同样可以
区别：#{}预编译的形式，将参数设置到sql语句中
大多数情况下采用#{}，
但以下情况采用${}:比如分表按照年份去拆分，组装表名称的时候， order by ${字段}
这些地方都是jdbc不支持占位符的地方采用${}  (分表、排序)
#{}只能取出参数的位置进行预编译
————————————————————————————————————————————————————————————————————————————————————
#{}更丰富的用法
jdbcType（数据库类型）通常需要在某种特定的条件下被设置，有些数据库可能不能识别mybatis对null的默认处理
比如Oracle（报错）jdbcType OTHER：无效的类型，因为mybatis对所有的null都映射的是原生Jdbc的 OTHER类型
oracle不能正确处理

由于全局配置中：jdbcTypeForNull=OTHER，oracle不支持
解决方案:1#{email,jdbcType=NULL},2.全局设置

IDEA插件之Mybatis Log plugin的使用
要配合开启持久层日志使用
logging:
  level:
    com.roy.mapper: debug
·Setting->plugin->Marketplace搜索框输入Mybatis Log plugin
日志中从Preparing到Parameters这两行的参数选中，右键选择restore sql from Selection
对于复杂的SQL语句来说，Mybatis Log plugin这款插件简直是太爱了，能够自动拼接参数生成执行的SQL语句。


Sqlsession
既然是接口，肯定不能在接口方法上打断点，上文介绍有两个实现类，分别是DefaultSqlSession、SqlSessionTemplate。
那么SpringBoot在初始化的时候到底注入的是哪一个呢？这个就要看Mybatis的启动器的自动配置类了，其中有一段这样的代码，如

从上面的代码可以知道，SpringBoot启动时注入了SqlSessionTemplate，此时就肯定从SqlSessionTemplate入手了。它的一些方法如下图：
public class SqlSessionTemplate implements SqlSession, DisposableBean {
    private final SqlSessionFactory sqlSessionFactory;
    private final ExecutorType executorType;
    private final SqlSession sqlSessionProxy;
    private final PersistenceExceptionTranslator exceptionTranslator;
}