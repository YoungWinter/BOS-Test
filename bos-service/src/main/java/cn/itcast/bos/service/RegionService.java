package cn.itcast.bos.service;

import java.util.List;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PageBean;

public interface RegionService {

	public void saveBatch(List<Region> regionList);

	public void pageQuery(PageBean<Region> pageBean);

	public List<Region> findAll();

}
