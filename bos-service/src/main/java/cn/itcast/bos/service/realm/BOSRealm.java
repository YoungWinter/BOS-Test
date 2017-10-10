package cn.itcast.bos.service.realm;

import java.util.List;

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
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.bos.dao.FunctionDao;
import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;

public class BOSRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FunctionDao functionDao;

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
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获取当前登陆用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 为用户授权
		List<Function> list = null;
		if (user.getUsername().equals("admin")) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			list = functionDao.findByCriteria(detachedCriteria);
		} else {
			list = functionDao.findFunctionListByUserId(user.getId());
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}

}
