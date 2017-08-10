package cn.itcast.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.bos.dao.base.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;

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

	@Override
	public List<T> findAll() {
		String hql = " from " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

}
