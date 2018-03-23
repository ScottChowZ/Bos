package com.scott.bos.dao.base;

import javax.annotation.Resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.take_delivery.WorkBill;

/**  
 * ClassName:WorkbillRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月23日 下午9:18:00 <br/>       
 */
@Repository("workbillRepository")
public interface WorkbillRepository extends JpaRepository<WorkBill, Long>{

}
  
