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
import com.scott.bos.service.menu.MenuService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:MenuAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午5:01:08 <br/>       
 */
@Namespace("/")
@Controller
@ParentPackage("struts-default")
@Scope("prototype")
public class MenuAction extends ActionSupport implements ModelDriven<Menu>{
    private Menu model;
    @Resource(name="menuService")
   private MenuService menuService;

    @Override
    public Menu getModel() {
         if(model==null){
             model=new Menu();
         } 
         
        return model;
    }
    
    @Action("menuAction_findLevelOne")
    public String findLevelOne() throws Exception{
       
     List<Menu> list = menuService.findLevelOne();
     JsonConfig config = new JsonConfig();
     config.setExcludes(new String[]{"childrenMenus","roles","parentMenu"});
     String string = JSONArray.fromObject(list,config).toString();
    ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
    ServletActionContext.getResponse().getWriter().write(string);                     
        return null;
    }
    @Action(value="menuAction_save",results={@Result(name="success",type="redirect",location="/pages/system/menu.html")})
    public String save(){
        System.out.println(model.toString());
        menuService.save(model);
        
        return SUCCESS;
    } 
    private int rows;
    public void setRows(int rows) {
        this.rows = rows;
    }
    @Action("menuAction_pageQuery")
    public String pageQuery() throws Exception{
     Pageable pageable = new PageRequest(Integer.parseInt(model.getPage())-1, rows);
    Page<Menu> page =menuService.findAll(pageable);
    List<Menu> list = page.getContent();
    long total = page.getTotalElements();
    Map<String,Object>map=new HashMap<>();
    System.out.println(list.size());
    map.put("total", total);
    map.put("rows", list);
   JsonConfig config = new JsonConfig();
   config.setExcludes(new String[]{"parentMenu","roles","childrenMenus"});
   String string = JSONObject.fromObject(map,config).toString();
   ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
   ServletActionContext.getResponse().getWriter().write(string);
        
        return null;
    }
    @Action("menuAction_findMenu")
    public String findMenu() throws Exception{
        List<Menu>list=menuService.findMenu();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"parentMenu","roles","childrenMenus"});
        String string = JSONArray.fromObject(list,config).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }
}
  
