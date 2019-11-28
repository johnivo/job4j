package ru.job4j.crud.servlet;

import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 28.11.2019
 */
public class UsersController extends HttpServlet {

    /**
     * Ссылка на объект ValidateService.
     * Logic layout - слой содержит выполнение бизнес логики.
     */
    private final ValidateService service = ValidateService.getInstance();


    /**
     * Выполняет редирект на UsersView.jsp
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("users", service.findAll());
        request.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(request, response);
    }

}
