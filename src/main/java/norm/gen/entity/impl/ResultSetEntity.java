package norm.gen.entity.impl;

import norm.gen.core.Generator;
import norm.gen.entity.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetEntity extends AbsEntity implements Entity {

    private String basePackage;
    private Column idColumn;
    private List<Column> columns = new ArrayList<Column>();
    private String table;
    private Converter converter;
    public ResultSetEntity(ResultSet resultSet, String table,String idName, Generator generator)
            throws SQLException{
        this.basePackage = generator.getBasePackage();
        this.converter = generator.getConverter();
        ResultSetMetaData metaData = resultSet.getMetaData();
        this.idColumn = new NameColumnImpl(converter,idName,resultSet,metaData);
        for(int i=1;i<=metaData.getColumnCount();i++){
            ColumnImpl column = new ColumnImpl(metaData,i,converter);
            if(metaData.getColumnName(i).equals(idName)){
                column.setIdColumn(true);
            }
            if(generator.getFilter() == null || generator.getFilter().accept(column)){
                columns.add(column);
            }
        }
        this.table = table;
    }


    @Override
    public String getTableName() {
        return this.table;
    }

    @Override
    public String getName() {
        return converter.getJavaName(this.table,null,this);
    }

    @Override
    public Column getIdColumn() {
        return this.idColumn;
    }

    @Override
    public List<Column> getColumns() {
        return this.columns;
    }

    @Override
    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getComment() {
        return null;
    }


}
