package com.scott.bos.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.StandardRepository;
import com.scott.bos.domain.base.Standard;
import com.scott.bos.service.base.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月13日 下午1:20:51 <br/>       
 */
@Service("standarService")
public class StandardServiceImpl implements StandardService {
   @Resource(name="standardRepository")
    private StandardRepository standardRepository;
    public void save(Standard standard) {

         standardRepository.save(standard);

    }
    public Page<Standard> pageQuery(Pageable pageable) {
          
          
        return standardRepository.findAll(pageable);
    }
    
 public List<Standard> findAll(){
     
     return standardRepository.findAll();
 }
}
  
