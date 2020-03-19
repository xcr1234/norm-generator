package norm.gen.entity.impl;

import norm.gen.core.Generator;
import norm.gen.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseEntityBuilder implements EntityBuilder {
    @Override
    public List<Entity> build(Generator generator) throws GenerateException {
        String table = generator.getTable();
        if(table == null){
            throw new GenerateException("generator.table is null!");
        }

        String catalog = generator.getCatalog();
        String schema = generator.getSchema();

        Connection connection = null;
        try {
            connection = generator.openConnection();

            DatabaseMetaData metaData = connection.getMetaData();

            List<Entity> entities = new ArrayList<Entity>();
            ResultSet tableRet = metaData.getTables(catalog,"%",table,new String[]{"TABLE"});
            boolean flag = true;
            while (tableRet.next()){
                DatabaseEntity entity = createEntity(metaData,tableRet,generator,catalog,schema);
                entities.add(entity);
                flag = false;
            }
            if(flag){
                throw new GenerateException("table not found:" + table);
            }
            return entities;
        }catch (SQLException e){
            throw new GenerateException("database query error.",e);
        }finally{
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException e){}
        }

    }


    private DatabaseEntity createEntity(DatabaseMetaData metaData,ResultSet tableRet,Generator generator,
                                        String catalog,String schema) throws SQLException, GenerateException {
        DatabaseEntity entity = new DatabaseEntity(tableRet,generator);

        String tableName = entity.getTableName();


        ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(catalog,schema,tableName);
        if(!primaryKeyResultSet.next()){
            throw new GenerateException("table has no primary key:" + tableName);
        }
        String idName = primaryKeyResultSet.getString("COLUMN_NAME");

        if(primaryKeyResultSet.next()){
            throw new GenerateException("table has multiple primary keys:" + tableName);
        }

        Column idColumn = null;
        List<Column> columns = new ArrayList<Column>();
        ResultSet colRet = metaData.getColumns(catalog,schema,tableName,"%");
        while (colRet.next()){
            DataBaseColumn column = new DataBaseColumn(colRet,generator.getConverter());
            if(column.getColumnName().equals(idName)){
                idColumn = column;
                column.setIdColumn(true);
            }
            if(generator.getFilter() == null || generator.getFilter().accept(column)){
                columns.add(column);
            }
        }
        if(idColumn == null){
            throw new GenerateException("table has no primary key:" + tableName);
        }
        entity.setIdColumn(idColumn);
        entity.setColumns(columns);
        return entity;
    }

    @Override
    public String getMode() {
        return "database";
    }
}
