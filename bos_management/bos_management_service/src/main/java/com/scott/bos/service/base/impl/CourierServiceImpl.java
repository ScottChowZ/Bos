package com.scott.bos.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scott.bos.dao.base.CourierRepository;
import com.scott.bos.domain.base.Courier;
import com.scott.bos.service.base.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午10:26:33 <br/>       
 */
@Service("courierService")
@Transactional
public class CourierServiceImpl implements CourierService{
    @Resource(name="courierRepository")
    private CourierRepository courierRepository;
  
    public void save(Courier model) {
          
          courierRepository.save(model);
        
    }
public Page<Courier> pageQuery(Specification<Courier> specification,Pageable pageable) {
      
      
    return courierRepository.findAll(specification, pageable);
}
public void batchDel(String ids) {
      
    String[] split = ids.split(",");
    for (String string : split) {
        courierRepository.updateByID(Long.parseLong(string));
    }
    
}
@Override
public List<Courier> findAllCourier() {
      
   
    return courierRepository.findAllCourier();
}

}
  
