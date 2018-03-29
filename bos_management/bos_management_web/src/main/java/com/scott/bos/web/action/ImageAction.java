package com.scott.bos.web.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**  
 * ClassName:ImageAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午10:51:33 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("imageAction")
@Scope("prototype")
public class ImageAction extends ActionSupport {
 
}
  
