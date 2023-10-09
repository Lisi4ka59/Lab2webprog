package com.example.lab2webprog;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;

@WebServlet(name = "TableServlet", value = "/TableServlet")

public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        out.println("""
            <table id="res">
            <tr >
                <th style="border-top-left-radius: 10px">Координата Х</th>
                <th>Координата У</th>
                <th>Значение R</th>
                <th>Время столкновения снаряда с объектом</th>
                <th>Время полета снаряда</th>
                <th style="border-top-right-radius: 10px">Результат</th>
            </tr>
            """);
        Enumeration<String> attributes = request.getSession().getAttributeNames();
        ArrayList<String> list = new ArrayList<>();
        while (attributes.hasMoreElements()) {
            String attribute = attributes.nextElement();
            list.add(attribute);
        }
        list.sort(Collections.reverseOrder());
        for (String s:list) {
            String[] pr = ((String) request.getSession().getAttribute(s)).split("#");
            String[] atr = pr[0].split("@");
            out.println("<tr>");
            for (String prom:atr) {
                out.println("<td>" + prom + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");

    }
}
