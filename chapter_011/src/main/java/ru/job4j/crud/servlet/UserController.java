package ru.job4j.crud.servlet;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 09.12.2019
 */
public class UserController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    /**
     * Обеспечивает отображение данных только текущего пользователя.
     *
     * Получаем атрибут сессии login, по нему находим пользователя и переходит на страницу /UserView
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        request.setAttribute("user", service.findByLogin(login));
//        Integer id = (Integer) session.getAttribute("id");
//        request.setAttribute("user", service.findById(id));

        request.getRequestDispatcher("/WEB-INF/views/UserView.jsp").forward(request, response);
    }

}
