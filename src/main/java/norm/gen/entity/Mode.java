package norm.gen.entity;

import norm.gen.entity.impl.DatabaseEntityBuilder;
import norm.gen.entity.impl.SelectEntityBuilder;

/**
 * 查询的模式
 * SELECT：通过sql查询{@link java.sql.ResultSet}，然后{@link java.sql.ResultSetMetaData}接口获取
 * DATABASE： 通过{@link java.sql.DatabaseMetaData}接口查询
 */
public enum Mode {

    SELECT {
        @Override
        public EntityBuilder getBuilder() {
            return new SelectEntityBuilder();
        }
    }, DATABASE {
        @Override
        public EntityBuilder getBuilder() {
            return new DatabaseEntityBuilder();
        }
    };

    public abstract EntityBuilder getBuilder();
}
