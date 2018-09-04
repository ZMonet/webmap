package cn.common.uploadFile;

import cn.common.action.GenericActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.util.jar.Pack200.Packer.ERROR;

/**
 * Created by user on 2016/8/31.
 */
public abstract class GenericMultipleFileUploadAction extends GenericActionSupport {
    protected File[] multipleFiles;
    protected String[] multipleFilesFileName;
    protected String[] multipleFilesContentType;
    protected String[] multipleFilesTargetFilePath;
    protected String[] multipleFilesTargetFileName;

    @Override
    public String execute(){
        try {
            multipleFilesTargetFileName = null;
            multipleFilesTargetFilePath = null;
            multipleFilesTargetFileName = getAllUploadFile();
            if (null == multipleFiles || null == multipleFilesFileName || null == multipleFilesContentType) {
                return ERROR;
            }
            if (multipleFiles.length < 1 || multipleFilesFileName.length < 1 || multipleFilesContentType.length < 1) {
                return ERROR;
            }

        } catch (Exception e) {
            //e.printStackTrace();
            //ServletActionContext.getRequest().setAttribute("ERROR", e.getMessage());
            return ERROR;
        }
        try {
            return super.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ERROR;
    }

    protected  String targetRelativePath="/upload";

    public  String[] getAllUploadFile()
    {
        try {
            String  realPath  = ServletActionContext.getServletContext().getRealPath(targetRelativePath);
            String targetDirectory = realPath;
            int nFileCount = this.multipleFiles.length;
            this.multipleFilesTargetFileName  = new String[nFileCount];
            this.multipleFilesTargetFilePath = new String[nFileCount];
            for(int i = 0; i < nFileCount;i++) {
                //Generate the target file name that is unique.
                multipleFilesTargetFileName[i] = generateFileName(this.multipleFilesFileName[i]);//client file name
                multipleFilesTargetFilePath[i] = targetDirectory + File.separator + multipleFilesTargetFileName[i];
                File target = new File(targetDirectory,multipleFilesTargetFileName[i]);
                FileUtils.copyFile(multipleFiles[i], target);
                multipleFiles[i].delete();
            }
            return multipleFilesTargetFileName;
        }catch (Exception e){

        }
        return null;
    }

    protected String generateFileName(String fileName) {
        if (fileName==null){
            return null;
        }
        //Get current time
        DateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");
        //change to string
        String formatDate = format.format(new Date());
        // Generate the random file code
        int random   = new Random().nextInt(10000);
        //Get file suffix
        //System.out.println("1111111111111111111111111");
        int position = fileName.lastIndexOf(".");
        //System.out.println("22222");
        String extension = fileName.substring(position);

        String uuid= "-"+ UUID.randomUUID().toString();
        //Compose a new file name
        return formatDate+"-" + random +uuid+ extension;
    }

    public File[] getMultipleFiles() {
        return multipleFiles;
    }
    public void setMultipleFiles(File[] multipleFiles) {
        this.multipleFiles = multipleFiles;
    }
    public String[] getMultipleFilesFileName() {
        return multipleFilesFileName;
    }
    public void setMultipleFilesFileName(String[] multipleFilesFileName) {
        this.multipleFilesFileName = multipleFilesFileName;
    }
    public String[] getMultipleFilesContentType() {
        return multipleFilesContentType;
    }
    public void setMultipleFilesContentType(String[] multipleFilesContentType) {
        this.multipleFilesContentType = multipleFilesContentType;
    }
    public String[] getMultipleFilesTargetFilePath() {
        return multipleFilesTargetFilePath;
    }
    public void setMultipleFilesTargetFilePath(String[] multipleFilesTargetFilePath) {
        this.multipleFilesTargetFilePath = multipleFilesTargetFilePath;
    }
    public String[] getMultipleFilesTargetFileName() {
        return multipleFilesTargetFileName;
    }
    public void setMultipleFilesTargetFileName(String[] multipleFilesTargetFileName) {
        this.multipleFilesTargetFileName = multipleFilesTargetFileName;
    }

    public String getTargetRelativePath() {
        return targetRelativePath;
    }

    public void setTargetRelativePath(String targetRelativePath) {
        this.targetRelativePath = targetRelativePath;
    }

}
