package norm.gen.entity;



import norm.gen.core.Generator;

import java.util.List;
import java.util.Map;

public interface EntityBuilder {
    List<Entity> build(Generator generator) throws GenerateException;
    String getMode();
}
