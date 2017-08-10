package cn.itcast.bos.dao;

import cn.itcast.bos.dao.base.IBaseDao;
import cn.itcast.bos.domain.User;

public interface UserDao extends IBaseDao<User> {

	User findUserByUsernameAndPassword(String username, String password);

}
