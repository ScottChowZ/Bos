package com.scott.bos.service.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scott.bos.domain.system.User;

/**  
 * ClassName:UserService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午5:53:24 <br/>       
 */
public interface UserService {

    Page<User> pageQuery(Pageable pageable);

    void save(User model);

}
  
