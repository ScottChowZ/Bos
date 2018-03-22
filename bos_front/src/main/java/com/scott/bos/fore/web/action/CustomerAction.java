package com.scott.bos.fore.web.action;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.aliyuncs.exceptions.ClientException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.fore.domain.Customer;
import com.scott.bos.utils.MailUtils;
import com.scott.bos.utils.SmsUtils;

import oracle.jdbc.util.Login;

/**  
 * ClassName:CustomerAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月22日 下午3:40:13 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer model;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public Customer getModel() {
          if(model==null){
              model=new Customer();
          }
         
        return model;
    }

    
    @Action("loginForm")
    public String Login(){
        String  code = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
      
        if(checkcode!=null&&checkcode.equals(code)){
         
      Customer customer = (Customer) WebClient.create("http://localhost:8180/CRMA/service/cs/login")
        .query("tel", model.getTelephone())
        .query("password", model.getPassword())
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .getCollection(Customer.class);
      System.out.println(customer.getId());
      
      }
        return null;
    }
    
 // 发送验证码
    @Action(value = "customerAction_sendSMS")
    public String sendSMS() {
       String code = RandomStringUtils.randomNumeric(6);//数字表示几位数
       ServletActionContext.getRequest().getSession().setAttribute("code", code);
       ServletActionContext.getRequest().getSession().setAttribute("tel", model.getTelephone());
       try {
           System.out.println(model.getTelephone());
        SmsUtils.sendSms("16675181252", code);
    } catch (ClientException e) {
          
         
        e.printStackTrace();  
        
    }
        return null;
    }
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    @Action("customerAction_regist")
    public String regist(){//注册
      /*  String code = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
        String tel= (String) ServletActionContext.getRequest().getSession().getAttribute("tel");
       if(checkcode!=null&&code.equals(checkcode)&&model.getTelephone().equals(tel)){
           WebClient.create("http://localhost:8180/CRMA/service/cs/saveCustomer")
           .type(MediaType.APPLICATION_JSON)
           .accept(MediaType.APPLICATION_JSON)
           .post(model);
           
           return null;
       }*/
      String mailcode = RandomStringUtils.randomNumeric(6);
      System.out.println(mailcode);
       redisTemplate.opsForValue().set(model.getTelephone(), mailcode, 1, TimeUnit.DAYS);//将验证码放入redis
      
        String emailBody="感谢您注册网址:<a href='http://localhost:8280/bos_front/customerAction_active.action?tel="+model.getTelephone()+"&mailCode="+mailcode+"'>激活</a>";
        //key为电话值为邮件码
        System.out.println(emailBody);
       MailUtils.sendMail(model.getEmail(), "激活邮件", emailBody);//邮件地址,主题,内容
        WebClient.create("http://localhost:8180/CRMA/service/cs/saveCustomer")
        .type(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .post(model);
        
        return null;
    }
    private String tel;
    private String mailCode;
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }
    
    @Action(value="customerAction_active",results={@Result(name="success",type="redirect",location="/active_success.html"),
    @Result(name="error",type="redirect",location="/active_error.html")})
    public String  active(){
        String code = redisTemplate.opsForValue().get(tel);
        
        if(mailCode.equals(code)){
     WebClient.create("http://localhost:8180/CRMA/service/cs/active")
     .query("tel", tel)
     .type(MediaType.APPLICATION_JSON)
     .accept(MediaType.APPLICATION_JSON)
     .put(null);
     return SUCCESS;
     }
        
        return ERROR;
    }
}
  
