package norm.gen.entity;


public interface Converter {


    Class getJavaType(int sqlType, String sqlTypeName, Column column);

    String getJavaName(String name, Column column, Entity entity);



}
