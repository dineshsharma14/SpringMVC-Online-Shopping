package com.bitwise.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String currUri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String redirectUrl = contextPath + "/login";
		System.out.println(currUri);
		boolean flag = authUserAgainstCookie(req);
		
		if (isLogoutUri(currUri, contextPath)) {
			if (! isSessionExpired(req)) {
				req.getSession(false).invalidate();
				res.sendRedirect(redirectUrl);
				return;
			}
		}
		if (! isLoginUrl(currUri, contextPath)) {
			if (! flag) {
				res.sendRedirect(redirectUrl);
				return;
			}
		} 
		if (isLoginUrl(currUri, contextPath)) {
			if (flag) {
				redirectUrl = contextPath + "/products/home";
				res.sendRedirect(redirectUrl);
			}
		}
		
		chain.doFilter(req, res);
	}

	private boolean authUserAgainstCookie(HttpServletRequest req) {
		boolean flag = false;
		Cookie [] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (! isSessionExpired(req)) {
					if (cookie.getValue()
							.equals(req.getSession(false)
									.getAttribute("sessID"))) {
						flag = true;
					}
				}
			}
		}
		return flag;
	}

	private boolean isSessionExpired(HttpServletRequest req) {
		return req.getSession(false) == null; // return true if not existing
	}

	private boolean isLoginUrl(String currUri, String contextPath) {
		return currUri.equals(contextPath + "/login");
	}

	private boolean isLogoutUri(String currUri, String contextPath) {
		return currUri.equals(contextPath + "/logout");
	}

	@Override
	public void destroy() {	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}


}
