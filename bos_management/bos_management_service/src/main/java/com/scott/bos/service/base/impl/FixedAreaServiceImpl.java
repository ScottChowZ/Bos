package com.scott.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scott.bos.dao.base.CourierRepository;
import com.scott.bos.dao.base.FixedAreaRepository;
import com.scott.bos.dao.base.TakeTimeRepository;
import com.scott.bos.domain.base.Courier;
import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.domain.base.TakeTime;
import com.scott.bos.service.base.FixedAreaService;

/**  
 * ClassName:FixedAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午4:08:20 <br/>       
 */
@Service("fixedAreaService")
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
    //@Resource(name="fixedAreaRepository")
    @Autowired
private FixedAreaRepository fixedAreaRepository;
    //@Resource(name="courierRepository")
    @Autowired
    private CourierRepository courierRepository;
   // @Resource(name="takeTimeRepository")
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    @Override
    public void save(FixedArea model) {
          
         fixedAreaRepository.save(model);
        
    }
    @Override
    public Page<FixedArea> findFixedArea(Pageable pageable) {
          
       
        return fixedAreaRepository.findAll(pageable);
    }
    @Override
    public void saveCourierToFixed(Long courierId, Long taketimeId, Long id) {
        System.out.println("1");
        Courier courier = courierRepository.findOne(courierId);
        TakeTime takeTime = takeTimeRepository.findOne(taketimeId);
         FixedArea fixedArea = fixedAreaRepository.findOne(id);
         courier.setTakeTime(takeTime);//时间快递员关联
         fixedArea.getCouriers().add(courier);
        
    }

}
  
