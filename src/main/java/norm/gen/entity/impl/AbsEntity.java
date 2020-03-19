package norm.gen.entity.impl;

import norm.gen.entity.*;

import java.util.Map;

public abstract class AbsEntity implements Entity {


    private EntityWriter writer;



    @Override
    public EntityWriter getWriter() {
        if(writer == null){
            return DefaultEntityWriter.getInstance();
        }
        return writer;
    }



    @Override
    public void setWriter(EntityWriter writer) {
        this.writer = writer;
    }

    @Override
    public String getBasePackageDir() {
        return getBasePackage().replace('.','/');
    }

    @Override
    public String toString() {
        return String.format("Entity{tableName=%s,name=%s,columns=%s,idColumn=%s}",
                getTableName(),
                getName(),
                String.valueOf(getColumns()),
                String.valueOf(getIdColumn()));
    }
}
