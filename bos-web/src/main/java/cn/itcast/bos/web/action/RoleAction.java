package cn.itcast.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Role;
import cn.itcast.bos.service.RoleService;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleService roleService;

	// 属性驱动，接收权限的id
	private String functionIds;

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	// 添加角色
	public String add() {
		roleService.save(model, functionIds);
		return LIST;
	}

	// 列表展示
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[] { "functions", "users" });
		return NONE;
	}

	// 多选框展示
	public String listajax() {
		List<Role> roleList = roleService.findAll();
		this.Java2Json(roleList, new String[] { "functions", "users" });
		return NONE;
	}

}
