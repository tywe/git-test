package org.teng.file.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String username;

    private String password;

    private List<File> file;

    private List<String> fileFileName;

    private List<String> fileContentType;

    private List<String> dataUrl;
    

    @Override
    public String execute() throws Exception {
        dataUrl = new ArrayList<String>();
        String imgpath = "upload/";
        String path = null;
        Object userName = ServletActionContext.getRequest().getAttribute("userName");
        Object files = ServletActionContext.getRequest(
        		).getAttribute("file");
        if(file == null || file.size() == 0){
        	System.out.println("file not received");
        }
        
        
        for (int i = 0; i < file.size(); ++i) {
            InputStream is = new FileInputStream(file.get(i));
            path = ServletActionContext.getServletContext().getRealPath("/");
            System.out.println("=====imgpath=" + path);

            dataUrl.add(imgpath+this.getFileFileName().get(i));
            File destFile = new File(path+imgpath, this.getFileFileName().get(i));

            OutputStream os = new FileOutputStream(destFile);

            byte[] buffer = new byte[400];

            int length = 0;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

            is.close();

            os.close();
        }
        return SUCCESS;
    }

    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getDataUrl() {
        return dataUrl;
    }




    public void setDataUrl(List<String> dataUrl) {
        this.dataUrl = dataUrl;
    }




    public void setPassword(String password) {
        this.password = password;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }
}