package cn.itcast.bos.service;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;

public interface StaffService {

	public void save(Staff model);

	public void pageQuery(PageBean<Staff> pageBean);

	public void deleteBatch(String ids);

	public Staff findById(Integer id);

	public void update(Staff staff);

}
