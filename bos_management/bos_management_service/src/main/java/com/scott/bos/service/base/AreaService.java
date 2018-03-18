package com.scott.bos.service.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.base.Area;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午9:20:51 <br/>       
 */
public interface AreaService {

    void save(ArrayList<Area> list);

    Page<Area> pageQuery(Pageable pageables);

    List<Area> findAll(String q);

  
    

}
  
