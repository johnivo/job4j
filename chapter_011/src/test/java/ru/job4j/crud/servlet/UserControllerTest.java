package ru.job4j.crud.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.datamodel.Role;
import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.12.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserControllerTest {

    @Test
    public void whenDoGetThenForwardToViewUserPage() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        validate.add(new User("name", "log", "email", "pass", new Role("user")));
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);

        User user = validate.findByLogin("log");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("log");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/UserView.jsp")).thenReturn(dispatcher);

        UserController userController = new UserController();
        userController.doGet(req, resp);

        verify(req, times(1)).getSession();

        verify(req, times(1)).setAttribute("user", user);
        verify(req, times(1)).getRequestDispatcher("/WEB-INF/views/UserView.jsp");
        verify(dispatcher, times(1)).forward(req, resp);
    }

}