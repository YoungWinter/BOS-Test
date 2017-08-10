package cn.itcast.bos.dao.user.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.dao.user.IUserDao;
import cn.itcast.bos.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

}
