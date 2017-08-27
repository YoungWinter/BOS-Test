package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.WorkbillDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Workbill;

@Repository("workbillDao")
public class WorkbillDaoImpl extends BaseDaoImpl<Workbill> implements WorkbillDao {

}
