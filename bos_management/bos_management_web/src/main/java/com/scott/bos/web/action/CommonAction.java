package com.scott.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.base.Courier;
import com.scott.bos.domain.base.Standard;

import net.sf.ehcache.util.ProductInfo;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CommonAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午11:04:51 <br/>       
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T>{
    
   private T model;
  public CommonAction(Class clazz) {
   
      try {
         model = (T) clazz.newInstance();
    } catch (InstantiationException e) {
          
        
        e.printStackTrace();  
        
    } catch (IllegalAccessException e) {
          
        
        e.printStackTrace();  
        
    }   
}
  
  
    @Override
    public T getModel() {
          
         
        return model;
    }
    
    public void page2json(Page<T> page,String[] ex ){
        long total = page.getTotalElements();
        List<T> content = page.getContent();
         Map<String, Object> map = new HashMap<String, Object>();
         
         map.put("total", total);
         map.put("rows", content);
         String string;
         if(ex==null){
             string = JSONObject.fromObject(map).toString();
         }else{
             JsonConfig jsonConfig = new JsonConfig();
             jsonConfig.setExcludes(ex);
             string = JSONObject.fromObject(map,jsonConfig).toString();
         }
         
         ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
         try {
            ServletActionContext.getResponse().getWriter().write(string);
        } catch (IOException e) {
              
            
            e.printStackTrace();  
            
        }
    }

}
  
