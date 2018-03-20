package cn.x5456.bos.web.realm;

import cn.x5456.bos.dao.IUserDao;
import cn.x5456.bos.domain.TUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userDao;

    //认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        System.out.println("realm中的认证方法执行了。。。。");
        UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
        String username = mytoken.getUsername();
        //根据用户名查询数据库中的密码
        TUser user = userDao.findUserByUserName(username);
        if (user == null) {
            //用户名不存在
            return null;
        }
        //如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }

    //授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取用户对象
        TUser user1 = (TUser) SecurityUtils.getSubject().getPrincipal();
        TUser user2 = (TUser) principals.getPrimaryPrincipal();
        System.out.println(user1 == user2);

        // TODO 根据用户对象查询数据库进行授权
        // 直接（不查数据库）为用户授权
        info.addStringPermission("staff-list");

        return info;
    }
}