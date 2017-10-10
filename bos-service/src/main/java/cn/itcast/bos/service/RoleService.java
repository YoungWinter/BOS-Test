package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Role;
import cn.itcast.bos.utils.PageBean;

public interface RoleService {

	void save(Role model, String functionIds);

	void pageQuery(PageBean<Role> pageBean);

	List<Role> findAll();

}
