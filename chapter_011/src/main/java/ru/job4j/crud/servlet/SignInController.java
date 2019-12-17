package ru.job4j.crud.servlet;

import ru.job4j.crud.datamodel.User;
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
 * @since 07.12.2019
 */
public class SignInController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    /**
     * Выполняет редирект на страницу /signin.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signin.jsp").forward(req, resp);
    }

    /**
     * Получаем параметры login и password из запроса,
     * по введенным учетным данным ищем пользователя в бд,
     * если логин/пароль верные, то устанавливаем соответствующие атрибуты сессии login, password и role.
     * В противном случае для запроса устанавливаем атрибут error и снова вызываем метод doGet.
     *
     * login, password используются для проверки входа в систему.
     * role при определении уровня доступа к данным.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //User user = service.findByLogin(login);
        User user = service.isCredential(login, password);

        if (user != null && login.equals(user.getLogin()) && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("login", user.getLogin());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("role", user.getRole().getRole());
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }

}
