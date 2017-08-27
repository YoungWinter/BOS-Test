package cn.itcast.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.utils.PageBean;

@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements SubareaService {

	@Autowired
	private SubareaDao subareaDao;

	@Override
	public void save(Subarea subarea) {
		subareaDao.save(subarea);

	}

	@Override
	public void pageQuery(PageBean<Subarea> pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone"));
		// TODO Auto-generated method stub
		return subareaDao.findByCriteria(dc);
	}

	@Override
	public List<Subarea> findListByDecidedzoneId(Integer decidedzoneId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return subareaDao.findByCriteria(dc);
	}

}
