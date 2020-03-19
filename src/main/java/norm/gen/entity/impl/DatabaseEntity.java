package norm.gen.entity.impl;

import norm.gen.core.Generator;
import norm.gen.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseEntity extends AbsEntity implements Entity {


    private String tableName;
    private Converter converter;
    private Column idColumn;
    private List<Column> columns;
    private String basePackage;
    private String comment;



    public DatabaseEntity(ResultSet tableRet, Generator generator)throws SQLException, GenerateException{
        this.basePackage = generator.getBasePackage();
        this.converter = generator.getConverter();
        tableName = tableRet.getString("TABLE_NAME");
        comment = tableRet.getString("REMARKS");
    }

    void setIdColumn(Column idColumn) {
        this.idColumn = idColumn;
    }

    void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getName() {
        return converter.getJavaName(tableName,null,this);
    }

    @Override
    public Column getIdColumn() {
        return idColumn;
    }

    @Override
    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getComment() {
        return comment;
    }
}
