package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.DecidedzoneDao;
import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.DecidedzoneService;
import cn.itcast.bos.utils.PageBean;

@Service("decidedzoneService")
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {

	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;

	@Override
	public void save(Decidedzone model, String[] subareaId) {
		decidedzoneDao.save(model);
		for (String string : subareaId) {
			Subarea subarea = subareaDao.findById(Integer.parseInt(string));
			subarea.setDecidedzone(model);
		}
	}

	@Override
	public void pageQuery(PageBean<Decidedzone> pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

	@Override
	public Decidedzone findById(Integer decidedzoneId) {
		return decidedzoneDao.findById(decidedzoneId);
	}

}
