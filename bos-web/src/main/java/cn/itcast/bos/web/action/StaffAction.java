package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.utils.PageBean;
import cn.itcast.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = 1L;

	// 属性驱动，接收页面提交的分页参数
	private int page;
	private int rows;
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
		PageBean<Staff> pageBean = new PageBean<Staff>();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		// 创建离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		pageBean.setDetachedCriteria(criteria);
		// 查询数据
		staffService.pageQuery(pageBean);
		List<Staff> staffList = new ArrayList<Staff>();
		for (Staff staff : pageBean.getRows()) {
			if ("0".equals(staff.getDeltag())) {
				staffList.add(staff);
			}
		}
		pageBean.setRows(staffList);
		// 将pageBean封装为json对象
		JsonConfig jsonConfig = new JsonConfig();
		// 排除不需要的数据
		jsonConfig.setExcludes(new String[] { "currentPage", "detachedCriteria", "pageSize" });
		String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(json);
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

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
