package cn.itcast.bos.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Function;
import cn.itcast.bos.service.FunctionService;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FunctionService functionService;

	// 查询权限
	public String listajax() {
		List<Function> list = functionService.findAll();
		this.Java2Json(list, new String[] { "parentFunction", "roles" });
		return NONE;
	}

	// 添加权限
	public String add() {
		functionService.save(model);
		return LIST;
	}

	// 列表展示
	public String pageQuery() {
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[] { "parentFunction", "roles", "children" });
		return NONE;
	}

	/**
	 * 根据当前登录人查询对应的菜单数据，返回json
	 */
	public String loadMenu() {
		List<Function> list = functionService.loadMenu();
		this.Java2Json(list, new String[] { "parentFunction", "roles", "children" });
		return NONE;
	}

}
