package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.UserDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findUserByUsernameAndPassword(String username,
			String password) {
		User user = null;
		String hql = " from User u where u.username=? and u.password=? ";
		List<User> userList = (List<User>) getHibernateTemplate().find(hql,
				username, password);
		if (userList != null && userList.size() > 0) {
			user = userList.get(0);
		}
		return user;
	}

}
