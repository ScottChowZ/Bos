package com.scott.bos.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.TakeTimeRepository;
import com.scott.bos.domain.base.TakeTime;
import com.scott.bos.service.base.TakeTimeService;

/**  
 * ClassName:TakeTimeServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午7:14:18 <br/>       
 */
@Service("takeTimeService")
public class TakeTimeServiceImpl implements TakeTimeService {
   @Resource(name="takeTimeRepository")
   private TakeTimeRepository takeTimeRepository;
    @Override
    public List<TakeTime> findAlltime() {
         
        
        return  takeTimeRepository.findAll();
    }

}
  
