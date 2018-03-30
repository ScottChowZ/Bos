package com.scott.bos.service.menu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.Role;
import com.scott.bos.domain.system.User;

/**  
 * ClassName:RoleService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:29:02 <br/>       
 */
public interface RoleService {

    Page<Role> pageQuery(Pageable pageable);

    void save(Role model);

    Page<Role> findAll(Pageable pageable);

    List<Role> findRole(User user);

}
  
