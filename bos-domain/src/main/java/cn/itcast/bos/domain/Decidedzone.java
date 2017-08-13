package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

public class Decidedzone implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Staff staff;
	private String name;
	private Set<Subarea> subareas = new HashSet<Subarea>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Subarea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}
