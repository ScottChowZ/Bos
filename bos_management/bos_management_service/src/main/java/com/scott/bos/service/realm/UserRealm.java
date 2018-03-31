package com.scott.bos.service.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.scott.bos.dao.base.UserRepository;
import com.scott.bos.domain.system.Permission;
import com.scott.bos.domain.system.Role;
import com.scott.bos.domain.system.User;
import com.scott.bos.service.menu.PermissionService;
import com.scott.bos.service.menu.RoleService;

/**  
 * ClassName:UserRealm <br/>  
 * Function:  <br/>  
 * Date:     2018年3月26日 下午4:54:31 <br/>       
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {
    @Resource(name="userRepository")
    private UserRepository userRepository;
    @Resource(name="roleService")
    private RoleService roleService;
    @Resource(name="permissionService")
    private PermissionService permissionService; 
    @Override//授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
          Subject subject = SecurityUtils.getSubject();
          User user = (User) subject.getPrincipal();
          SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
          if("admin".equals(user.getUsername())){
              Page<Permission> pageQuery = permissionService.pageQuery(null);
              List<Permission> list2 = pageQuery.getContent();
              info.addStringPermission("admin");
              for (Permission permission : list2) {
                info.addStringPermission(permission.getKeyword());
            }
          Page<Role>page =roleService.findAll(null);
           List<Role> list = page.getContent(); 
           
           for (Role role : list) {              
               info.addRole(role.getKeyword());             
        }
          }else{
              
              List<Role>list =roleService.findRole(user);
              
              for (Role role : list) {
                  
                info.addRole("base");
            }
              List<Permission> lis= permissionService.findPermission(user);
              for (Permission permission : lis) {
                info.addStringPermission(permission.getKeyword());
            }
              
          }
         
           
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
  
