package cn.wsria.demo.entity;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 实体基类，所有实体均需继承本类.
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
@MappedSuperclass
public class BaseEntity extends IdEntity {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
