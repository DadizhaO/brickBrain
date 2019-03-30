package filter;

import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

@WebFilter(filterName = "ThreadContextFilter", urlPatterns = "/*")
public class ThreadContextFilter implements Filter {
    private static final String UUID_FIELD = "uuid";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            ThreadContext.put(UUID_FIELD, UUID.randomUUID().toString());
            chain.doFilter(request, response);
        } finally {
            ThreadContext.remove(UUID_FIELD);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
