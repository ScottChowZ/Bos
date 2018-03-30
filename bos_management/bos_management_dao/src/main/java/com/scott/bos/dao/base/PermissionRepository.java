package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.Permission;

/**  
 * ClassName:PermissionRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午10:30:25 <br/> 
 * select *
  from t_permission p
  join t_role_permission rp
    on p.c_id = rp.c_permission_id
  join t_role r
    on rp.c_role_id = r.c_id
    join t_user_role ur
    on r.c_id=ur.c_role_id
    join t_user u
    on u.c_id=ur.c_user_id
    where u.c_id=661      
 */
@Repository("permissionRepository")
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
    @Query("select p from Permission p join p.roles r join r.users u where u.id=? ")
    List<Permission> findPermission(Long id);

}
  
