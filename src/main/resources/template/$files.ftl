<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->
<#assign packageDir = entity.basePackageDir>
<?xml version="1.0" encoding="UTF-8"?>
<files>
    <file src="op/dao.ftl" target="${packageDir}/${generator.daoPackage}/${entity.name}${generator.daoName}.java"></file>
    <file src="op/domain.ftl" target="${packageDir}/${generator.entityPackage}/${entity.name}${generator.entityName}.java"></file>
    <file src="op/service.ftl" target="${packageDir}/${generator.servicePackage}/${entity.name}${generator.serviceName}.java"></file>
    <file src="op/service-impl.ftl" target="${packageDir}/${generator.serviceImplPackage?replace('.','/')}/${entity.name}${generator.serviceImplName}.java"></file>
    <#if generator.controller>
        <file src="op/rest-controller.ftl" target="${packageDir}/${generator.controllerPackage}/${entity.name}${generator.controllerName}.java"></file>
    </#if>
    <#if generator.controllerOutR>
        <file src="op/r.ftl" target="${generator.rPackage?replace('.','/')}/R.java"></file>
    </#if>
</files>