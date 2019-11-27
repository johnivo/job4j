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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        User user = service.findById(id);

        request.setAttribute("id", user.getId());
        request.setAttribute("name", user.getName());
        request.setAttribute("login", user.getLogin());
        request.setAttribute("email", user.getEmail());

        request.getRequestDispatcher("/update.jsp").forward(request, response);
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

        if (service.update(user, id)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            writer.append(String.format("error updated for id=%d", id));
        }

    }

}
