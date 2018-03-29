package com.scott.bos.web.action;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.system.User;


/**  
 * ClassName:UserAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月26日 下午3:50:15 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User model;
    private String code;
   
    public void setCode(String code) {
        this.code = code;
    }
    @Override
    public User getModel() {
        if(model==null){
            model=new User();
                }  

        return model;
    }
    @Action(value="userAction_login",results={@Result(name="success",type="redirect",location="/index.html"),
    @Result(name="login",type="redirect",location="/login.html")
    })
   public String login(){
   
         Object attribute = ServletActionContext.getRequest().getSession().getAttribute("validateCode");  
       
               Subject subject = SecurityUtils.getSubject();
               UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());                                                                                                                             
                         
                                try {
                                    subject.login(token);
                                       User user = (User) subject.getPrincipal();
                                       return SUCCESS;
                                } catch (UnknownAccountException e) {
                                      
                                    System.out.println("账号错误");
                                    e.printStackTrace(); 
                                    
                                    
                                } catch (IncorrectCredentialsException  e) {
                                    
                                  System.out.println("密码错误");
                                  e.printStackTrace(); 
                                  
                                  
                              }
                                                                                                                                    
               return "login";
   }
    @Action(value="userAction_logout",results={@Result(name="success",type="redirect",location="/login.html")})
    public String logout(){
        
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//注销,清空session
       ServletActionContext.getRequest().getSession().removeAttribute("user");
        
        return SUCCESS;
    }
    @RequiresPermissions("ee")
    @Action(value="te",results={@Result(name="success",type="redirect",location="/index.html")})
    public String te(){
        
        return SUCCESS;
    }
    
}
  
