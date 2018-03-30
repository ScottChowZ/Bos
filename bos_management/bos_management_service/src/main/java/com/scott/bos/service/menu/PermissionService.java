package com.scott.bos.service.menu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.Permission;
import com.scott.bos.domain.system.User;

/**  
 * ClassName:PermissionService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:27:01 <br/>       
 */
public interface PermissionService {

    Page<Permission> pageQuery(Pageable pageable);

    void save(Permission model);

    List<Permission> findPermission(User user);

}
  
