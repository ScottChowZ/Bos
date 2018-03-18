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
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.scott.bos.domain.base.SubArea;
import com.scott.bos.service.base.SubareaService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:SubareaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:28:45 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
@Controller("subareaAction")
public class SubareaAction extends CommonAction<SubArea> {
     @Resource(name="subareaService")
    private SubareaService subareaService;
    private SubArea model=getModel(); 
    public SubareaAction() {
          
        super(SubArea.class);  
              
    }
    @Action("subareaAction_save")
    public String save(){
        subareaService.save(model);
        //System.out.println(model.toString());
        return null;
    }
    private int page;
    private int rows;
    public void setPage(int page) {
        this.page = page;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    @Action("subareaAction_pageQuery")
    public String pageQuery() throws Exception{
      /*  Pageable pageable = new PageRequest(page-1, rows);
        Page<SubArea>page=subareaService.pageQuery(pageable);
        long total = page.getTotalElements();
        List<SubArea> content = page.getContent();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", content);
        
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"fixedArea"} );
         String string = JSONObject.fromObject(map, config).toString();
         ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
          ServletActionContext.getResponse().getWriter().write(string);
          */
        
        
        return null;
    }

}
  
