package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.Role;

/**  
 * ClassName:RoleRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:35:48 <br/>       
 */
@Repository("roleRepository")
public interface RoleRepository  extends JpaRepository<Role, Long>{

}
  
