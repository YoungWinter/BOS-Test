package cn.itcast.bos.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Workbill implements Serializable {

	private String id;
	private Noticebill noticebill;
	private Staff staff;
	private String type;// 工单类型:新、追、改、销
	private String pickstate;// 取件状态:未取件、取件中、已取件
	private Timestamp buildtime;
	private Integer attachbilltimes;
	private String remark;

	private static final long serialVersionUID = 1L;

	public static final String TYPE_NEW = "新单";
	public static final String TYPE_APPEND = "追单";
	public static final String TYPE_MODIFY = "改单";
	public static final String TYPE_DESTROY = "销单";

	public static final String PICKSTATE_NO = "未取件";
	public static final String PICKSTATE_RUNNING = "取件中";
	public static final String PICKSTATE_YES = "已取件";

	public Workbill() {
	}

	public Workbill(String id, Noticebill noticebill, Staff staff, String type, String pickstate, Timestamp buildtime,
			Integer attachbilltimes, String remark) {
		this.id = id;
		this.noticebill = noticebill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Noticebill getNoticebill() {
		return noticebill;
	}

	public void setNoticebill(Noticebill noticebill) {
		this.noticebill = noticebill;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public Timestamp getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	public Integer getAttachbilltimes() {
		return attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
