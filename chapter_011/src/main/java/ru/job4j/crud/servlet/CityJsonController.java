package ru.job4j.crud.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 08.01.2020
 */
public class CityJsonController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    /**
     * Отправляет на вид список стран.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> countries = service.getCountries();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(countries);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter pw = new PrintWriter(resp.getOutputStream());
//        PrintWriter writer = resp.getWriter();
//        writer.print(json);
        pw.append(json);
        pw.flush();

    }

    /**
     * Получает страну, и отправляет на вид соответствующие ей города.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String country = req.getParameter("country");
        List<String> cities = service.getCities(country);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cities);

        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.print(json);
        writer.flush();
    }

}
