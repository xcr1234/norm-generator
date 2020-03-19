<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->

<#assign basePackage = entity.basePackage>
package ${basePackage}.${generator.servicePackage};

import ${basePackage}.${generator.daoPackage}.${entity.name}${generator.daoName};
import ${basePackage}.${generator.entityPackage}.${entity.name}${generator.entityName};
import norm.BaseService;

public interface ${entity.name}${generator.serviceName} extends BaseService<${entity.name}${generator.entityName},${entity.idColumn.javaType}>{


}