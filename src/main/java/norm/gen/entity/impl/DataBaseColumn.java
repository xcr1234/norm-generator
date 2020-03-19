package norm.gen.entity.impl;

import norm.gen.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseColumn extends AbsColumn {


    private String columnName;
    private int sqlType;
    private String sqlTypeName;
    private String comment;
    private Converter converter;



    public DataBaseColumn(ResultSet resultSet, Converter converter) throws SQLException{
        this.converter = converter;
        columnName = resultSet.getString("COLUMN_NAME");
        sqlType = resultSet.getInt("DATA_TYPE");
        sqlTypeName = resultSet.getString("TYPE_NAME");
        comment = resultSet.getString("REMARKS");
    }

    @Override
    public Class getJavaTypeClass() {
        return converter.getJavaType(sqlType,sqlTypeName,this);
    }

    @Override
    public String getComment() {
        return comment;
    }


    @Override
    public int getSQLType() throws SQLException {
        return sqlType;
    }

    @Override
    public String getSQLTypeName()  {
        return sqlTypeName;
    }

    @Override
    public String getJavaName() {
        return converter.getJavaName(columnName,this,null);
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

}
