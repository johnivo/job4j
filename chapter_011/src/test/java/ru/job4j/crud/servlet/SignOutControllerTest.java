package ru.job4j.crud.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
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
public class SignOutControllerTest {

    @Test
    public void whenDoGetThenForwardToView() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);

        SignOutController signOutController = new SignOutController();
        signOutController.doGet(req, resp);

        verify(req, times(1)).getSession();
        verify(session, times(1)).removeAttribute("login");
        verify(req, times(1)).getRequestDispatcher("/WEB-INF/views/signin.jsp");
        verify(dispatcher).forward(req, resp);
    }

}