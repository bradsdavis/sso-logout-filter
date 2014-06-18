package com.rhc.sso;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Checks a value in the HTTP header to provide the URL to redirect to.
 * @author bradsdavis
 *
 */
public class SSOHeaderLogoutFilter extends AbstractSSOLogoutFilter {
	private static final Logger LOG = Logger.getLogger(SSOHeaderLogoutFilter.class);
	private String logoutHeaderProperty;
	
	public void init(FilterConfig conf) throws ServletException {
		logoutHeaderProperty = conf.getInitParameter("header-logout-url");
		
		if(StringUtils.isBlank(logoutHeaderProperty)) {
			throw new ServletException("Expected required init property [header-logout-parameter], which is required to tell the filter where to redirect.");
		}
		LOG.info("Initialized SSOHeaderLogoutFilter.");
	}
	
	public void destroy() {
		LOG.info("Destroyed SSOHeaderLogoutFilter.");
	}

	@Override
	protected String getRedirectUrl(ServletRequest req) {
		String headerUrl = ((HttpServletRequest)req).getHeader(logoutHeaderProperty);
		
		if(StringUtils.isBlank(headerUrl)) {
			throw new RuntimeException("Expected header property ["+logoutHeaderProperty+"] was not available on the request.");
		}
		
		return headerUrl;
	}


}
