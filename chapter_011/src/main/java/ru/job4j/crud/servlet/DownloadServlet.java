package ru.job4j.crud.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 02.12.2019
 */
public class DownloadServlet extends HttpServlet {

    private static final String FN = File.separator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");

//        File file = new File("C:/Tools/apache-tomcat-8.5.47/bin/images" + FN + name);
//        File file = new File("images" + FN + name);

        File images = new File(System.getProperty("javax.servlet.context.tempdir"), "images");
        File file = new File(images, name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }

}
