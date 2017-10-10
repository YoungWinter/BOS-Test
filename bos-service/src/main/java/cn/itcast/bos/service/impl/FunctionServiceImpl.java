package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.FunctionDao;
import cn.itcast.bos.domain.Function;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.FunctionService;
import cn.itcast.bos.utils.BOSUtils;
import cn.itcast.bos.utils.PageBean;

@Service("functionService")
@Transactional
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		Function parentFunction = model.getParentFunction();
		if (parentFunction != null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}

	@Override
	public void pageQuery(PageBean<Function> pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> loadMenu() {
		List<Function> list = null;
		User user = BOSUtils.getLoginUser();
		if (user.getUsername().equals("admin")) {
			list = functionDao.findAllMenu();
		} else {
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}
}
