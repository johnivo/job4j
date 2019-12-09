package ru.job4j.crud.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр авторизации пользователя.
 *
 * Авторизация является функцией определения прав доступа к ресурсам и управления этим доступом.
 *
 * аутентификация — это установление соответствия лица названному им идентификатору;
 * а авторизация — предоставление этому лицу возможностей в соответствие с положенными ему правами
 * или проверка наличия прав при попытке выполнить какое-либо действие.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.12.2019
 */
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Проверяет атрибут сессии role.
     *
     * Если установлен атрибут admin, то обрабатываем фильтры дальше.
     * В противном случае переходим на страницу пользователя /UserView.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin")) {
            filterChain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(String.format("%s/UserView", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {

    }
}
