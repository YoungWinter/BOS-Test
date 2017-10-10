package cn.itcast.bos.dao;

import java.util.List;

import cn.itcast.bos.dao.base.BaseDao;
import cn.itcast.bos.domain.Function;

public interface FunctionDao extends BaseDao<Function> {

	List<Function> findFunctionListByUserId(Integer id);

	List<Function> findAllMenu();

	List<Function> findMenuByUserId(Integer userId);

}
