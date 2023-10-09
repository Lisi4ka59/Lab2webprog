package com.example.lab2webprog;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")

public class ControllerServlet extends HttpServlet {

    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String X = request.getParameter("X");


        out.println("<html>");
        out.println("<head><head/><body>");
        if (X == null) {
            out.println("Данные не заданы");
        }
        out.println("</body></html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String startTime = String.valueOf(System.currentTimeMillis());
        String X = request.getParameter("X");
        String Y = request.getParameter("Y");
        String R = request.getParameter("R");
        HttpSession session = request.getSession();
        session.setAttribute("StartTime", startTime);
        out.println("<html>");
        out.println("<head><head/><body>");
        RequestDispatcher requestDispatcher;
        if (X == null || X.isEmpty() || Y == null || Y.isEmpty() || R == null || R.isEmpty()) {
            requestDispatcher = request.getRequestDispatcher("index.jsp");

            //response.sendRedirect("index.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("AreaCheckServlet");

        }
        requestDispatcher.forward(request, response);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}