package ru.job4j.cinema.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.job4j.cinema.model.*;
import ru.job4j.cinema.service.Validate;
import ru.job4j.cinema.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 03.02.2020
 */
public class HallController extends HttpServlet {

    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        List<Seat> seats = service.getSeats();
        Map<Integer, Set<Seat>> seatMap = new TreeMap<>();
        for (Seat seat : seats) {
            int row = seat.getRow();
            if (!seatMap.containsKey(row) || seatMap.get(row) == null) {
                seatMap.put(row, new TreeSet<>(Set.of(seat)));
            } else {
                seatMap.get(row).add(seat);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayJson = objectMapper.createArrayNode();
        for (Map.Entry<Integer, Set<Seat>> row : seatMap.entrySet()) {
            ObjectNode node = objectMapper.createObjectNode();
            ArrayNode arrayNode = objectMapper.createArrayNode();
            node.put("row", row.getKey());
            for (Seat seat : row.getValue()) {
                ObjectNode innerNode = objectMapper.createObjectNode();
                innerNode.put("place", seat.getPlace());
                innerNode.put("price", seat.getPrice());
                innerNode.put("booked", seat.isBooked());
                arrayNode.add(innerNode);
            }
            node.set("seats", arrayNode);
            arrayJson.add(node);
        }
        String json = objectMapper.writeValueAsString(arrayJson);
        pw.append(json);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(sb.toString());
        int row = node.get("row").asInt();
        int place = node.get("place").asInt();
        int price = node.get("price").asInt();
        String username = node.get("username").asText();
        String phone = node.get("phone").asText();
        Seat seat = new Seat(row, place, price, false);
        User user = new User(username, phone, null);
        ObjectNode resNode = mapper.createObjectNode();
        try {
            service.makePayment(seat, user);
            resNode.put("success", true).put("row", row).put("place", place);
        } catch (Exception e) {
            resNode.put("success", false).put("row", row).put("place", place);
        }
        PrintWriter pw = resp.getWriter();
        String json = mapper.writeValueAsString(resNode);
        pw.append(json);
        pw.flush();
    }

}
