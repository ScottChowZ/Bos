package com.scott.bos.fore.web.action;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.base.Area;
import com.scott.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月23日 下午4:08:47 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("orderAction")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

    private Order model;
    private String sendAreaInfo;
    private String recAreaInfo;
    public void setRecAreaInfo(String recAreaInfo) {
        this.recAreaInfo = recAreaInfo;
    }
    public void setSendAreaInfo(String sendAreaInfo) {
        this.sendAreaInfo = sendAreaInfo;
    }
    @Override
    public Order getModel() {
        if(model==null){
            model=new Order();
        }  
          
        return model;
    }
    @Action("orderAction_add")
    public String addOrder(){
        if(sendAreaInfo!=null){
           Area area = new Area();
            String[] split = sendAreaInfo.split("/");
            String province = split[0].substring(0, split[0].length()-1);
            String city = split[1].substring(0, split[1].length()-1);
            String district = split[2].substring(0, split[2].length()-1);
            area.setProvince(province);
            area.setCity(city);
            area.setDistrict(district);
            model.setSendArea(area);
        }
        if(recAreaInfo!=null){
            Area area = new Area();
            String[] split = recAreaInfo.split("/");
            String province = split[0].substring(0, split[0].length()-1);
            String city = split[1].substring(0, split[1].length()-1);
            String district = split[2].substring(0, split[2].length()-1);
            area.setProvince(province);
            area.setCity(city);
            area.setDistrict(district);
            model.setRecArea(area);;
        }
        System.out.println(model.getSendArea().getCity());
        
         WebClient.create("http://localhost:8080/bos_management_web/webService/orderService/saveOrder")
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .post(model);
     
        return null;
    }

}
  
