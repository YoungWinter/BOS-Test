package cn.itcast.bos.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	// 属性驱动，接收页面输入的验证码
	private String checkcode;

	@Autowired
	private UserService userService;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// 用户登录
	public String login() {
		String result = "";
		// 1.从Session中获取验证码
		String validatecode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("key");
		// 2.校验验证码是否输入正确
		if (StringUtils.isNotBlank(checkcode)
				&& checkcode.equals(validatecode)) {
			// 校验用户是否存在
			User user = userService.login(model);
			if (user != null) {
				// 登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession()
						.setAttribute("loginUser", user);
				result = HOME;
			} else {
				// 登录失败,设置提示信息,跳转到登录页面
				this.addActionError("用户名或者密码输入错误！");
				result = LOGIN;
			}
		} else {
			// 输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			result = LOGIN;
		}
		return result;
	}

	// 用户登出
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

}
