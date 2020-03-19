# norm-generator

运行App类生成项目



generator类配置参考:

```java
    /**
     * 要生成的表名
     */
    private String table;

    /**
     * sql查询语句，mode为{@link Mode#SELECT}时需要填写
     */
    private String sql;

    /**
     * id列的名称，mode为{@link Mode#SELECT}时需要填写
     */
    private String idColumn;

    /**
     * 输出目录
     */
    private String outputDir;

    /**
     * 表名前缀，可为空
     */
    private String tablePrefix;

    /**
     * 列名前缀，可为空
     */
    private String columnPrefix;

    /**
     * 自定义转换器接口，默认实例为{@link GeneratorConverter}
     */
    private Converter converter;

    /**
     * 生成的包名
     */
    private String basePackage;

    /**
     * 数据库连接参数
     */
    private String driverClass;
    /**
     * 数据库连接参数
     */
    private String jdbcUrl;
    /**
     * 数据库连接参数
     */
    private String username;
    /**
     * 数据库连接参数
     */
    private String password;
    /**
     * 数据库连接catalog，可为空
     */
    private String catalog;
    /**
     * 数据库连接schema，可为空
     */
    private String schema;

    /**
     * 生成模式
     */
    private Mode mode = Mode.DATABASE;

    /**
     * 模板$files.ftl的classpath位置
     */
    private String template;

    /**
     * 列过滤器，过滤不需要的列，可为空
     */
    private ColumnFilter filter;

    /**
     * 生成static 列名属性，例如
     * public static String COL_ID = "id";
     */
    private boolean staticCols = true;

    /**
     * dao的Package
     */
    private String daoPackage = "dao";

    /**
     * dao的name, 最后映射出来就是XXDao
     */
    private String daoName = "Dao";

    /**
     * entity的Package
     */
    private String entityPackage = "domain";

    /**
     * entity的name, 最后映射出来就是XXDomain
     */
    private String entityName = "Domain";

    /**
     * service的Package
     */
    private String servicePackage = "service";

    /**
     * service的name, 最后映射出来就是XXService
     */
    private String serviceName = "Service";

    /**
     * service-impl的Package
     */
    private String serviceImplPackage = "service.impl";

    /**
     * service-impl的name, 最后映射出来就是XXServiceImpl
     */
    private String serviceImplName = "ServiceImpl";

    /**
     * controller的Package
     */
    private String controllerPackage = "controller";

    /**
     * controller的name, 最后映射出来就是XXController
     */
    private String controllerName = "Controller";

    /**
     * 是否输出Controller
     */
    private boolean controller;


    /**
     * 生成实体类加上序列化
     */
    private boolean serial;

```

# sql类型转java类型

修改`DefaultConverter`类的getJavaType方法

# R模式

默认生成出来的controller直接打印json：

```java
  @RequestMapping(value = "/insert")
    public void insert(UserDomain domain, PrintWriter out) throws IOException{
        userService.save(domain);
        out.write("{\"code\":0,\"success\":true}");
   }
```

可以打开controllerOutR，

```java
 generator.setControllerOutR(true);
```

最后生成出来的如下：

```java
    @RequestMapping(value = "/insert")
    public R insert(UserDomain domain){
        userService.save(domain);
        return R.ok();
    }
```

