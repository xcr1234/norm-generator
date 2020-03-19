<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->

<#assign basePackage = entity.basePackage>
package ${basePackage}.${generator.serviceImplPackage};

import norm.support.spring.SpringService;
import org.springframework.stereotype.Service;

import ${basePackage}.${generator.daoPackage}.${entity.name}${generator.daoName};
import ${basePackage}.${generator.entityPackage}.${entity.name}${generator.entityName};
import ${basePackage}.${generator.servicePackage}.${entity.name}${generator.serviceName};

@Service
public class ${entity.name}${generator.serviceImplName} extends SpringService<${entity.name}${generator.daoName},${entity.name}${generator.entityName},${entity.idColumn.javaType}> implements ${entity.name}${generator.serviceName}{

}
