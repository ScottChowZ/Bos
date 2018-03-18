package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.SubArea;

/**  
 * ClassName:SubareaRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:52:14 <br/>       
 */
@Repository("subareaRepository")
public interface SubareaRepository extends JpaRepository<SubArea, Long> {

}
  
