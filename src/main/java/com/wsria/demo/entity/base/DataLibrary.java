package com.wsria.demo.entity.base;

import java.io.Serializable;
import javax.persistence.*;

import com.wsria.arch.util.orm.BaseEntity;

/**
 * 实体：数据字典
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@Entity
@Table(name = "wsria_demo_datalibrary")
public class DataLibrary extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean enabled;
	private String libraryCode;
	private String libraryName;
	private String libraryType;
	private String libraryValue;
	private String remark;
	private Integer sort;

	public DataLibrary() {
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "library_code")
	public String getLibraryCode() {
		return this.libraryCode;
	}

	public void setLibraryCode(String libraryCode) {
		this.libraryCode = libraryCode;
	}

	@Column(name = "library_name")
	public String getLibraryName() {
		return this.libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	@Column(name = "library_type")
	public String getLibraryType() {
		return this.libraryType;
	}

	public void setLibraryType(String libraryType) {
		this.libraryType = libraryType;
	}

	@Column(name = "library_value")
	public String getLibraryValue() {
		return this.libraryValue;
	}

	public void setLibraryValue(String libraryValue) {
		this.libraryValue = libraryValue;
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