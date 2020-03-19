<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->

<#assign basePackage = entity.basePackage>
package ${basePackage}.${generator.daoPackage};
import norm.CrudDao;
import org.springframework.stereotype.Repository;
import ${basePackage}.${generator.entityPackage}.${entity.name}${generator.entityName};

@Repository
public interface ${entity.name}${generator.daoName} extends CrudDao<${entity.name}${generator.entityName} , ${entity.idColumn.javaType}> {

}
