package com.scott.bos.service.menu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.Menu;

/**  
 * ClassName:MenuService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月28日 下午5:16:38 <br/>       
 */
public interface MenuService {

    List<Menu> findLevelOne();

    void save(Menu model);

    Page<Menu> findAll(Pageable pageable);

}
  
