package ru.job4j.crud.servlet;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.11.2019
 */
public class UserDeleteServlet extends HttpServlet {

    /**
     * Logic layout - слой содержит выполнение бизнес логики.
     */
    private final Validate service = ValidateService.getInstance();

    private static final String FN = File.separator;

    /**
     * Удаляет пользователя.
     * После удаления отправляет GET сервлету UsersServlet.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();

//        File folder = new File("images");
//        if (!folder.exists()) {
//            folder.mkdir();
//        }

        int id = Integer.parseInt(request.getParameter("id"));
//        String photoId = service.findById(id).getPhotoId();

        if (service.delete(id)) {
            //new File(folder + FN + photoId).delete();
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        } else {
            writer.append(String.format("error delete for id=%d", id));
        }

    }

}
