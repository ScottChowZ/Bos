package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.take_delivery.WayBill;

/**  
 * ClassName:WaybillRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午9:44:08 <br/>       
 */
@Repository("waybillRepository")
public interface WaybillRepository extends JpaRepository<WayBill, Long> {

}
  
