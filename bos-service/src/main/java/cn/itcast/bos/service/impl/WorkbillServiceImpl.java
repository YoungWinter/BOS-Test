package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.WorkbillDao;
import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.WorkbillService;

@Service("workbillService")
@Transactional
public class WorkbillServiceImpl implements WorkbillService {

	@Autowired
	private WorkbillDao workbillDao;

	@Override
	public void save(Workbill workbill) {
		workbillDao.save(workbill);
	}

}
