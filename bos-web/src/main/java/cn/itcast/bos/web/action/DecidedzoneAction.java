package cn.itcast.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.service.DecidedzoneService;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.service.Customer;
import cn.itcast.crm.service.CustomerService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	private static final long serialVersionUID = 1L;

	// 属性驱动
	private String[] subareaId;

	@Autowired
	private DecidedzoneService decidedzoneService;

	@Autowired
	private CustomerService customerService;

	// 添加定区
	public String add() {
		decidedzoneService.save(model, subareaId);
		return LIST;
	}

	// 定区列表展示
	public String pageQuery() throws IOException {
		// 查询数据
		decidedzoneService.pageQuery(pageBean);
		Java2Json(pageBean, new String[] { "currentPage", "detachedCriteria", "pageSize", "subareas", "decidedzones" });
		return NONE;
	}

	// 未关联客户列表
	public String findListNotAssociation() {
		List<Customer> customerList = customerService.findListNotAssociation();
		Java2Json(customerList, new String[] {});
		return NONE;
	}

	// 已关联客户列表
	public String findListHasAssociation() {
		List<Customer> customerList = customerService.findListHasAssociation(model.getId().toString());
		Java2Json(customerList, new String[] {});
		return NONE;
	}

	// 属性驱动，接收页面提交的多个客户id
	private List<Integer> customerIds;

	// 保存关联客户
	public String assigncustomerstodecidedzone() {
		customerService.assigncustomerstodecidedzone(model.getId().toString(), customerIds);
		return LIST;
	}

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}

}
