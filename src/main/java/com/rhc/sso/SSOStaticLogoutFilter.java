package com.rhc.sso;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Provides a static URL to redirect to on SSO logout.
 * @author bradsdavis
 *
 */
public class SSOStaticLogoutFilter extends AbstractSSOLogoutFilter {
	private static final Logger LOG = Logger.getLogger(SSOStaticLogoutFilter.class);
	private static final String STATIC_INIT_PROPERTY = "static.logout.url";
	private String redirectUrl;
	
	public void destroy() {
		LOG.info("Destroyed SSOStaticLogoutFilter.");
	}

	public void init(FilterConfig conf) throws ServletException {
		redirectUrl = conf.getInitParameter(STATIC_INIT_PROPERTY);
		
		if(StringUtils.isBlank(redirectUrl)) {
			throw new ServletException("Expected required init property [static-logout-url], which tells the filter where to redirect on logout.");
		}
		
		LOG.info("Initialized SSOStaticLogoutFilter.");
	}

	@Override
	protected String getRedirectUrl(ServletRequest req) {
		return redirectUrl; 
	}

}
