package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.Role;

/**  
 * ClassName:RoleRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午4:35:48 <br/>    
 *     select *
      from t_role r
      join t_user_role ur
        on r.c_id = ur.c_role_id
      join t_user u
        on u.c_id = ur.c_user_id
   HQL语句不需要写on
 *    select r from Role r join r.users u  where u.id=?
 */
@Repository("roleRepository")
public interface RoleRepository  extends JpaRepository<Role, Long>{
    @Query("select r from Role r join r.users u  where u.id=? ")
    List<Role> findRole(Long id);

}
  
