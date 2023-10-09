package com.example.lab2webprog;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        int X;
        float Y;
        float R;
        try {
            X = Integer.parseInt(request.getParameter("X").trim());
            Y = Float.parseFloat(request.getParameter("Y").trim());
            R = Float.parseFloat(request.getParameter("R").trim());
        } catch (NumberFormatException ex) {
            out.println(ex);
            X = -1000;
            Y = -1000;
            R = -1000;

        }
        out.println("<html>");
        out.println("""
                                  <head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
                                  <title>Result</title>
                                  <style type="text/css">
                                   html,

                                        .contact {
                            height: 30px;
                        }
                .data {
                            padding: 5%;
                            position: relative;
                            background-color: #3a88fc;
                            border-radius: 20px;
                            z-index: 1;
                            width: 80%;
                            margin-left: 5%;
                            opacity: 1;
                            margin-top: 5%;
                            margin-bottom: 10%;

                        }

                .container {
                            position: absolute;
                            top: 32%;
                            z-index: 0;
                            width: 60%;
                            margin-left: 20%;
                            background-color: rgba(37, 122, 250, 0.7);
                            border-radius: 50px;

                        }
                .header-info {
                            font-family: sans-serif;
                            color: #d1d1d1;
                            font-size: 18px;
                            margin-bottom: 1%;
                            margin-right: 2%;
                            z-index: 1;
                        }


                #res td, th{
                            padding: 5px;
                            border: 2px solid whitesmoke;
                        }
                #res{

                            border-collapse: collapse;
                        }
                        table, th, td {

                            border-radius: 5px;
                        }

                #back {
                            top: 0;
                            left: 0;
                            position: absolute;
                            margin: 0;
                            width: 100%;
                            z-index: -1;

                        }
                #intro {
                    top: 0;
                    left: 0;
                    position: absolute;
                    margin: 0;
                    height: 133vh;
                    overflow: hidden;
                    background: rgba(0,0,0,0) !important;
                    width: 100%;
                    z-index: -1;
                }
                        body {
                            font-family: 'Lucida Sans Unicode', 'Helvetica', sans-serif;
                            font-weight: 600;
                            font-size: 16px;
                            padding: 15% 0;

                        }
                        table {
                            overflow-x: auto;
                            color: white;
                            margin: auto;
                            
                        }
                        #ok {
                       
                        text-align: center;
                        padding-bottom: 40px;
                        }
                #table {
                            position: relative;
                            margin: auto;
                            width: 86%;
                            padding-bottom: 45px;
                padding: 50px;
                        }
                        

                                  </style><head/><body>""");
        out.println("""
                <img src="static/img/back.jpg" alt="back" id="back">
                <div class="intro" id="intro"></div>
                <div class="container">
                                
                        
                              
                        <div id="table">
                                <table id="res">
                                    <tr>
                                        <th style="border-top-left-radius: 10px;">Координата Х</th>
                                                                          <th>Координата У</th>
                                                                          <th>Значение R</th>
                                                                          <th>Время столкновения снаряда с объектом</th>
                                                                          <th>Время полета снаряда</th>
                                                                          <th style="border-top-right-radius: 10px;">Результат</th>
                                    </tr>
                                    
                                   
                    
                </div>
                """);
        Random random = new Random();
        int gr = 0;
        int re = 0;
        int bl = 0;
        String text = "";
        if (X == -1000 || Y == -1000 || R == -1000) {
            out.println("Данные не заданы");
        } else {
            if (!((Y >= -5 && Y<=5) && (X>=-5 && X<=3) && (R>=1 && R <=3))){
                out.println("<img src=\"static/img/cover.jpg\" alt=\"Achtung!\" style=\"z-index:10; height:100vh; position: relative; margin: auto;\">");
            }else {
                if (((X <= 0 && X > -R) && (Y <= 0 && Y > -R/2)) || ((X >= 0 && Y >= 0) && ((X + Y) < R/2)) || ((X <= 0 && Y >= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) < Math.pow(R, 2))))) {
                    if (random.nextInt(10) < 5) {
                        gr = 60;
                        re = 60;
                        bl = 60;
                        text = "Не пробил!";
                    } else {
                        gr = 255;
                        re = 0;
                        bl = 0;
                        text = "Есть пробитие!";
                    }
                } else {
                    if (((X <= 0 && Y <= 0) && (X == -R || Y == -R/2) || ((X >= 0) && (Y >= 0)) && (X + Y == R/2)) || ((X <= 0 && Y >= 0) && ((Math.pow(X, 2) + Math.pow(Y, 2) == Math.pow(R, 2))))) {
                        gr = 150;
                        re = 150;
                        bl = 150;
                        text = "Рикошет!";
                    } else {
                        gr = 0;
                        re = 255;
                        bl = 0;
                        text = "Не попал!";

                    }
                }
            }


            HttpSession session = request.getSession();
            long startTime = Long.parseLong((String) session.getAttribute("StartTime"));
            session.removeAttribute("StartTime");
            long scriptTime = System.currentTimeMillis() - startTime;
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTimeInMillis());
            session.setAttribute(time, X + "@" + Y + "@" + R + "@" + time + "@" + scriptTime + "@" + text + "#" + re + "%" + gr + "%" + bl);
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
                String[] color = pr[1].split("%");
                String r = color[0];
                String g = color[1];
                String b = color[2];

                out.println("<tr>");
                int i;
                for (i = 0; i < atr.length - 1; i++) {
                    out.println("<td>" + atr[i] + "</td>");
                }
                out.println("<td style=\"color: rgb(" + r + "," + g +"," + b + "); font-weight: 900;\">" + atr[i] + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("""
                     </div>
                                    <div id="ok">
                                    <a href="index.jsp"> <img style="border-radius: 10px;" src="static/img/button.png" alt="OK"/> </a>
                                    </div>
                    <script src="static/JS/three.r134.min.js"></script>
                    <script src="static/JS/vanta.birds.min.js"></script>
                    <script src="static/JS/app.js"></script></body></html>""");
        }
    }

}
