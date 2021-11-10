package ru.itis.filters;

import ru.itis.services.SecurityService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class AuthFilter implements Filter {

    private SecurityService securityService;
    private ServletContext context;
    private String[] protectedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context = filterConfig.getServletContext();
        securityService = (SecurityService) context.getAttribute("securityService");
        protectedPaths = new String[]{"/signIn", "/signUp"};
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (securityService.isAuth(request, request.getSession())) {
            request.setAttribute("auth", true);
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("auth", false);
            boolean isProtected = false;
            for (String str : protectedPaths) {
                if (request.getRequestURI().startsWith(request.getContextPath() + str)) {
                    isProtected = true;
                }
            }
            if (isProtected) {
                filterChain.doFilter(request, response);
                return;
            }
            response.sendRedirect(request.getContextPath() + "/signIn");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
