package norm.gen.entity.impl;

import norm.gen.core.Generator;
import norm.gen.entity.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import norm.gen.util.FileTask;
import norm.gen.util.FileTaskItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultEntityWriter implements EntityWriter {

    private Log logger = LogFactory.getLog(DefaultEntityWriter.class);

    private static final DefaultEntityWriter defaultEntityWriter = new DefaultEntityWriter();

    public static DefaultEntityWriter getInstance() {
        return defaultEntityWriter;
    }


    @Override
    public void write(File out, Entity entity, Generator generator) throws IOException, TemplateException, GenerateException {
        String template = generator.getTemplate();
        if (template == null) {
            template = "template";
        }


        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(),  template);

        Map<String, Object> root = new HashMap<String, Object>();
        root.put("entity", entity);
        root.put("generator",generator);

        Template fileTemplate = configuration.getTemplate("$files.ftl");
        StringWriter stringWriter = new StringWriter();

        fileTemplate.process(root, stringWriter);

        String filesXml = stringWriter.toString();
        XStream xStream = new XStream(new Dom4JDriver());
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{FileTask.class, FileTaskItem.class});
        xStream.autodetectAnnotations(true);
        xStream.alias("files", FileTask.class);
        FileTask fileTask = (FileTask) xStream.fromXML(filesXml);
        List<FileTaskItem> fileTaskItemList = fileTask.getFileTaskItemList();

        String encoding = fileTask.getEncoding();
        if(encoding == null || encoding.isEmpty()){
            encoding = Charset.defaultCharset().displayName();
        }


        for (FileTaskItem fileTaskItem : fileTaskItemList) {
            File file = new File(out, fileTaskItem.getTarget());
            write(configuration, file, root, fileTaskItem.getSrc(),encoding);
        }

        if(fileTask.getCopyList() != null){
            URL url = Thread.currentThread().getContextClassLoader().getResource("template");
            if(url == null){
                throw new FileNotFoundException("classpath:" + template);
            }
            String path = url.getPath();
            for(FileTaskItem item : fileTask.getCopyList()){
              File file1 = new File(path + File.separator + template,item.getSrc());
              File file2 = new File(out,item.getTarget());
              FileUtils.copyFile(file1,file2);
              logger.info("success copy [" + file1 + "] to [" + file2 + "]");
            }
        }

    }

    protected void write(Configuration configuration, File file, Map<String, Object> root, String templateName,String encoding) throws IOException, TemplateException {
        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("failed to make dir:" + parent);
            }
        }
        Template daoTemplate = configuration.getTemplate(templateName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            daoTemplate.process(root, new OutputStreamWriter(outputStream, encoding));
            logger.info("generate file [" + file + "] with template:" + templateName);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

}



