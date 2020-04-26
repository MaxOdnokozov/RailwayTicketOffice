package ua.nure.odnokozov.railway.ticket.office.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class ResourceFilter implements Filter {  

    private static final Logger LOG = Logger.getLogger(ResourceFilter.class); 
    private static final String DEFAULT_NAME = "default"; 
    
    private RequestDispatcher defaultRequestDispatcher;  

    @Override  
    public void destroy() {}  

    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)   
          throws IOException, ServletException {  
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        LOG.debug("Static resource: " + path);
        if(path.matches("/static.*") || path.matches("/webjars.*") || path.matches(".ico")|| path.matches("/images-directory.*"))
        defaultRequestDispatcher.forward(request, response);  
    }  

    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.defaultRequestDispatcher =   
            filterConfig.getServletContext().getNamedDispatcher(DEFAULT_NAME);  
    }  
}