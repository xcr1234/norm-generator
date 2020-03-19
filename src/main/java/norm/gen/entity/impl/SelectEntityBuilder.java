package norm.gen.entity.impl;

import norm.gen.core.Generator;
import norm.gen.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SelectEntityBuilder  implements EntityBuilder {

    private static Log logger = LogFactory.getLog(SelectEntityBuilder.class);

    @Override
    public List<Entity> build(Generator generator) throws GenerateException {
        String sql = generator.getSql();
        String table = generator.getTable();
        if(table == null){
            throw new GenerateException("generator.table is null!");
        }
        if(sql == null){
            throw new GenerateException("generator.sql is null!");
        }

        String idColumn = generator.getIdColumn();
        if(idColumn == null){
            throw new GenerateException("generator.idColumn is null!");
        }
        Connection connection = null;
        try{
            connection = generator.openConnection();
            logger.info("execute sql [" + sql + "],connection is :" + connection);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSetEntity entity =  new ResultSetEntity(resultSet,table,idColumn,generator);
            return Collections.singletonList((Entity) entity);
        }catch (SQLException e){
           throw new GenerateException("an error occurred while execute query.",e);
        } finally{
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){}
        }
    }

    @Override
    public String getMode() {
        return "select";
    }
}
