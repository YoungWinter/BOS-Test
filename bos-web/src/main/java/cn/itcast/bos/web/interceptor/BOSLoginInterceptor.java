package cn.itcast.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.bos.domain.User;
import cn.itcast.bos.utils.BOSUtils;

/**
 * 用户未登陆自动跳转到登陆页面
 * 
 * @author YangWentao
 *
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		User loginUser = BOSUtils.getLoginUser();
		if (loginUser == null) {
			return "login";
		}
		return invocation.invoke();
	}

}
