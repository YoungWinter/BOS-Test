package cn.itcast.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PageBean;

@Service("regionService")
@Transactional
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDao regionDao;

	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.save(region);
		}
	}

	@Override
	public void pageQuery(PageBean<Region> pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

}
