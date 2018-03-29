package com.scott.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scott.bos.domain.system.User;

/**  
 * ClassName:UserRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月26日 下午4:28:23 <br/>       
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
     @Query("from User where username=?")
    User findUser(String username);
     
    
   

}
  
