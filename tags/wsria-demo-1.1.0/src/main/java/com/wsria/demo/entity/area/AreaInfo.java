package com.wsria.demo.entity.area;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wsria.arch.util.orm.BaseEntity;


/**
 * 实体：地区信息
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@Entity
@Table(name="wsria_demo_area_info")
public class AreaInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String areaCode;
	private Integer areaLevel;
	private String areaName;
	private String areaNumber;
	private String countryCode;
	private Boolean enabled;
	private Long parentAreaId;
	private String remark;
	private Integer sort;

    public AreaInfo() {
    }

	@Column(name="area_code")
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	@Column(name="area_level")
	public Integer getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}


	@Column(name="area_name")
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	@Column(name="area_number")
	public String getAreaNumber() {
		return this.areaNumber;
	}

	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}


	@Column(name="country_code")
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	@Column(name="parent_area_id")
	public Long getParentAreaId() {
		return this.parentAreaId;
	}

	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}