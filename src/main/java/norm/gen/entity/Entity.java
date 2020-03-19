package norm.gen.entity;

import java.util.List;
import java.util.Map;

public interface Entity {

    String getTableName();

    String getName();

    Column getIdColumn();

    List<Column> getColumns();

    String getBasePackage();

    EntityWriter getWriter();

    void setWriter(EntityWriter entityWriter);

    String getBasePackageDir();


    String getComment();

}
