package com.scott.bos.service.base.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.FixedAreaRepository;
import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.service.base.FixedAreaService;

/**  
 * ClassName:FixedAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午4:08:20 <br/>       
 */
@Service("fixedAreaService")
public class FixedAreaServiceImpl implements FixedAreaService {
    @Resource(name="fixedAreaRepository")
private FixedAreaRepository fixedAreaRepository;
    @Override
    public void save(FixedArea model) {
          
         fixedAreaRepository.save(model);
        
    }

}
  
