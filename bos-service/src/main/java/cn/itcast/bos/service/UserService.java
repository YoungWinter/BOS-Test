package cn.itcast.bos.service;

import cn.itcast.bos.domain.User;

public interface UserService {

	public User login(User user);

	public void editPassword(Integer id, String password);

}
