package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:30:25 <br/>       
 */
@Repository("permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
  
