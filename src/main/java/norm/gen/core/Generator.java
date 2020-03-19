package norm.gen.core;


import norm.gen.entity.ColumnFilter;
import norm.gen.entity.Converter;
import norm.gen.entity.GenerateException;
import norm.gen.entity.Mode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Generator {


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
    private boolean controller = true;


    /**
     * 生成实体类加上序列化
     */
    private boolean serial;

    /**
     * 控制器最后输出json，例如：
     * out.write("{\"code\":0,\"success\":true}");
     */
    private boolean controllerOutJson = true;

    /**
     * 控制器最后输出R，例如
     * return R;
     */
    private boolean controllerOutR;

    /**
     * R类的包名，默认basePackage.util.R
     */
    private String rPackage;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }



    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


    public ColumnFilter getFilter() {
        return filter;
    }

    public void setFilter(ColumnFilter filter) {
        this.filter = filter;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getColumnPrefix() {
        return columnPrefix;
    }

    public void setColumnPrefix(String columnPrefix) {
        this.columnPrefix = columnPrefix;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public boolean isStaticCols() {
        return staticCols;
    }

    public void setStaticCols(boolean staticCols) {
        this.staticCols = staticCols;
    }

    public boolean isSerial() {
        return serial;
    }

    public void setSerial(boolean serial) {
        this.serial = serial;
    }

    public String getDaoPackage() {
        return daoPackage;
    }

    public void setDaoPackage(String daoPackage) {
        this.daoPackage = daoPackage;
    }

    public String getDaoName() {
        return daoName;
    }

    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplPackage() {
        return serviceImplPackage;
    }

    public void setServiceImplPackage(String serviceImplPackage) {
        this.serviceImplPackage = serviceImplPackage;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public boolean isController() {
        return controller;
    }

    public void setController(boolean controller) {
        this.controller = controller;
    }

    public boolean isControllerOutJson() {
        return controllerOutJson;
    }

    public void setControllerOutJson(boolean controllerOutJson) {
        this.controllerOutJson = controllerOutJson;
    }

    public boolean isControllerOutR() {
        return controllerOutR;
    }

    public void setControllerOutR(boolean controllerOutR) {
        this.controllerOutR = controllerOutR;
    }

    public String getrPackage() {
        return rPackage;
    }

    public void setrPackage(String rPackage) {
        this.rPackage = rPackage;
    }

    public Connection openConnection() throws SQLException,GenerateException{
        if(driverClass == null){
            throw new GenerateException("generator.driverClass is null!");
        }
        try{
            Class.forName(driverClass);
        }catch (ClassNotFoundException e){
            throw new GenerateException("generator.driverClass not found.",e);
        }
        return DriverManager.getConnection(jdbcUrl,username,password);
    }
}