package ru.job4j.crud.servlet;

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
public class SignOutController extends HttpServlet {

    /**
     * Обеспечивает выход пользователя из системы.
     *
     * Удаляем атрибут сессии login и выполняем редирект на страницу  аутентификации.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.removeAttribute("login");
        req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
    }

}
