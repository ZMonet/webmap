package cn.common.uploadFile;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by user on 2016/8/31.
 */
public abstract class GenericFileUploadAction extends GenericMultipleFileUploadAction {
    protected File file;
    protected String fileFileName;
    protected String fileContentType;
    protected String dir;
    protected String targetFileName;
    protected  String targetRelativePath="/upload";

    @Override
    public String execute(){
        super.execute();

        if (multipleFilesTargetFileName!=null){
            return SUCCESS;
        }
        try {
            targetFileName = getUploadFile();
        } catch (Exception e) {
            //e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String getUploadFile() throws Exception
    {
        String  realPath  = ServletActionContext.getServletContext().getRealPath(targetRelativePath);
        String targetDirectory = realPath;
        targetFileName  = generateFileName(fileFileName);
        setDir(targetDirectory + File.separator + targetFileName);
        File target = new File(targetDirectory,targetFileName);
        FileUtils.copyFile(file, target);
        file.delete();
        return this.targetFileName;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    public String getDir() {
        return dir;
    }
    public void setDir(String dir) {
        this.dir = dir;
    }
    public String getTargetFileName() {
        return targetFileName;
    }
    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }
    @Override
    public String getTargetRelativePath() {
        return targetRelativePath;
    }
    @Override
    public void setTargetRelativePath(String targetRelativePath) {
        this.targetRelativePath = targetRelativePath;
    }

}
