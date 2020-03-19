package norm.gen.util;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;


public class FileTask {

    @XStreamImplicit(itemFieldName = "file")
    private List<FileTaskItem> fileTaskItemList;
    @XStreamImplicit(itemFieldName = "copy")
    private List<FileTaskItem> copyList;

    @XStreamAsAttribute
    private String encoding;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<FileTaskItem> getFileTaskItemList() {
        return fileTaskItemList;
    }


    public void setFileTaskItemList(List<FileTaskItem> fileTaskItemList) {
        this.fileTaskItemList = fileTaskItemList;
    }

    public List<FileTaskItem> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<FileTaskItem> copyList) {
        this.copyList = copyList;
    }
}
