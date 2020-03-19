package norm.gen.core;

import norm.gen.entity.Column;
import norm.gen.entity.Entity;
import norm.gen.entity.impl.DefaultConverter;

public class GeneratorConverter extends DefaultConverter {

    private final Generator generator;


    public GeneratorConverter(Generator generator) {
        this.generator = generator;
    }

    @Override
    public String getJavaName(String columnName, Column column, Entity entity) {
        if(column == null ){
            if(generator.getTablePrefix() != null && columnName.startsWith(generator.getTablePrefix())){
                columnName = columnName.substring(generator.getTablePrefix().length());
            }
        }else{
            if(generator.getColumnPrefix() != null && columnName.startsWith(generator.getColumnPrefix())){
                columnName = columnName.substring(generator.getColumnPrefix().length());
            }
        }
        return super.getJavaName(columnName, column, entity);
    }
}
