package cn.itcast.bos.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.service.NoticebillService;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.service.Customer;
import cn.itcast.crm.service.CustomerService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private NoticebillService noticebillService;

	@Autowired
	private CustomerService customerService;

	// 根据手机号查询客户信息
	public String findCustomerByTelephone() {
		Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
		Java2Json(customer, new String[] {});
		return NONE;
	}

	// 保存一个业务通知单，并尝试自动分单
	public String add() {
		noticebillService.save(model);
		return "noticebill_add";
	}

}
