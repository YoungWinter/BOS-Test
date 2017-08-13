package cn.itcast.bos.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean<T> {
	// 当前页数
	private int currentPage;
	// 每页显示的记录数
	private int pageSize;
	// 总记录数
	private int total;
	// 当前页面需要展示的数据
	private List<T> rows = new ArrayList<T>();
	// 查询条件
	private DetachedCriteria detachedCriteria;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

}