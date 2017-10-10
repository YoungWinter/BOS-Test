package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.utils.PageBean;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(User user) {
		// 使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(), password);
	}

	/**
	 * 修改密码
	 */
	@Override
	public void editPassword(Integer id, String password) {
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editPassword", password, id);
	}

	/**
	 * 保存用户
	 */
	public void save(User user, String[] roleIds) {
		String password = MD5Utils.md5(user.getPassword());
		user.setPassword(password);
		userDao.save(user);
		if (roleIds != null && roleIds.length > 0) {
			for (String roleId : roleIds) {
				Role role = new Role(roleId);
				user.getRoles().add(role);
			}
		}
	}

	@Override
	public void pageQuery(PageBean<User> pageBean) {
		userDao.pageQuery(pageBean);
	}

}
