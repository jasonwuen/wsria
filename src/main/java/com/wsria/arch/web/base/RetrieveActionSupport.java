package com.wsria.arch.web.base;

import org.springside.modules.orm.Page;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 表单查询ActionSupport
 *
 * @author HenryYan
 *
 */
public abstract class RetrieveActionSupport<T> extends BaseActionSupport implements ModelDriven<T> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Action函数,显示Entity列表界面.
	 * 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;
	
	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}
	
	public abstract Page<T> getPage();

}
