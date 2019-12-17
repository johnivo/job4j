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

import static org.mockito.Mockito.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.12.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class SignInControllerTest {

    @Test
    public void whenDoGetThenForwardToView() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(req.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);

        SignInController signInController = new SignInController();
        signInController.doGet(req, resp);

        verify(req).getRequestDispatcher("/WEB-INF/views/signin.jsp");
        verify(dispatcher).forward(req, resp);
    }

    @Test
    public void whenCredentialsValidAndUserExistThenSuccessSignIn() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        validate.add(new User("name", "log", "email", "pass", new Role("user")));
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);

        User user = validate.isCredential("log", "pass");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("log");
        when(req.getParameter("password")).thenReturn("pass");

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        SignInController signInController = new SignInController();
        signInController.doPost(req, resp);

        verify(req, times(1)).getSession();

        verify(session, times(1)).setAttribute("login", user.getLogin());
        verify(session, times(1)).setAttribute("password", user.getPassword());
        verify(session, times(1)).setAttribute("role", user.getRole().getRole());

        verify(resp, times(1)).sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Test
    public void whenPasswordInvalidAndUserExistThenFailedSignIn() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        validate.add(new User("name", "log", "email", "pass2", new Role("user")));
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);

        User user = validate.isCredential("log", "pass");

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("login")).thenReturn("log");
        when(req.getParameter("password")).thenReturn("pass");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);

        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);

        SignInController signInController = new SignInController();
        signInController.doPost(req, resp);

//        verify(req).setAttribute("error", "Credential invalid");
//        verify(req).getRequestDispatcher("/WEB-INF/views/signin.jsp");
//        verify(dispatcher).forward(req, resp);
    }

//    @Test
//    public void whenLoginInvalidAndUserExistThenFailedSignIn() throws ServletException, IOException {
//        Validate validate = new ValidateStub();
//        validate.add(new User("name", "log2", "email", "pass", new Role("user")));
//        PowerMockito.mockStatic(ValidateService.class);
//        when((ValidateService.getInstance())).thenReturn(validate);
//
//        User user = validate.isCredential("log", "pass");
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        when(req.getParameter("login")).thenReturn("log");
//        when(req.getParameter("password")).thenReturn("pass");
//
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//        when(req.getRequestDispatcher("/WEB-INF/views/signin.jsp")).thenReturn(dispatcher);
//
//        HttpSession session = mock(HttpSession.class);
//        when(req.getSession()).thenReturn(session);
//
//        SignInController signInController = new SignInController();
//        signInController.doPost(req, resp);
//
//        verify(req, times(0)).getSession();
//        verify(resp, times(0)).sendRedirect(String.format("%s/", req.getContextPath()));
//
//        verify(req, times(1)).setAttribute("error", "Credential invalid");
//        verify(req, times(1)).getRequestDispatcher("/WEB-INF/views/signin.jsp");
//        verify(dispatcher, times(1)).forward(req, resp);
//    }
}