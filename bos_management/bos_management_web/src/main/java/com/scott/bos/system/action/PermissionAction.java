package com.scott.bos.system.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.bouncycastle.jce.provider.JDKDSASigner.stdDSA;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.system.Permission;
import com.scott.bos.service.menu.PermissionService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:PermissionAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:18:36 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("permissionAction")
@Scope("prototype")
public class PermissionAction extends ActionSupport implements ModelDriven<Permission>{
    @Resource(name="permissionService")
  private PermissionService permissionService;
    private Permission model;
    @Override
    public Permission getModel() {
          if(model==null){
              model=new Permission();
          }
         
        return model;
    }
    private int page;
    private int rows;
    public void setPage(int page) {
        this.page = page;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    @Action(value="permissionAction_pageQuery")
    public String pageQuery() throws IOException{
        
       Pageable pageable=new PageRequest(page-1, rows);
      Page<Permission> page=permissionService.pageQuery(pageable);
        
        List<Permission> list = page.getContent();
        long total = page.getTotalElements();
        
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows",list );
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"roles"});
        String string = JSONObject.fromObject(map,config).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }
    @Action(value="permissionAction_save",results={@Result(name="success",type="redirect",location="/pages/system/permission.html")})
    public String save(){
        //System.out.println(model.getName());
        permissionService.save(model);
        return SUCCESS;
    }
}
  
