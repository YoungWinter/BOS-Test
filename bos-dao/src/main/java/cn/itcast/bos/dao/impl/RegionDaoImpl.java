package cn.itcast.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Region;

@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findListByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? " + "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		return (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%",
				"%" + q + "%", "%" + q + "%");
	}

}
