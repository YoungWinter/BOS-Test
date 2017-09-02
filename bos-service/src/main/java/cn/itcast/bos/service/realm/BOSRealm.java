package cn.itcast.bos.service.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	// 认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken UWToken = (UsernamePasswordToken) token;
		// 获得页面输入的用户名
		String username = UWToken.getUsername();
		// 根据用户名查询密码
		User user = userDao.findUserByUsername(username);
		if (user == null) {
			return null;
		}
		// Shiro认证
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}

	// 授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 为用户授权
		info.addStringPermission("staff.list");
		// TODO 根据实际情况获得对应的权限
		return info;
	}

}
