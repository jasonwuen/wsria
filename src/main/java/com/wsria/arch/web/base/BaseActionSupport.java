package com.wsria.arch.web.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础Action类，继承了{@link ActionSupport}
 *
 * @author HenryYan
 *
 */
public class BaseActionSupport extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String JSON = "json";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
}
