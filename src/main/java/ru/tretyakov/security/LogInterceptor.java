package ru.tretyakov.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class are filter that pass all request thought itself and filtering if
 * login attribute is missing in session.
 *
 * author Rooter
 * since 27.09.18
 **/

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
    /**
     * TODO comment.
     * @param request todo comment
     * @param response todo comment
     * @param handler todo comment
     * @return todo comment
     */
    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) {
        if (!request.getRequestURI().equals("/signin")) {
            if (request.getSession().getAttribute("login") == null) {
                try {
                    response.sendRedirect("/signin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        return true;
    }

    /**
     * TODO comment.
     * @param request todo comment
     * @param response todo comment
     * @param handler todo comment
     * @param modelAndView todo comment
     */
    @Override
    public void postHandle(final HttpServletRequest request,
                           final  HttpServletResponse response,
                           final Object handler,
                           final ModelAndView modelAndView) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }

    /**
     * TODO comment.
     * @param request todo comment
     * @param response todo comment
     * @param handler todo comment
     * @param ex todo comment
     */
    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler, final Exception ex) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
    }
}
