package com.scott.bos.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.take_delivery.WayBill;
import com.scott.bos.service.base.WaybillService;

/**  
 * ClassName:WaybillAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午9:28:47 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("waybillAction")
@Scope("prototype")
public class WaybillAction extends ActionSupport implements ModelDriven<WayBill>{
    @Resource(name="waybillService")
    private WaybillService waybillService;
   private WayBill model;
    @Override
    public WayBill getModel() {
          if(model==null){
              model=new WayBill();
          }
      
        return model;
    }
    @Action("waybillAction_save")
    public String saveWayBill(){
       waybillService.saveWayBill(model);
        
        return null;
    }
}
  
