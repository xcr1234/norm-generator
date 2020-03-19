package norm.gen.entity.impl;

import norm.gen.entity.Column;
import org.apache.commons.lang.StringUtils;

public abstract class AbsColumn implements Column {

    @Override
    public String getGetterName() {
        if(boolean.class == getJavaTypeClass()){
            return "is" + StringUtils.capitalize(this.getJavaName());
        }
        return "get" + StringUtils.capitalize(this.getJavaName());
    }

    @Override
    public String getSetterName() {
        return "set" + StringUtils.capitalize(this.getJavaName());
    }

    @Override
    public String toString() {
        return String.format("Column{javaType=%s,sqlType=%s,javaName=%s,columnName=%s}",
                getJavaType(),
                getSQLTypeName(),
                getJavaName(),
                getColumnName());
    }

    @Override
    public String getJavaType() {
        String name = getJavaTypeClass().getName();
        if(name.startsWith("java.lang.")){
            return name.substring("java.lang.".length());
        }
        return name;
    }
    private boolean idColumn;

    @Override
    public boolean isIdColumn() {
        return idColumn;
    }

    public void setIdColumn(boolean idColumn) {
        this.idColumn = idColumn;
    }
}
