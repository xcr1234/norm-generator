package norm.gen;

import norm.gen.core.Generator;
import norm.gen.core.GeneratorMain;
import norm.gen.entity.Column;
import norm.gen.entity.Entity;
import norm.gen.entity.GenerateException;
import norm.gen.entity.Mode;


public class App {


    public static void main(String[] args) throws  GenerateException {
        Generator generator = new Generator();

        generator.setOutputDir("D:\\files");
        generator.setBasePackage("com.example.table");
        generator.setDriverClass("com.mysql.jdbc.Driver");
        generator.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        generator.setUsername("root");
        generator.setPassword("root");

        generator.setTablePrefix("t_");
        generator.setColumnPrefix("t_");


        generator.setTemplate("template");

        generator.setMode(Mode.DATABASE);
        generator.setTable("user");

        //generator.setControllerOutR(true);

        // Mode.DATABASE无效时可以用Select模式：
        // generator.setMode(Mode.SELECT);
        // generator.setSql("select * from user");

        //generator.setFilter(MyFilter.class); // filter，过滤不需要的列

        GeneratorMain.doGenerate(generator);

    }


}
