package com.scott.bos.service.base.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.SubareaRepository;
import com.scott.bos.domain.base.SubArea;
import com.scott.bos.service.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:48:18 <br/>       
 */
@Service("subareaService")
public class SubareaServiceImpl implements SubareaService {
    @Resource(name="subareaRepository")
    private SubareaRepository subareaRepository;
    public void save(SubArea model) {
          subareaRepository.save(model);
       
        
    }
    public Page<SubArea> pageQuery(Pageable pageable) {
          
        
        return subareaRepository.findAll(pageable);
    }

    
    
}
  
