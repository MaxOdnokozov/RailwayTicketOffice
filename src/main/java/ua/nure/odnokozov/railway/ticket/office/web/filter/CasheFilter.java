package ua.nure.odnokozov.railway.ticket.office.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CasheFilter implements Filter {
    
    private static final Logger LOG = Logger.getLogger(CasheFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing CasheFilter ");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        LOG.trace("Execute doFilter()");
        final HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Cache-Control", "no-cache,no-store");
        response.setHeader("Pragma", "no-cache");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        LOG.info("Destroy CasheFilter ");
    }
}
