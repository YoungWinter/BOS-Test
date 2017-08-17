package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Region;

@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

}
