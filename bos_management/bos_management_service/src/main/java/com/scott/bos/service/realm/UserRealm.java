package com.scott.bos.service.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.scott.bos.dao.base.UserRepository;
import com.scott.bos.domain.system.User;

/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     2018年3月26日 下午4:54:31 <br/>       
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {
    @Resource(name="userRepository")
    private UserRepository userRepository;
    @Override//授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
          SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addStringPermission("ee");
            info.addStringPermission("courier_pageQuery");
            info.addRole("admin");
        return info;
    }

    @Override//认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken  =(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
       User user =userRepository.findUser(username);
      
      if(user!=null){
          AuthenticationInfo AuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
              
          return AuthenticationInfo;
      }
       
        return null;
    }

}
  
