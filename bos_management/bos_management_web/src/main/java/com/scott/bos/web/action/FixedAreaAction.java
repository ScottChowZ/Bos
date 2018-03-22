package com.scott.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
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

import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.service.base.FixedAreaService;
import com.scott.bos.utils.Customer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:FixedAreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午3:40:25 <br/>       
 */
@ParentPackage("struts-default")
@Namespace("/")
@Controller("fixedAreaAction")
@Scope("prototype")
public class FixedAreaAction extends CommonAction<FixedArea>{
    @Resource(name="fixedAreaService")
    private FixedAreaService fixedAreaService;
  private FixedArea model=getModel();
  
    public FixedAreaAction() {
          
        super(FixedArea.class);  
          
        
    }
    @Action(value="fixedAreaAction_save",results={@Result(name="success",type="redirect",location="/pages/base/fixed_area.html")})
    public String save(){
       fixedAreaService.save(model);
        return SUCCESS;
    }
    
    @Action("fixedAreaAction_findCustomersNotAssociated")//查找无关联客户
    public  String findCustomersNotAssociated() throws IOException{
        List<Customer> list = (List<Customer>) WebClient.create(
                "http://localhost:8180/CRMA/service/cs/customer")// 指定请求地址
                .accept(MediaType.APPLICATION_JSON)// 指定返回值的数据格式
                .getCollection(Customer.class);// 指定请求方式和返回值类型
       String string = JSONArray.fromObject(list).toString();
        
          ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
           
          ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }
    @Action("fixedAreaAction_findCustomersAssociated")
    public String findCustomersAssociated() throws Exception{//查找已关联客户
       
      List<Customer> list =(List<Customer>) WebClient.create("http://localhost:8180/CRMA/service/cs/cu")
         .query("fixedAreaId", model.getId())
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
        String string = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        
        ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }
    private Long[] customerIds;
    public void setCustomerIds(Long[] customerIds) {
        this.customerIds = customerIds;
    }
    @Action(value="fixedAreaAction_assignCustomersFixedArea",results={@Result(name="success",type="redirect",location="/pages/base/fixed_area.html")})
    public  String assignCustomersUpdate(){//取消客户关联
        if(customerIds!=null){
            WebClient.create("http://localhost:8180/CRMA/service/cs/save").accept(MediaType.APPLICATION_JSON)
            .query("id",customerIds )//
            .query("fixedAreaId", model.getId())
            .type(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .put(null);  
        }else{
            WebClient.create("http://localhost:8180/CRMA/service/cs/save").accept(MediaType.APPLICATION_JSON)
            
            .query("fixedAreaId", model.getId())
            .type(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .put(null);
        }
      
        
        return SUCCESS;
    }
   private int page;
   private int rows;
   public void setPage(int page) {
    this.page = page;
}
   public void setRows(int rows) {
    this.rows = rows;
}
    @Action("fixedAreaAction_findFixedArea")
  public String  findFixedArea() throws Exception{
      Pageable pageable = new PageRequest(page-1, rows);
      Page<FixedArea> page=fixedAreaService.findFixedArea(pageable);
      long total = page.getTotalElements();
      List<FixedArea> list = page.getContent();
      JsonConfig config = new JsonConfig();
      config.setExcludes(new String[]{"couriers","subareas"});
      HashMap<String, Object> map = new HashMap<>();
      map.put("total", total);
      map.put("rows", list);
     String string = JSONObject.fromObject(map,config).toString();
      System.out.println(string);
      ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
      ServletActionContext.getResponse().getWriter().write(string);
      return null;
  }
    private Long courierId;
    private Long taketimeId;
    public void setTaketimeId(Long taketimeId) {
        this.taketimeId = taketimeId;
    }
    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }
    @Action(value="fixedAreaAction_associationCourierToFixedArea",results={@Result(name="success",type="redirect",location="/pages/base/fixed_area.html")})
    public String saveCourierToFixed(){
        fixedAreaService.saveCourierToFixed(courierId,taketimeId,model.getId());
        return SUCCESS;
    }
}
  
