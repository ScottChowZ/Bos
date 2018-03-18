package com.scott.bos.service.base.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.AreaDAO;
import com.scott.bos.domain.base.Area;
import com.scott.bos.service.base.AreaService;

/**  
 * ClassName:AreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午9:21:26 <br/>       
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService {
    @Resource(name="areaDAO")
    private AreaDAO areaDAO;
    public void save(ArrayList<Area> list) {
          
         areaDAO.save(list) ;
        
    }
    public Page<Area> pageQuery(Pageable pageables) {
          
        
        return areaDAO.findAll(pageables);
    }
    public List<Area> findAll(String q) {
          
         q="%"+q+"%";
        return areaDAO.finAll(q);
    }
   
   

}
  
