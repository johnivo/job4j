package ru.job4j.crud.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр аутентификации пользователя.
 *
 * Аутентификация - это подтверждение подлинности чего-либо или кого либо.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.12.2019
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Если URI запроса содержит /signin, то обрабатываем фильтры дальше.
     * В противном случае проверяем, залогинился ли пользователь с помощью проверки атрибута сессии login.
     *
     * Если у сессии нет атрибута login, то переходим на страницу /signin.
     * Если атрибут login установлен, то пользователь уже залогинился, обрабатываем фильтры дальше.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            filterChain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
