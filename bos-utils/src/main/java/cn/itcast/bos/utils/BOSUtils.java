package cn.itcast.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.User;

public class BOSUtils {

	/**
	 * 获取Session对象
	 * 
	 * @return session
	 */
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 获取登陆用户
	 * 
	 * @return User对象
	 */
	public static User getLoginUser() {
		return (User) getSession().getAttribute("loginUser");
	}
}
