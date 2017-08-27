package cn.itcast.bos.service;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.utils.PageBean;

public interface DecidedzoneService {

	void save(Decidedzone model, String[] subareaId);

	void pageQuery(PageBean<Decidedzone> pageBean);

	Decidedzone findById(Integer decidedzoneId);

}
