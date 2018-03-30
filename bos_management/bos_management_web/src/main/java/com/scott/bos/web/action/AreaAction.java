package com.scott.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
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
import com.scott.bos.utils.FileDownloadUtils;
import com.scott.bos.utils.PinYin4jUtils;

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
    
    @Action("areaAction_exportExcel")
    public String exportExcel() throws Exception{
        Page<Area> pageQuery = areaService.pageQuery(null);
        List<Area> list = pageQuery.getContent();
       
        HSSFWorkbook workbook = new HSSFWorkbook();//创建文件
          HSSFSheet sheet = workbook.createSheet();//创建工作簿
          HSSFRow row = sheet.createRow(0);//创建第一行
          
          row.createCell(0).setCellValue("省");//创建头列并赋值
          row.createCell(1).setCellValue("市");
          row.createCell(2).setCellValue("区");
          row.createCell(3).setCellValue("邮编");
          row.createCell(4).setCellValue("简码");
          row.createCell(5).setCellValue("城市编码");
          for (Area area : list) {//创建数据列
              int lastRowNum = sheet.getLastRowNum();
              HSSFRow createRow = sheet.createRow(lastRowNum+1);
              createRow.createCell(0).setCellValue(area.getProvince());
              createRow.createCell(1).setCellValue(area.getCity());
              createRow.createCell(2).setCellValue(area.getDistrict());
              createRow.createCell(3).setCellValue(area.getPostcode());
              createRow.createCell(4).setCellValue(area.getShortcode());
              createRow.createCell(5).setCellValue(area.getCitycode());
          }
        //--------------------------------------两头一流
        
          
          String filename="区域数据统计.xls";
        HttpServletResponse response = ServletActionContext.getResponse();
        ServletOutputStream outputStream = response.getOutputStream();//输出流
        HttpServletRequest request = ServletActionContext.getRequest();
        String header = request.getHeader("User-Agent");//一头,获取浏览器的类型
        
        ServletContext servletContext = ServletActionContext.getServletContext();
        // 先获取mimeType再重新编码,避免编码后后缀名丢失,导致获取失败
        String mimeType = servletContext.getMimeType(filename);//二头,获取mimeType
        
        filename = FileDownloadUtils.encodeDownloadFilename(filename, header);// 对文件名重新编码,不从新编码,文件没名,格式也不对

        response.setContentType(mimeType);//设置信息头
        response.setHeader("Content-Disposition","attachment; filename=" + filename);
        
           workbook.write(outputStream);//将文件写出去
           workbook.close();
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
  
