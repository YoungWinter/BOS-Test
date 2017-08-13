package cn.itcast.bos.dao;

import cn.itcast.bos.dao.base.BaseDao;
import cn.itcast.bos.domain.User;

public interface UserDao extends BaseDao<User> {

	User findUserByUsernameAndPassword(String username, String password);

}
