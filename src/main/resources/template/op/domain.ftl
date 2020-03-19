<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->

<#assign basePackage = entity.basePackage >
package ${basePackage}.${generator.entityPackage};
import norm.anno.Column;
import norm.anno.Id;
import norm.anno.Table;
import org.springframework.format.annotation.DateTimeFormat;

<#if entity.comment?has_content>
/**
* ${entity.comment}
*/
</#if>
@Table("${entity.tableName}")
public class ${entity.name}${generator.entityName}<#if generator.serial> implements java.io.Serializable</#if>{
<#if generator.serial>
    private static final long serialVersionUID = 1L;
</#if>
<#if generator.staticCols>
<#list entity.columns as column>
    public static String COL_${column.columnName?upper_case} = "${column.columnName}";
</#list>
</#if>

<#list entity.columns as column>
    <#if column.comment?has_content>
    /**
    * ${column.comment}
    */
    </#if>
    <#if column.idColumn>
    @Id
    </#if>
    @Column("${column.columnName}")
    <#if column.SQLType == 91 || column.SQLType == 92 || column.SQLType == 93>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${column.javaType} ${column.javaName};
    public ${column.javaType} ${column.getterName}(){
        return this.${column.javaName};
    }
    public void ${column.setterName}(${column.javaType} ${column.javaName}){
        this.${column.javaName} = ${column.javaName};
    }
</#list>


}