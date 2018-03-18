package com.scott.bos.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.CDATA;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.base.Courier;
import com.scott.bos.domain.base.Standard;
import com.scott.bos.service.base.CourierService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:CourierAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:55:58 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("courierAction")
@Scope("prototype")
public class CourierAction extends CommonAction<Courier> {

    public CourierAction() {
          
        super(Courier.class);  
        
        
    }
   
    @Resource(name="courierService")
    private CourierService courierService;
   private Courier model=getModel();
  
    @Action(value="courierAction_save",results={@Result(name="success",type="redirect",location="/pages/base/courier.html")})
    public String save(){
        model.setDeltag(new Character('0'));
        courierService.save(model);
        
        return SUCCESS;
    }
    private int page;
    private int rows;
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setPage(int page) {
        this.page = page;
    }
    @Action("courierAction_pageQuery")
    public String pageQuery() throws Exception{
       
      Specification<Courier> specification  =new Specification<Courier>() {

            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                  
                String courierNum = model.getCourierNum();//工号
                Standard standard = model.getStandard();//收派标准
                String company = model.getCompany();//所属公司
                String type = model.getType();//类型
                ArrayList<Predicate> list = new ArrayList<>();
                if(StringUtils.isNotEmpty(courierNum )){
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courierNum );
                    list.add(p1);
                }
                
                if(StringUtils.isNotEmpty(company )){
                 
                    Predicate p2 = cb.like(root.get("company").as(String.class), "%"+company+"%" );
                    list.add(p2);
                }
                
                if(StringUtils.isNotEmpty(type )){
                    Predicate p3 = cb.equal(root.get("type").as(String.class), type);
                    list.add(p3);
                    
                }
                if(standard!=null){
                    String name = standard.getName();
                    if(StringUtils.isNotEmpty(name)){
                        //连表查询
                        Join<Object, Object> join = root.join("standard");
                        Predicate p4 = cb.equal(join.get("name").as(String.class ), name);
                        list.add(p4);
                    }
                }
                Predicate[] arr  =new Predicate[list.size()];
                list.toArray(arr);
                
                
                return cb.and(arr);
            }};
        
        Pageable pageable= new PageRequest(page-1, rows);
        Page<Courier>page=courierService.pageQuery(specification,pageable);
        page2json(page, new String[]{"fixedAreas", "takeTime"});
        /*long total = page.getTotalElements();
        List<Courier> content = page.getContent();
         Map<String, Object> map = new HashMap<String, Object>();
         
         map.put("total", total);
         map.put("rows", content);
         JsonConfig jsonConfig = new JsonConfig();
         jsonConfig.setExcludes(new String[]{"fixedAreas", "takeTime"});
         String string = JSONObject.fromObject(map,jsonConfig).toString();
         ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
         ServletActionContext.getResponse().getWriter().write(string);*/
        return null;
    }
    private String ids;
    public void setIds(String ids) {
        this.ids = ids;
    }
    @Action(value="courierAction_batchDel",results={@Result(name="success",type="redirect",location="/pages/base/courier.html")})
    public String batchDel(){
        
       courierService.batchDel(ids);
        return SUCCESS;
    }

      

}
  
