<#-- @ftlvariable name="generator" type="norm.gen.core.Generator" -->
package ${generator.rPackage};

import java.util.HashMap;

public class R extends HashMap<String, Object> {

    public R add(String key, Object value) {
        put(key, value);
        return this;
    }

    public static R ok() {
        return new R().add("code", 0).add("msg", "success");
    }

    public static R error(int code,String msg){
        return new R().add("code", code).add("msg", msg);
    }

    public static R error(String msg){
        return new R().add("code", 500).add("msg", msg);
    }

}
