package ua.nure.odnokozov.railway.ticket.office.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CharsetEncodingFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CharsetEncodingFilter.class);

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    private static final String ENCODING = "UTF-8";

    private static final String ENCODING_INIT_PARAM = "encoding";

    private String encoding;

    public void destroy() {
        LOG.info("Destroy CharsetEncodingFilter");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        String contentType = req.getContentType();
        if (contentType != null && contentType.startsWith(CONTENT_TYPE))
            req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LOG.info("Init CharsetEncodingFilter");
        encoding = config.getInitParameter(ENCODING_INIT_PARAM);
        if (encoding == null)
            encoding = ENCODING;
    }
}
