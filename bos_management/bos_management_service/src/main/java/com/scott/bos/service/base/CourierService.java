package com.scott.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.scott.bos.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午10:26:17 <br/>       
 */
public interface CourierService {

    void save(Courier model);

    Page<Courier> pageQuery(Specification<Courier> specification, Pageable pageable);

    void batchDel(String ids);

    List<Courier> findAllCourier();

    

    

}
  
