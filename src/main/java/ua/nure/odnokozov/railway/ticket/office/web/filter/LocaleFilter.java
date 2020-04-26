package ua.nure.odnokozov.railway.ticket.office.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing LocaleFilter");
    }

    private static final Logger LOG = Logger.getLogger(LocaleFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        LOG.trace("Start doFilter()");
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
        String localeName = servletRequest.getParameter("lang");
        if (localeName != null) {
            LOG.trace("Setting session attribute 'lang' to locale name");
            httpReq.getSession().setAttribute("lang", localeName);
        } 
        filterChain.doFilter(httpReq, httpResp);
    }

    @Override
    public void destroy() {
        LOG.info("Destroy LocaleFilter");
    }
}
