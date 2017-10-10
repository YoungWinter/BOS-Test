package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.PageBean;

public interface UserService {

	public User login(User user);

	public void editPassword(Integer id, String password);

	public void save(User model, String[] roleIds);

	public void pageQuery(PageBean<User> pageBean);

}
