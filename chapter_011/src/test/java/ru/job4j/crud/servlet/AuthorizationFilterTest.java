package ru.job4j.crud.servlet;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.12.2019
 */
public class AuthorizationFilterTest {

    @Test
    public void whenRoleIsNullThenSendRedirect() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(null);

        new AuthorizationFilter().doFilter(req, resp, filterChain);

        verify(req, times(1)).getSession();
        verify(resp, times(1)).sendRedirect(String.format("%s/UserView", req.getContextPath()));

        verify(filterChain, times(0)).doFilter(req, resp);
    }

    @Test
    public void whenRoleIsAdminThenDoFilter() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("admin");

        new AuthorizationFilter().doFilter(req, resp, filterChain);

        verify(req, times(1)).getSession();
        verify(resp, times(0)).sendRedirect(String.format("%s/UserView", req.getContextPath()));

        verify(filterChain, times(1)).doFilter(req, resp);
    }

    @Test
    public void whenRoleIsNotAdminThenSendRedirect() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");

        new AuthorizationFilter().doFilter(req, resp, filterChain);

        verify(req, times(1)).getSession();
        verify(resp, times(1)).sendRedirect(String.format("%s/UserView", req.getContextPath()));

        verify(filterChain, times(0)).doFilter(req, resp);
    }
}