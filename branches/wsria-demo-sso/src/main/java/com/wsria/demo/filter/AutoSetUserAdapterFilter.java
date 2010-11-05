package com.wsria.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wsria.demo.entity.account.User;
import com.wsria.demo.service.account.UserManager;
import com.wsria.demo.util.UserUtil;


/**
 * 自动根据单点登录系统的信息设置本系统的用户信息
 *
 * @author 咖啡兔
 * @site www.wsria.cn
 *
 */
public class AutoSetUserAdapterFilter implements Filter {
	
	/**
	 * Default constructor. 
	 */
	public AutoSetUserAdapterFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中，
	 * 如果不存在使用用户查询接口查询出用户对象并设置在Session中
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		// _const_cas_assertion_是CAS中存放登录用户名的session标志
		Object object = httpRequest.getSession().getAttribute("_const_cas_assertion_");
		
		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();
			User user = UserUtil.loginUsers.get(loginName);
			
			// 第一次登录系统
			if (user == null) {
				WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(httpRequest
						.getSession().getServletContext());
				UserManager userManager = (UserManager) wct.getBean("userManager");
				user = userManager.findUserByLoginName(loginName);
			}
			
			// 保存用户信息到Session
			UserUtil.saveUserToSession(httpRequest.getSession(), user);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
