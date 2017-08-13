package cn.itcast.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.bos.dao.base.BaseDao;
import cn.itcast.bos.utils.PageBean;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType types = (ParameterizedType) this.getClass().getGenericSuperclass();
		entityClass = (Class<T>) types.getActualTypeArguments()[0];
	}

	// 根据类型注入spring工厂中的会话工厂对象sessionFactory
	@Resource
	public void setLocalSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = " from " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... params) {
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.executeUpdate();

	}

	@Override
	public void pageQuery(PageBean<T> pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria criteria = pageBean.getDetachedCriteria();
		// 查询总记录数total-->select count(*) from bc_staff
		criteria.setProjection(Projections.rowCount());
		List<Long> countList = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
		pageBean.setTotal(countList.get(0).intValue());
		// 查询当前页面展示数据rows-->select * from bc_staff
		criteria.setProjection(null);
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List<T> rows = (List<T>) getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
		pageBean.setRows(rows);
	}

}
