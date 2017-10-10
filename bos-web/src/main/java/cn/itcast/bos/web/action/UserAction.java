package cn.itcast.bos.web.action;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.service.UserService;
import cn.itcast.bos.utils.BOSUtils;
import cn.itcast.bos.utils.MD5Utils;
import cn.itcast.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	// 属性驱动，接收页面输入的验证码
	private String checkcode;

	private String[] roleIds;

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	@Autowired
	private UserService userService;

	/**
	 * 保存用户
	 */
	public String add() {
		userService.save(model, roleIds);
		return LIST;
	}

	/**
	 * 分页查询
	 */
	public String pageQuery() {
		userService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[] { "noticebills", "roles" });
		return NONE;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// 修改密码
	public String editPassword() throws IOException {
		// 设置响应标志
		String flag = "1";
		// 获取当前登陆用户
		User loginUser = BOSUtils.getLoginUser();
		try {
			userService.editPassword(loginUser.getId(), model.getPassword());
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(flag);
		return NONE;
	}

	// 用户登录(使用Shiro框架)
	public String login() {
		String result = "";
		// 1.从Session中获取验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		// 2.校验验证码是否输入正确
		if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)) {
			// 使用Shiro进行认证操作
			Subject subject = SecurityUtils.getSubject();
			// 创建用户名密码令牌
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),
					MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
				// 登录成功,将user对象放入session，跳转到首页
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				result = HOME;
			} catch (AuthenticationException e) {
				e.printStackTrace();
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

	/*
	 * // 用户登录 public String login() { String result = ""; // 1.从Session中获取验证码
	 * String validatecode = (String)
	 * ServletActionContext.getRequest().getSession().getAttribute("key"); //
	 * 2.校验验证码是否输入正确 if (StringUtils.isNotBlank(checkcode) &&
	 * checkcode.equals(validatecode)) { // 校验用户是否存在 User user =
	 * userService.login(model); if (user != null) { //
	 * 登录成功,将user对象放入session，跳转到首页
	 * ServletActionContext.getRequest().getSession().setAttribute("loginUser",
	 * user); result = HOME; } else { // 登录失败,设置提示信息,跳转到登录页面
	 * this.addActionError("用户名或者密码输入错误！"); result = LOGIN; } } else { //
	 * 输入的验证码错误,设置提示信息，跳转到登录页面 this.addActionError("输入的验证码错误！"); result = LOGIN;
	 * } return result; }
	 */

	// 用户登出
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

}
