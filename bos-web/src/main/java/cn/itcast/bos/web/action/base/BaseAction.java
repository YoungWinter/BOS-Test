package cn.itcast.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;

	public static final String HOME = "home";
	public static final String LIST = "list";
	// 模型对象
	protected T model;
	// 分页对象
	protected PageBean<T> pageBean = null;
	protected DetachedCriteria criteria = null;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> modelClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		try {
			model = modelClass.newInstance();
			pageBean = new PageBean<T>();
			criteria = DetachedCriteria.forClass(modelClass);
			pageBean.setDetachedCriteria(criteria);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	// Java对象转Json字符串
	public void Java2Json(Object obj, String[] excludes) {
		// 将pageBean封装为json对象
		JsonConfig jsonConfig = new JsonConfig();
		// 排除不需要的数据
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(obj, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Java集合对象转Json字符串
	@SuppressWarnings("rawtypes")
	public void Java2Json(List objs, String[] excludes) {
		// 将pageBean封装为json对象
		JsonConfig jsonConfig = new JsonConfig();
		// 排除不需要的数据
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(objs, jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

}
