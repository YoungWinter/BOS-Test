package cn.itcast.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import cn.itcast.bos.utils.PageBean;

public interface BaseDao<T> {

	public void save(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T findById(Serializable id);

	public List<T> findAll();

	public void executeUpdate(String queryName, Object... params);

	// 分页查询
	public void pageQuery(PageBean<T> pageBean);
}
