package com.example.lab2webprog;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "HitServlet", value = "/HitServlet")
public class HitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        //response.getWriter().println(Math.round(Double.parseDouble(request.getParameter("X"))) + " " + Math.round(Double.parseDouble(request.getParameter("Y"))));
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
        //System.out.println(X+" "+Y+" "+R);
        String text;
        if (((X <= 0 && X > -R) && (Y <= 0 && Y > -R/2)) || ((X >= 0 && Y >= 0) && ((X + Y) < R/2)) || ((X <= 0 && Y >= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) < Math.pow(R, 2))))) {
            if (random < 0.5) {
                text = "Не пробил!";
            } else {
                text = "Есть пробитие!";
            }
        } else {
            if (((X <= 0 && Y <= 0) && (X == -R || Y == -R/2) || ((X >= 0) && (Y >= 0)) && (X + Y == R/2)) || ((X <= 0 && Y >= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) == Math.pow(R, 2))))) {
                text = "Рикошет!";
            } else {
                text = "Не попал!";
            }
        }
        //System.out.println(text);
        response.getWriter().print(text);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
