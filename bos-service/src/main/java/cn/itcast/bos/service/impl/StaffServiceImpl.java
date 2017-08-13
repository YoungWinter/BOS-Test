package cn.itcast.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.StaffDao;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.utils.PageBean;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	@Override
	public void save(Staff staff) {
		staffDao.save(staff);
	}

	@Override
	public void pageQuery(PageBean<Staff> pageBean) {
		staffDao.pageQuery(pageBean);
	}

	// 批量删除取派员
	@Override
	public void deleteBatch(String ids) {
		String[] idList = ids.split(",");
		for (String id : idList) {
			staffDao.executeUpdate("staff.deleteBatch", Integer.parseInt(id));
		}
	}

	@Override
	public Staff findById(Integer id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}
}
