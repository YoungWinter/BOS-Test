package cn.itcast.bos.domain;

import java.util.HashSet;
import java.util.Set;

public class Region implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codeId;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String shortcode;
	private String citycode;
	private Set<Subarea> subareas = new HashSet<Subarea>();

	public Region() {
	}

	public Region(String codeId, String province, String city, String district, String postcode, String shortcode,
			String citycode, Set<Subarea> subareas) {
		super();
		this.codeId = codeId;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	public String getName() {
		return province + "-" + city + "-" + district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Set<Subarea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", codeId=" + codeId + ", province=" + province + ", city=" + city + ", district="
				+ district + ", postcode=" + postcode + ", shortcode=" + shortcode + ", citycode=" + citycode
				+ ", subareas=" + subareas + "]";
	}

}
