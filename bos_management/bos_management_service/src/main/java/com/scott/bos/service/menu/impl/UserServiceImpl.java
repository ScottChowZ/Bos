package com.scott.bos.service.menu.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.scott.bos.dao.base.UserRepository;
import com.scott.bos.domain.system.User;
import com.scott.bos.service.menu.UserService;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月29日 下午5:53:36 <br/>       
 */
@Component("userService")
public class UserServiceImpl implements UserService {
    @Resource(name="userRepository")
    private UserRepository userRepository;
    @Override
    public Page<User> pageQuery(Pageable pageable) {
          
          
        return userRepository.findAll(pageable);
    }
    @Override
    public void save(User model) {
          
        userRepository.save(model);
        
    }

}
  
