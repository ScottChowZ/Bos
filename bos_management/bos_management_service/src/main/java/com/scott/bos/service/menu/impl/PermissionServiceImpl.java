package com.scott.bos.service.menu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.PermissionRepository;
import com.scott.bos.domain.system.Permission;
import com.scott.bos.domain.system.User;
import com.scott.bos.service.menu.PermissionService;

/**  
 * ClassName:PermissionServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:27:18 <br/>       
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
   @Resource(name="permissionRepository")
    private PermissionRepository permissionRepository ;
    @Override
    public Page<Permission> pageQuery(Pageable pageable) {
          
         
        return permissionRepository.findAll(pageable);
    }
    @Override
    public void save(Permission model) {
          
        permissionRepository.save(model);
        
    }
    @Override
    public List<Permission> findPermission(User user) {
            
        
        return permissionRepository.findPermission(user.getId());
    }

}
  
