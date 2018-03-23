package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.take_delivery.Order;

/**  
 * ClassName:OrderRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月23日 下午7:32:48 <br/>       
 */
@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {

}
  
