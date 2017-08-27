package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.PageBean;

public interface SubareaService {

	void save(Subarea model);

	void pageQuery(PageBean<Subarea> pageBean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

	List<Subarea> findListByDecidedzoneId(Integer decidedzoneId);

}
