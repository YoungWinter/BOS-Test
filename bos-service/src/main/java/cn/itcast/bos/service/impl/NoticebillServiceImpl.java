package cn.itcast.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.NoticebillDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.domain.User;
import cn.itcast.bos.domain.Workbill;
import cn.itcast.bos.service.DecidedzoneService;
import cn.itcast.bos.service.NoticebillService;
import cn.itcast.bos.service.WorkbillService;
import cn.itcast.bos.utils.BOSUtils;
import cn.itcast.crm.service.CustomerService;

@Service("noticebillService")
@Transactional
public class NoticebillServiceImpl implements NoticebillService {

	@Autowired
	private NoticebillDao noticebillDao;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private DecidedzoneService decidedzoneService;

	@Autowired
	private WorkbillService WorkbillService;

	// 保存通知单并自动分单
	@Override
	public void save(Noticebill model) {
		// 保存通知单
		User user = BOSUtils.getLoginUser();
		model.setUser(user);
		noticebillDao.save(model);

		// 自动分单
		Integer DecidedzoneId = customerService.findDecidedzoneIdByAddress(model.getPickaddress());
		if (DecidedzoneId != null) {
			Decidedzone decidedzone = decidedzoneService.findById(DecidedzoneId);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);// 业务通知单关联取派员对象
			// 设置分单类型为：自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			// 为取派员产生一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);// 追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));// 创建时间，当前系统时间
			workbill.setNoticebill(model);// 工单关联页面通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);// 取件状态
			workbill.setRemark(model.getRemark());// 备注信息
			workbill.setStaff(staff);// 工单关联取派员
			workbill.setType(Workbill.TYPE_NEW);// 工单类型
			WorkbillService.save(workbill);
		} else {
			// 没有查询到定区id，不能完成自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}

}
