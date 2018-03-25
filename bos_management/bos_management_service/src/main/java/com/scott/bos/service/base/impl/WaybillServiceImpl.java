package com.scott.bos.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.WaybillRepository;
import com.scott.bos.domain.take_delivery.WayBill;
import com.scott.bos.service.base.WaybillService;

/**  
 * ClassName:WaybillServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月25日 下午9:43:23 <br/>       
 */
@Service("waybillService")
public class WaybillServiceImpl implements WaybillService {
    @Resource(name="waybillRepository")
    private WaybillRepository waybillRepository;

    @Override
    public void saveWayBill(WayBill model) {
          
        waybillRepository.save(model); 
        
    }

}
  
