package com.scott.bos.web.action;

import java.io.IOException;
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
import org.springframework.stereotype.Controller;

import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.service.base.FixedAreaService;
import com.scott.bos.utils.Customer;

import net.sf.json.JSONArray;

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
    @Action("fixedAreaAction_findCustomersNotAssociated")
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
    public String findCustomersAssociated() throws Exception{
      List<Customer> list =(List<Customer>) WebClient.create("http://localhost:8180/CRMA/service/cs/cu")
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
        String string = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        
        ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }

}
  
