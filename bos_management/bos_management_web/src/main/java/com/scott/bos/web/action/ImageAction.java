package com.scott.bos.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**  
 * ClassName:ImageAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午10:51:33 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("imageAction")
@Scope("prototype")
public class ImageAction extends ActionSupport {
 
    private File imgFile;
    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }
    private String imgFileFileName;
    public void setImgFileFileName(String imgFileFileName) {
        this.imgFileFileName = imgFileFileName;
    }
    @Action("imageAction_upload")
    public String upload() throws Exception{
        String dirPath = "/upload";
        ServletContext servletContext = ServletActionContext.getServletContext();
        String realPath = servletContext.getRealPath(dirPath);//获得在tomcat中图片全路径
        String substring = imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
        String string = UUID.randomUUID().toString().replace("-", "");
        String fileName=string+substring;//生成新名字
        System.out.println(realPath);
        System.out.println(realPath+"/"+fileName);
        File file = new File(realPath+"/"+fileName);//生成新文件
       
            FileUtils.copyFile(imgFile, file);//将旧文件考到新文件
            
          //----------------------------------  
            String path = servletContext.getContextPath();
            Map<String, Object> map = new HashMap<>();
            map.put("error", 0);
            
            map.put("url", path+"/upload/"+fileName);
         String string2 = JSONObject.fromObject(map).toString();
         ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
         ServletActionContext.getResponse().getWriter().write(string2);
        
        
        return null;
    }
}
  
