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
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.system.Menu;
import com.scott.bos.domain.system.Role;
import com.scott.bos.service.menu.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:RoleAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:23:58 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role> {
      @Resource(name="roleService")
    private RoleService roleService;
    private Role model;
    
    @Override
    public Role getModel() {
          if(model==null){
              model=new Role();
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
   @Action("roleAction_pageQuery")
public String pageQuery() throws Exception{
       
  Pageable pageable  =new PageRequest(page-1, rows);
    Page<Role>page  =roleService.pageQuery(pageable);
     List<Role> list = page.getContent();
     long total = page.getTotalElements();
     Map<String, Object>map=new HashMap<>();
     map.put("total", total);
     map.put("rows", list);
    JsonConfig config = new JsonConfig();
    config.setExcludes(new String[]{"menus","permissions","users"});
     
     String string = JSONObject.fromObject(map,config).toString();
     ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
     ServletActionContext.getResponse().getWriter().write(string);
    return null;
}
   @Action("roleAction_findAll")
   public String findAll() throws Exception{
       Page<Role>page  =roleService.pageQuery(null);
       List<Role> list = page.getContent();
       JsonConfig config = new JsonConfig();
       config.setExcludes(new String[]{"menus","permissions","users"});
        
        String string = JSONArray.fromObject(list,config).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(string);
       return null;
   }
   private String menuIds;
   public void setMenuIds(String menuIds) {
    this.menuIds = menuIds;
}
   @Action(value="roleAction_save",results={@Result(name="success",type="redirect",location="/pages/system/role.html")})
   public String save(){
       String[] split = menuIds.split(",");
       for (String string : split) {
            Menu menu = new Menu();
            menu.setId(Long.parseLong(string));
            model.getMenus().add(menu);
        
    }
       roleService.save(model);
       
       return SUCCESS;
   } 
}
  
