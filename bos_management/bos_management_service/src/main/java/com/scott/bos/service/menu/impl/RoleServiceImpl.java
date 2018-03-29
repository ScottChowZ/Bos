package com.scott.bos.service.menu.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.RoleRepository;
import com.scott.bos.domain.system.Role;
import com.scott.bos.service.menu.RoleService;

/**  
 * ClassName:RoleServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:30:04 <br/>       
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
   @Resource(name="roleRepository")
    private RoleRepository roleRepository;
    @Override
    public Page<Role> pageQuery(Pageable pageable) {
          
        
        return roleRepository.findAll(pageable);
    }
    @Override
    public void save(Role model) {
          
        roleRepository.save(model);
        
    }

}
  
