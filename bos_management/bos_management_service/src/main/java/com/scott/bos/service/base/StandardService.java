package com.scott.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月13日 下午1:17:14 <br/>       
 */
public interface StandardService {
      
    void save(Standard standard);

    Page<Standard> pageQuery(Pageable pageable);
    List<Standard> findAll();

}
  
