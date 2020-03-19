package norm.gen.entity;


import norm.gen.core.Generator;

import java.io.File;
import java.util.Map;

public interface EntityWriter {

    void write(File out, Entity entity, Generator generator)throws Exception;


}
