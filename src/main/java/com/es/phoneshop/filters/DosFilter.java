package com.es.phoneshop.filters;

import com.es.phoneshop.utils.DosCounter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        DosCounter.count();
        DosCounter.replaceDate();
        if(DosCounter.getNum()>= 20) {
            ((HttpServletResponse) servletResponse).setStatus(429);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
