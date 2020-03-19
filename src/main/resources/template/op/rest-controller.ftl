<#-- @ftlvariable name="entity" type="norm.gen.entity.Entity" -->
<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->
<#assign basePackage = entity.basePackage>
package ${basePackage}.${generator.controllerPackage};

<#if generator.controllerOutR>
import ${generator.rPackage}.R;
</#if>
import ${basePackage}.${generator.entityPackage}.${entity.name}${generator.entityName};
import ${basePackage}.${generator.servicePackage}.${entity.name}${generator.serviceName};
import norm.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<#if generator.controllerOutJson>
import java.io.PrintWriter;
</#if>
import java.util.List;


@RestController
@RequestMapping("/api/${entity.name ? uncap_first}")
public class ${entity.name}${generator.controllerName} {

    @Autowired
    private ${entity.name}${generator.serviceName} ${entity.name ? uncap_first}${generator.serviceName};


    @RequestMapping(value = "/list")
    public Page<${entity.name}${generator.entityName}> listAll(${entity.name}${generator.entityName} domain, Page<${entity.name}${generator.entityName}> page){
        ${entity.name ? uncap_first}${generator.serviceName}.findAll(domain,page);
        return page;
    }

    <#if generator.controllerOutJson>
    @RequestMapping(value = "/update")
    public void update(${entity.name}${generator.entityName} domain, PrintWriter out){
        ${entity.name ? uncap_first}${generator.serviceName}.update(domain);
        out.write("{\"code\":0,\"msg\":\"success\"}");

    }
    @RequestMapping(value = "/insert")
    public void insert(${entity.name}${generator.entityName} domain, PrintWriter out) {
        ${entity.name ? uncap_first}${generator.serviceName}.save(domain);
        out.write("{\"code\":0,\"success\":true}");
   }

    @RequestMapping(value = "/delete")
    public void delete(${entity.name}${generator.entityName} domain, PrintWriter out) {
        ${entity.name ? uncap_first}${generator.serviceName}.delete(domain);
        out.write("{\"code\":0,\"msg\":\"success\"}");
    }
    </#if>


    <#if generator.controllerOutR>
    @RequestMapping(value = "/update")
    public R update(${entity.name}${generator.entityName} domain){
        ${entity.name ? uncap_first}${generator.serviceName}.update(domain);
        return R.ok();
    }
    @RequestMapping(value = "/insert")
    public R insert(${entity.name}${generator.entityName} domain){
        ${entity.name ? uncap_first}${generator.serviceName}.save(domain);
        return R.ok();
    }

    @RequestMapping(value = "/delete")
    public R delete(${entity.name}${generator.entityName} domain){
        ${entity.name ? uncap_first}${generator.serviceName}.delete(domain);
        return R.ok();
    }
    </#if>


}