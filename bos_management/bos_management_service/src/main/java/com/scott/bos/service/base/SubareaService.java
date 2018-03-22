package com.scott.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:47:51 <br/>       
 */
public interface SubareaService {

    void save(SubArea model);

    Page<SubArea> pageQuery(Pageable pageable);

    List<SubArea> findByfixedArea();

    List<SubArea> findByfixedAreaNotNull(Long id);

    void savefixareatoSubarea(Long id, Long[] subAreaIds);

   

}
  
