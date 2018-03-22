package com.scott.bos.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scott.bos.dao.base.FixedAreaRepository;
import com.scott.bos.dao.base.SubareaRepository;
import com.scott.bos.domain.base.FixedArea;
import com.scott.bos.domain.base.SubArea;
import com.scott.bos.service.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月16日 下午5:48:18 <br/>       
 */
@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements SubareaService {
    @Resource(name="subareaRepository")
    private SubareaRepository subareaRepository;
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    public void save(SubArea model) {
          subareaRepository.save(model);
       
        
    }
    public Page<SubArea> pageQuery(Pageable pageable) {
          
        
        return subareaRepository.findAll(pageable);
    }
    @Override
    public List<SubArea> findByfixedArea() {
          
          
        return subareaRepository.findByfixedAreaIsNull();
    }
    @Override
    public List<SubArea> findByfixedAreaNotNull(Long id) {
        FixedArea fixedArea = fixedAreaRepository.findOne(id);   
       
        return subareaRepository.findByfixedAreaNotNull(fixedArea);
    }
    @Override
    public void savefixareatoSubarea(Long id, Long[] subAreaIds) {
        FixedArea fixedArea = fixedAreaRepository.findOne(id); 
        subareaRepository.delByfix(fixedArea );
        if(subAreaIds!=null){
        for (int i=0;i<subAreaIds.length;i++) {
            subareaRepository.savefixareatoSubarea(fixedArea,subAreaIds[i]);
        }
        }
        
    }

    
    
}
  
