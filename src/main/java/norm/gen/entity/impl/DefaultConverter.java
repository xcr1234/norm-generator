package norm.gen.entity.impl;

import norm.gen.entity.*;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import static java.sql.Types.*;

public class DefaultConverter implements Converter {

    @Override
    public Class getJavaType(int sqlType, String sqlTypeName, Column column) {
        switch (sqlType){
            case CHAR: case VARCHAR: case LONGVARCHAR: case LONGNVARCHAR:
                return String.class;
            case NUMERIC: case DECIMAL:
                return BigDecimal.class;
            case BIT: case BOOLEAN:
                return Boolean.class;
            case TINYINT:
                return Byte.class;
            case SMALLINT:
                return Short.class;
            case INTEGER:
                return Integer.class;
            case BIGINT:
                return Long.class;
            case REAL:
                return Float.class;
            case FLOAT: case DOUBLE:
                return Double.class;
            case BINARY: case LONGVARBINARY: case VARBINARY:
                return byte[].class;
            case DATE: case TIME: case TIMESTAMP:
                return Date.class;
            case CLOB:
                return Clob.class;
            case BLOB:
                return Blob.class;

        }
        throw new UnsupportedOperationException("unsupported sql type:" + sqlTypeName);
    }



    @Override
    public String getJavaName(String columnName,Column column,Entity entity) {
        StringBuilder sb = new StringBuilder(columnName.length() + 6);
        boolean flag = false;
        for(int i=0;i<columnName.length();i++){

            char c = columnName.charAt(i);
            if(c == '_'){
                flag = true;
            }else if(flag || i == 0){
                sb.append(Character.toUpperCase(c));
                flag = false;
            }else{
                sb.append(Character.toLowerCase(c));
                flag = false;
            }
        }
        if(column != null){
            return StringUtils.uncapitalize(sb.toString());
        }
        return sb.toString();
    }
}
