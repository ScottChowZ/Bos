package com.scott.bos.service.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.Permission;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:27:01 <br/>       
 */
public interface PermissionService {

    Page<Permission> pageQuery(Pageable pageable);

    void save(Permission model);

}
  
