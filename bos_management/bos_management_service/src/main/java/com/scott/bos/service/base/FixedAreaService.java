package com.scott.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedAreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午4:07:57 <br/>       
 */
public interface FixedAreaService {

    void save(FixedArea model);

    Page<FixedArea> findFixedArea(Pageable pageable);

    void saveCourierToFixed(Long courierId, Long taketimeId, Long id);

}
  
