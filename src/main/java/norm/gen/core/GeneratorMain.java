package norm.gen.core;

import norm.gen.entity.*;
import norm.gen.entity.impl.DefaultConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GeneratorMain {

    private static Log logger = LogFactory.getLog(GeneratorMain.class);


    public static void doGenerate(Generator generator) throws GenerateException {
        long time = System.currentTimeMillis();
        String o = generator.getOutputDir();
        if(o == null){
            throw new GenerateException("outputDir is null!");
        }
        File outDir = new File(o);
        if(!outDir.exists() || !outDir.isDirectory()){
            throw new GenerateException("outputDir is not a valid dir:" + outDir);
        }

        String basePackage = generator.getBasePackage();
        if(basePackage == null){
            throw new GenerateException("basePackage is null!");
        }

        if(generator.isControllerOutR()){
            if(StringUtils.isEmpty(generator.getrPackage())){
                generator.setrPackage(basePackage + ".util");
            }
            generator.setControllerOutJson(false);
        }

        if(generator.getConverter() == null){
            generator.setConverter(new GeneratorConverter(generator));
        }




        EntityBuilder entityBuilder = generator.getMode().getBuilder();
        List<Entity> entityList = entityBuilder.build(generator);


        logger.info("success create entities:" + entityList);


        for(Entity entity : entityList){
            try {
                EntityWriter writer = entity.getWriter();
                writer.write(outDir,entity,generator);
            } catch (Exception e) {
                throw new GenerateException("generate write error!",e);
            }
        }


        long cost = System.currentTimeMillis() - time;
        logger.info("generator run successfully in " + cost + " ms.");
    }
}
