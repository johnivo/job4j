package ru.job4j.crud.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 13.12.2019
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserDeleteServletTest {

    @Test
    public void whenDeleteUserThenRemoved() throws ServletException, IOException {

        Validate validate = new ValidateStub();
        validate.add(new User("name1"));
        validate.add(new User("name2"));
        for (Object u : validate.findAll()) {
            System.out.println(u);
        }
        PowerMockito.mockStatic(ValidateService.class);
        when((ValidateService.getInstance())).thenReturn(validate);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");

        //StringWriter stringWriter = new StringWriter();
        //PrintWriter writer = new PrintWriter(stringWriter);
        PrintWriter writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);

        List<User> users = validate.findAll();
        assertThat(users.get(0).getName(), is("name1"));
        assertThat(users.get(1).getName(), is("name2"));

        Iterator iterator = users.iterator();
        assertThat(((User) iterator.next()).getName(), is("name1"));
        assertThat(((User) iterator.next()).getName(), is("name2"));

        UserDeleteServlet userDeleteServlet = new UserDeleteServlet();
        userDeleteServlet.doPost(req, resp);

        assertThat(validate.findAll().size(), is(1));
        verify(resp, times(1)).sendRedirect(String.format("%s/", req.getContextPath()));
        verify(writer, times(0))
                .append(String.format("error delete for id=%s", req.getParameter("id")));
    }

}