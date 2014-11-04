package org.teng.file.upload;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadAction2 extends ActionSupport{
    
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型

    public String execute() throws Exception {
        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        //D:\apache-tomcat-6.0.18\webapps\struts2_upload\images
        System.out.println("realpath: "+realpath);
        if (image != null) {
            File savefile = new File(new File(realpath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            ActionContext.getContext().put("message", "文件上传成功");
        }
        return "success";
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    
}