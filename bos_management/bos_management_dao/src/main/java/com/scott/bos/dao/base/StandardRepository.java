package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.base.Standard;

/**  
 * ClassName:StandardRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月12日 下午10:47:33 <br/>       
 */
@Repository("standardRepository")
public interface StandardRepository extends JpaRepository<Standard, Long> {

}
  
