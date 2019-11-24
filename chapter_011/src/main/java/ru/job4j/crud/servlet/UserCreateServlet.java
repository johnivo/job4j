package ru.job4j.crud.servlet;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.11.2019
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Logic layout - слой содержит выполнение бизнес логики.
     */
    private final ValidateService service = ValidateService.getInstance();

    /**
     * Открывает форму для создания нового пользователя.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.sendRedirect(request.getContextPath() + "/create.jsp");
    }

    /**
     * Сохраняет/добавляет пользователя.
     * После создания пользователя отправляет GET сервлету UsersServlet.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        Integer id = 0;
        //int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");

        User user = new User(id, name, login, email);

        if (service.add(user)) {
            //response.sendRedirect(request.getContextPath() + "/list");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            writer.append("error create");
        }
    }

}
