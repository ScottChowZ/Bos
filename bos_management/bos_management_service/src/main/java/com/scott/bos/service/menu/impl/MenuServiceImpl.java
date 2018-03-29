package com.scott.bos.service.menu.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.MenuRepository;
import com.scott.bos.domain.system.Menu;
import com.scott.bos.service.menu.MenuService;

/**  
 * ClassName:MenuServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午5:17:02 <br/>       
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource(name="menuRepository")
    private MenuRepository menuRepository;
    @Override
    public List<Menu> findLevelOne() {
          
      
        return menuRepository.findByParentMenuIsNull();
    }
    @Override
    public void save(Menu model) {
        if(model.getParentMenu().getId()==null){
            model.setParentMenu(null);
        } 
        
         menuRepository.save(model);
        
    }
    @Override
    public Page<Menu> findAll(Pageable pageable) {
        
         
        return  menuRepository.findAll(pageable);
    }

}
  
