package cn.itcast.bos.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.RoleDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.RoleService;
import cn.itcast.bos.utils.PageBean;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public void save(Role model, String functionIds) {
		roleDao.save(model);
		if (StringUtils.isNotBlank(functionIds)) {
			String[] fIds = functionIds.split(",");
			for (String functionId : fIds) {
				// 手动构造一个权限对象，设置id，对象状态为托管状态
				Function function = new Function(functionId);
				// 角色关联权限
				model.getFunctions().add(function);
			}
		}
	}

	/**
	 * 分页查询
	 */
	public void pageQuery(PageBean<Role> pageBean) {
		roleDao.pageQuery(pageBean);
	}

	/**
	 * 查询所有
	 */
	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
