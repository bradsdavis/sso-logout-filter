package com.rhc.sso;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Checks the request to see whether a logout is being issued via the URL.
 * 
 * @author bradsdavis
 *
 */
public abstract class AbstractSSOLogoutFilter implements Filter {
	private final Logger LOG = Logger.getLogger(AbstractSSOLogoutFilter.class);
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if(isLogout(req)) {
			HttpServletRequest httpReq = (HttpServletRequest)req;
			HttpServletResponse httpResp = (HttpServletResponse)resp;
			
			if(httpReq.getSession()!=null) {
				httpReq.getSession().invalidate();
			}
			
			httpResp.sendRedirect(getRedirectUrl(httpReq));
		}
		else {
			chain.doFilter(req, resp);
		}
	}
	
	protected boolean isLogout(ServletRequest req)
	{
		boolean logout = StringUtils.endsWith(((HttpServletRequest)req).getServletPath(), "logout");
		if(!logout && LOG.isDebugEnabled()) {
			LOG.debug("Not logout request: "+((HttpServletRequest)req).getServletPath()+" as it does not contain 'logout.'");
		}
		return logout;
	}
	
	protected abstract String getRedirectUrl(ServletRequest req);

}
