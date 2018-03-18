package com.scott.bos.web.action;

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
import com.scott.bos.domain.base.Standard;
import com.scott.bos.service.base.StandardService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**  
 * ClassName:StandardAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月12日 下午9:44:47 <br/>       
 */
@Controller("standardAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class StandardAction extends ActionSupport implements ModelDriven<Standard>{
private Standard standard;
@Resource(name="standarService")
private  StandardService standarService;
    @Override
    public Standard getModel() {
          if(standard==null){
              standard=new Standard();
          }
         
        return standard;
    }
   @Action(value="saveStandard", results = {@Result(name="success",type="redirect",location="/pages/base/standard.html")})
    public String saveStandard(){
       standarService.save(standard);
        return SUCCESS;
    }
   private int rows;
   private int page;
   public void setPage(int page) {
    this.page = page;
}
  public void setRows(int rows) {
    this.rows = rows;
}
   @Action("standardAction_pageQuery")
   public String pageQuery() throws Exception{
       
       Pageable pageable = new PageRequest(page-1, rows);
       Page<Standard> page=standarService.pageQuery(pageable);
         long total = page.getTotalElements();
         List<Standard> content = page.getContent();
         Map<String, Object> map = new HashMap<String , Object>();
         map.put("total", total);
         map.put("rows", content);
         //System.out.println(map);
         String string  = JSONObject.fromObject(map).toString();
        //System.out.println(string);
         ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
         ServletActionContext.getResponse().getWriter().write(string);
        
       
       return null;
   }
   @Action("standard_findAll")
   public String findAll() throws Exception{
      List<Standard> list = standarService.findAll();
     String json = JSONArray.fromObject(list).toString();
      //System.out.println(json);
     ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
     ServletActionContext.getResponse().getWriter().write(json);
       return null;
   }

}
  
