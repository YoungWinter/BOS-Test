package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.utils.PageBean;

public interface FunctionService {

	List<Function> findAll();

	void save(Function model);

	void pageQuery(PageBean<Function> pageBean);

}
