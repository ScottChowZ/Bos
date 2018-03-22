package com.scott.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.scott.bos.domain.base.TakeTime;
import com.scott.bos.service.base.TakeTimeService;

import net.sf.json.JSONArray;

/**  
 * ClassName:TakeTimeAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午5:58:27 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("takeTimeAction")
@Scope("prototype")
public class TakeTimeAction extends CommonAction<TakeTime> {
    @Resource(name="takeTimeService")
    private TakeTimeService takeTimeService;
    private TakeTime model=getModel();
    public TakeTimeAction() {
          
        super(TakeTime.class);  
     
        
    }
    @Action("takeTimeAction_findTime")
    public String findTime() throws IOException{
        
      List<TakeTime>list=takeTimeService.findAlltime();
      String string = JSONArray.fromObject(list).toString();
      ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
      ServletActionContext.getResponse().getWriter().write(string);
        return null;
    }
    

}
  
