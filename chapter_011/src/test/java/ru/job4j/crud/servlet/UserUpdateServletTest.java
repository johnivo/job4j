package ru.job4j.crud.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.datamodel.Role;
import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.12.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {

    @Test
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getParameter("id")).thenReturn("0");
        when(req.getRequestDispatcher("/WEB-INF/views/update.jsp")).thenReturn(dispatcher);

        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.doGet(req, resp);

        verify(req, times(1)).getRequestDispatcher("/WEB-INF/views/update.jsp");
        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void whenUpdateUserThenUserChanged() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        validate.add(new User("name", "log", "email", "pass", new Role("user")));
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("nameUp");
        when(req.getParameter("login")).thenReturn("logUp");
        when(req.getParameter("email")).thenReturn("emailUp");
        when(req.getParameter("password")).thenReturn("passUp");
        when(req.getParameter("role")).thenReturn("userUp");

        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);

        ServletConfig config = mock(ServletConfig.class);
        UserUpdateServlet userUpdateServlet = new UserUpdateServlet();
        userUpdateServlet.init(config);
        userUpdateServlet.doPost(req, resp);

        User user = validate.findById(0);

        assertThat(user.getName(), is("nameUp"));
        assertThat(user.getLogin(), is("logUp"));
        assertThat(user.getEmail(), is("emailUp"));
        assertThat(user.getPassword(), is("passUp"));
        assertThat(user.getRole().getRole(), is("userUp"));

        verify(resp, times(1)).sendRedirect(String.format("%s/", req.getContextPath()));
        verify(writer, times(0))
                .append(String.format("error updated for id=%s", req.getParameter("id")));
    }

}