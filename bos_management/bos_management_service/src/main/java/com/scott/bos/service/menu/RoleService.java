package com.scott.bos.service.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.Role;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:29:02 <br/>       
 */
public interface RoleService {

    Page<Role> pageQuery(Pageable pageable);

    void save(Role model);

}
  
