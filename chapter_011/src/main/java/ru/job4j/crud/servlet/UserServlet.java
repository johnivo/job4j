package ru.job4j.crud.servlet;

import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout - слой сервлетов. В них приложение должно получать данные от клиента и отдавать данные клиенту.
 *
 * GET:
 * /[no params] возвращает список всех пользователей в хранилище
 * /?id=[id] - возвращает пользователя по id
 * если полоьзователей нет, то "No such user."
 * POST:
 * /?action=add&id=[id]&name=[name]&login=[login]&email=[email] - создание пользователя
 * /?action=update&id=[id]&name=[name]&login=[login]&email=[email] - обновление пользователя
 * /?action=delete&id=[id] - удаление пользователя по id.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @since 15.11.2019
 */
public class UserServlet extends HttpServlet {

    /**
     * Ссылка на объект ValidateService.
     */
    private final Validate service = ValidateService.getInstance();

    /**
     * Ссылка на объект DispatchAction, с загруженным списком операций.
     */
    private final DispatchAction dispatcher = new DispatchAction(service).init();

    /**
     * GET запрос.
     * В ответ возвращается список всех пользователей или пользователь по id.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        if (request.getParameter("id") == null) {
            if (!service.findAll().isEmpty()) {
                for (Object user : service.findAll()) {
                    writer.append(user.toString());
                    writer.append(System.lineSeparator());
                }
            } else {
                writer.append("No data, users are not found.");
            }
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            if (service.findById(id) != null) {
                writer.append(service.findById(id).toString());
            } else {
                writer.append("No such user.");
            }
        }
        writer.flush();
    }

    /**
     * POST запрос.
     * Позволяет создавать, изменять и удалять пользователей.
     * По ключу из параметра "action" запроса получаем функцию из карты dispatch,
     * которая выполняет нужное действие.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String action = request.getParameter("action");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");

        String success = "Action completed successfully";
        String failure = "Action was not be performed";

        User user = new User(id, name, login, email);

        writer.append(this.dispatcher.sent(action, user) ? success : failure);
    }

}
