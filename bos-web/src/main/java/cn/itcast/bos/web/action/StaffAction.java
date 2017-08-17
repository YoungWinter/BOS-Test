package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;

	// 属性驱动，接收页面提交的分页参数
	private String ids;

	@Autowired
	private StaffService staffService;

	// 批量删除取派员
	public String deleteBatch() {
		staffService.deleteBatch(ids);

		return LIST;
	}

	// 取派员分页查询
	public String pageQuery() throws IOException {
		// 查询数据
		staffService.pageQuery(pageBean);
		List<Staff> staffList = new ArrayList<Staff>();
		for (Staff staff : pageBean.getRows()) {
			if ("0".equals(staff.getDeltag())) {
				staffList.add(staff);
			}
		}
		pageBean.setRows(staffList);
		Java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize" });
		return NONE;
	}

	// 添加取派员
	public String add() {
		staffService.save(model);
		return LIST;
	}

	// 添加取派员
	public String edit() {
		Staff staff = staffService.findById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staffService.update(staff);
		return LIST;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
