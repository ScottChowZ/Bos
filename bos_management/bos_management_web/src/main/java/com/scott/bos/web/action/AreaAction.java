package com.scott.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.scott.bos.domain.base.Area;
import com.scott.bos.service.base.AreaService;
import com.scott.bos.utils.PinYin4jUtils;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午7:50:26 <br/>       
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller("areaAction")
@Scope("prototype")
public class AreaAction extends ActionSupport implements ModelDriven<Area>  {

    /**  
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
     * @since JDK 1.6  
     */
    @Resource(name="areaService")
private  AreaService areaService;

    private Area model;
    @Override
    public Area getModel() {
         if(model==null){
             model=new Area();
         } 
         
        return model;
    }
private File areaFile;
public void setAreaFile(File areaFile) {
    this.areaFile = areaFile;
}
    
    @Action("areaAction_importXLS")
    public String importXLS(){
       try {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(areaFile));
        //加载文件
        HSSFSheet sheetAt = workbook.getSheetAt(0);
        ArrayList<Area> list = new ArrayList<>();//表中有多组Area对象数据
        //读取文件中第一个工作簿的内容
        for (Row row : sheetAt) {//遍历按行读
            if(row.getRowNum()==0){//如果是第一行,第一行为表头
                continue;
            }
            String id = row.getCell(0).getStringCellValue();//第一列
            String province = row.getCell(1).getStringCellValue();//第二列
            String city = row.getCell(2).getStringCellValue();//第三列
            String district = row.getCell(3).getStringCellValue();//第四列
            String postcode = row.getCell(4).getStringCellValue();//第五列
            province=province.substring(0,province.length()-1);
            city=city.substring(0, city.length()-1);
            district=district.substring(0, district.length()-1);
            String[] string = PinYin4jUtils.getHeadByString(province+city+district);
            
            String shortcode = StringUtils.join(string).toUpperCase();
            String citycode = PinYin4jUtils.hanziToPinyin(city, "").toUpperCase();
            Area area = new Area();
            
            area.setProvince(province);
            area.setCity(city);
            area.setDistrict(district);
            area.setPostcode(postcode);
            area.setCitycode(citycode);
            area.setShortcode(shortcode);
            list.add(area);
        }
        
        areaService.save(list);
    } catch (Exception e) {
          
        // TODO Auto-generated catch block  
        e.printStackTrace();  
        
    } 
        
        
        return null;
        
    }
    private int page;
    private int rows;
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setPage(int page) {
        this.page = page;
    }
    @Action("areaAction_pageQuery" )
    public String pageQuery() throws IOException{
      
        
       
           
            
       
           Pageable pageables = new PageRequest(page-1, rows);
           Page<Area> page=areaService.pageQuery(pageables);
           long total = page.getTotalElements();
           List<Area> content = page.getContent();
           
           HashMap<String, Object> map = new HashMap<>();
           map.put("total", total);
           map.put("rows", content);
           JsonConfig config = new JsonConfig();
           config.setExcludes(new String[]{"subareas"});
           String string = JSONObject.fromObject(map,config).toString();
        
           ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
           ServletActionContext.getResponse().getWriter().write(string);
        
        return null;
        
    }
    private String  q;
    public void setQ(String q) {
        this.q = q;
    }
    @Action("areaAction_findAll")
    public String findAll() throws IOException{
        List<Area> list=null;
        if(q!=null){
            list=areaService.findAll(q);
        }else{
            Page<Area> pageQuery = areaService.pageQuery(null);
             list = pageQuery.getContent();
        }
        
        
        
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subareas"});
        String string = JSONArray.fromObject(list,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().write(string);
        
        return null;
    }
}
  
