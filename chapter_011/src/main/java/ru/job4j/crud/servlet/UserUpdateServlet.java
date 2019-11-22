package ru.job4j.crud.servlet;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.11.2019
 */
public class UserUpdateServlet extends HttpServlet {

    /**
     * Logic layout - слой содержит выполнение бизнес логики.
     */
    private final ValidateService service = ValidateService.getInstance();

    /**
     * Открывает форму для редактирования с заполенными полями.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        User user = service.findById(id);

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"

                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Update user</title>"
                + "</head>"

                + "<body>"
                + "<form action = '" + request.getContextPath() + "/list/update' method='post'>"

                //+ "id: <input type=\"number\" name=\"id\"/><br/>"
                + "<input type='hidden' name='id' value='" + id + "'/>"
                + "name: <input type=\"text\" name=\"name\" value='" + user.getName() + "' /><br/>"
                + "login: <input type=\"text\" name=\"login\" value='" + user.getLogin() + "' /><br/>"
                + "email: <input type=\"text\" name=\"email\" value='" + user.getEmail() + "' /><br/>"

                + "<input type='submit' value='Update this user' />"
                + "</form>"
                + "</body>"

                + "</html>");

        writer.flush();
    }

    /**
     * Обновляет пользователя.
     * После обновления отправляет GET сервлету UsersServlet.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");

        User user = new User(id, name, login, email);

        if (service.update(user)) {
            response.sendRedirect(request.getContextPath() + "/list");
        } else {
            writer.append(String.format("error updated for id=%d", id));
        }

    }

}
