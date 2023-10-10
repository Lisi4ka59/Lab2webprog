package com.example.lab2webprog;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HitServlet", value = "/HitServlet")
public class HitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        float X;
        float Y;
        float R;
        float random;
        try {
            R = 74;
            X = (Float.parseFloat(request.getParameter("X"))-100);
            Y = (100 - Float.parseFloat(request.getParameter("Y")));
            random = Float.parseFloat(request.getParameter("rand"));
        } catch (NumberFormatException | NullPointerException ex){
            response.getWriter().print("Получены странные данные");
            return;
        }
        String text;
        String[] result = DotValidator.result(X, Y, R, random);
        text = result[3];
        response.getWriter().print(text);
    }
}
