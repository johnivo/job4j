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
public class AuthenticationFilterTest {

    @Test
    public void whenURIContainsSignInThenDoFilter() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(req.getRequestURI()).thenReturn("/signin");

        new AuthenticationFilter().doFilter(req, resp, filterChain);

        verify(filterChain, times(1)).doFilter(req, resp);
    }

    @Test
    public void whenURINotContainsSignInAndLoginIsNullThenGetSessionAndSendRedirect() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn(null);
        when(req.getRequestURI()).thenReturn("/update");

        new AuthenticationFilter().doFilter(req, resp, filterChain);
        verify(req, times(1)).getSession();
        verify(session, times(1)).getAttribute("login");

        verify(resp, times(1)).sendRedirect(String.format("%s/signin", req.getContextPath()));

        verify(filterChain, times(0)).doFilter(req, resp);
    }

    @Test
    public void whenURINotContainsSignInAndLoginNotNullThenGetSessionAndDoFilter() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("login1");
        when(req.getRequestURI()).thenReturn("/update");

        new AuthenticationFilter().doFilter(req, resp, filterChain);
        verify(req, times(1)).getSession();
        verify(session, times(1)).getAttribute("login");

        verify(resp, times(0)).sendRedirect(String.format("%s/signin", req.getContextPath()));

        verify(filterChain, times(1)).doFilter(req, resp);
    }

}