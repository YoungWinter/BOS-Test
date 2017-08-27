package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.dao.base.BaseDao;
import cn.itcast.bos.domain.Region;

public interface RegionDao extends BaseDao<Region> {

	List<Region> findListByQ(String q);

}
