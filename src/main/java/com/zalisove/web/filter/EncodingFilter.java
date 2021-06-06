package com.zalisove.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;


/**
 * Encoding filter.
 * @author O.S.Kyrychenko
 */
@WebFilter(urlPatterns = {"/*"}, initParams = @WebInitParam(name = "encoding",value = "UTF-8"))
public class EncodingFilter implements Filter {

	private static final Logger log = LogManager.getLogger(EncodingFilter.class);

	private String encoding = "UTF-8";

	public void destroy() {
		log.debug("Filter destruction starts");
		log.debug("Filter destruction finished");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
						 FilterChain chain) throws IOException, ServletException {
		
		log.debug("Filter starts");
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		log.trace("Request uri --> " + httpRequest.getRequestURI());
		
		String requestEncoding = request.getCharacterEncoding();
		if (requestEncoding == null) {
			log.trace("Request encoding = null, set encoding --> " + encoding);
			request.setCharacterEncoding(encoding);
		}
		
		log.debug("Filter finished");		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("Filter initialization starts");
		encoding = fConfig.getInitParameter("encoding");
		log.trace("Encoding from web.xml --> " + encoding);
		log.debug("Filter initialization finished");
	}

}