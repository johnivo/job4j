package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 30.12.2019
 */
public class JsonServlet extends HttpServlet {

    private PersonStorage storage = PersonStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = this.storage.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(persons);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(br, Person.class);
        this.storage.add(person);
    }
}
