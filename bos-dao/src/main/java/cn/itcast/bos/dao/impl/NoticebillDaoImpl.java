package cn.itcast.bos.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.bos.dao.NoticebillDao;
import cn.itcast.bos.dao.base.impl.BaseDaoImpl;
import cn.itcast.bos.domain.Noticebill;

@Repository("noticebillDao")
public class NoticebillDaoImpl extends BaseDaoImpl<Noticebill> implements NoticebillDao {

}
