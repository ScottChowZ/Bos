package com.scott.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.Menu;

/**  
 * ClassName:MenuRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午5:26:33 <br/>       
 */
@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Long>{

    List<Menu> findByParentMenuIsNull();
    @Query("select m from Menu m join m.roles r join r.users u where u.id=? and m.parentMenu=null ")
    List<Menu> findMenu(Long id);

   

}
  
