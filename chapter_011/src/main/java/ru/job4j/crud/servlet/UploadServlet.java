package ru.job4j.crud.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.crud.datamodel.User;
import ru.job4j.crud.logic.Validate;
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
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 02.12.2019
 */
public class UploadServlet extends HttpServlet {

    private static final String FN = File.separator;

    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }

            Integer id = 0;
            String photoId = "";
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    photoId = item.getName();
                    File file = new File(folder + FN + photoId);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                } else {
                    if (item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                }
            }
            User user = service.findById(id);
//            if (user.getPhotoId() != "") {
//                new File(folder + FN + user.getPhotoId()).delete();
//            }
            user.setPhotoId(photoId);
            if (service.uploadImage(user)) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
            } else {
                writer.append(String.format("uploading error for id=%d", id));
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

}
