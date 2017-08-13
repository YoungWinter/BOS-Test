package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

public class Staff implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String telephone;
	private String haspda = "0";// 是否有PDA，1：有 0：无
	private String deltag = "0";
	private String station;
	private String standard;
	private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
		return haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set<Decidedzone> getDecidedzones() {
		return decidedzones;
	}

	public void setDecidedzones(Set<Decidedzone> decidedzones) {
		this.decidedzones = decidedzones;
	}

}
