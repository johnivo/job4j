package ru.job4j.crud.servlet;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 14.12.2019
 */
public class DownloadServletTest {

//локально и на тревисе разные кидает
//    @Test(expected = java.nio.file.NoSuchFileException.class) //java.io.FileNotFoundException.class
//    public void whenDoGetThenDownloadFile() throws ServletException, IOException {
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//
//        String path = System.getProperty("java.io.tmpdir") + "images";
//        InputStream inputStream = DownloadServletTest.class.getResourceAsStream("/icon.jpg");
//        Path source = Paths.get(path, "/icon.jpg");
//        Files.deleteIfExists(source);
//        Files.copy(inputStream, source);
//
//        when(req.getParameter("name")).thenReturn(String.valueOf(source.getFileName()));
//
//        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
//        when(resp.getOutputStream()).thenReturn(servletOutputStream);
//
//        DownloadServlet downloadServlet = new DownloadServlet();
//        downloadServlet.doGet(req, resp);
//
//        verify(resp, times(1)).getOutputStream();
//        verify(resp, times(1)).setContentType("name=" + "icon.jpg");
//        verify(resp, times(1)).setContentType("image/png");
//        verify(resp, times(1)).setHeader("Content-Disposition", "attachment; filename=\"" + "icon.jpg" + "\"");
//    }

}