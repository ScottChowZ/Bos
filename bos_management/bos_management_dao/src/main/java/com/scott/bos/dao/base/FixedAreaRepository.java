package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedAreaRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午4:11:23 <br/>       
 */
@Repository("fixedAreaRepository")
public interface FixedAreaRepository extends JpaRepository<FixedArea, Long> {

    
}
  
