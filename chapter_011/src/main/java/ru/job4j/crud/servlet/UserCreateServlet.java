package ru.job4j.crud.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 19.11.2019
 */
public class UserCreateServlet extends HttpServlet {

    /**
     * Logic layout - слой содержит выполнение бизнес логики.
     */
    private final ValidateService service = ValidateService.getInstance();

    private static final String FN = File.separator;

    /**
     * Открывает форму для создания нового пользователя.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
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
        PrintWriter writer = response.getWriter();

        Integer id = 0;
        String name = "";
        String login = "";
        String email = "";
        String photoId = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    photoId = item.getName();
                    if (!photoId.equals("")) {
                        File file = new File(folder + FN + photoId);
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                    }
                } else {
                    if (item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    } else if (item.getFieldName().equals("name")) {
                        name = item.getString();
                    } else if (item.getFieldName().equals("login")) {
                        login = item.getString();
                    } else if (item.getFieldName().equals("email")) {
                        email = item.getString();
                    } else if (item.getFieldName().equals("photoId")) {
                        photoId = item.getString();
                    }
                }
            }
            User user = new User(id, name, login, email, LocalDateTime.now(), photoId);
            if (service.add(user)) {
                response.sendRedirect(String.format("%s/", request.getContextPath()));
            } else {
                writer.append("error create");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

}
