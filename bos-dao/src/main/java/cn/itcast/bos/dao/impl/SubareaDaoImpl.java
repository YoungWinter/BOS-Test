package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Subarea;

@Repository("subareaDao")
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao {

}
