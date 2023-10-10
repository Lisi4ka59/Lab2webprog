package com.example.lab2webprog;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "GraphicsServlet", value = "/GraphicsServlet")
public class GraphicsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_INDEXED);
        float x = Float.parseFloat(request.getParameter("x"));
        float y = Float.parseFloat(request.getParameter("y"));
        float radius;
        float random = Float.parseFloat(request.getParameter("rand"));
        try {
            radius = Float.parseFloat(request.getParameter("r"));
        }catch (Exception ex){
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0,0, 200, 200);
            graphics.setColor(Color.red);
            graphics.drawString("Parameter R not found!", 30, 90);
            graphics.dispose();
            ImageIO.write(image, "jpeg", out);
            out.close();
            return;
        }

        int roundx = Math.round(x-100);
        int roundy = -Math.round(y-100);
        float X = (x-100)*radius;
        float Y = -(y-100)*radius;
        float R = radius * 74;

        int gr;
        int re;
        int bl;
        String text;

        String[] result = DotValidator.result(X, Y, R, random);
        re = Integer.parseInt(result[0]);
        gr = Integer.parseInt(result[1]);
        bl = Integer.parseInt(result[2]);
        text = result[3];



        Graphics2D graphics = image.createGraphics();
        graphics.setColor(new Color(255,255,255));
        graphics.fillRect(0,0, 200, 200);
        graphics.setColor(new Color(51, 153, 255));
        graphics.fillRect(27, 100, 73, 36);
        graphics.fillArc(26, 26, 150, 150, 90, 90);
        graphics.fillPolygon(new int[]{100, 100, 137}, new int[] {100, 63, 100}, 3);
        graphics.setColor(new Color(0,0,0));
        graphics.fillRect(8, 99, 184, 2);
        graphics.fillRect(99, 8, 2, 184);


        graphics.fillRect(27, 96, 2, 8);
        graphics.fillRect(63, 96, 2, 8);
        graphics.fillRect(136, 96, 2, 8);
        graphics.fillRect(172, 96, 2, 8);
        graphics.fillRect(96, 27, 8, 2);
        graphics.fillRect(96, 63, 8, 2);
        graphics.fillRect(96, 136, 8, 2);
        graphics.fillRect(96, 172, 8, 2);


        graphics.fillPolygon(new int[]{188, 188, 196}, new int[] {96, 104, 100}, 3);
        graphics.fillPolygon(new int[]{96, 104, 100}, new int[] {12, 12, 4}, 3);


        graphics.drawString(String.valueOf(radius), 164,92);
        graphics.drawString(String.valueOf(radius/2), 164 - 36,92);
        graphics.drawString(String.valueOf(radius/2), 164 - 109,92);
        graphics.drawString(String.valueOf(radius), 164 - 145,92);


        graphics.drawString(String.valueOf(radius), 106,32);
        graphics.drawString(String.valueOf(radius/2), 106,32 + 36);
        graphics.drawString(String.valueOf(radius/2), 106,32 + 36*3+1);
        graphics.drawString(String.valueOf(radius), 106,32 + 36*3+1 +36);

        graphics.drawString("X", 185,117);
        graphics.drawString("Y", 107,15);



        if (x != 1000) {
            graphics.setColor(new Color(re, gr, bl));
            graphics.fillOval(roundx + 100 - 2, -(roundy - 100 + 2), 5, 5);
        }
        Enumeration<String> attributes = request.getSession().getAttributeNames();
        ArrayList<String> list = new ArrayList<>();
        while (attributes.hasMoreElements()) {
            String attribute = attributes.nextElement();
            list.add(attribute);
        }
        for (String s:list) {
            String[] pr = ((String) request.getSession().getAttribute(s)).split("#");
            String[] atr = pr[0].split("@");
            float oldX = Float.parseFloat(atr[0]);
            float oldY = Float.parseFloat(atr[1]);
            float oX = oldX * 74 / radius;
            float oY = oldY * 74 / radius;
            int newX = Math.round(((oX)+100));
            int newY = -Math.round(-((oY)-100));
            String[] color = pr[1].split("%");
            int r = Integer.parseInt(color[0]);
            int g = Integer.parseInt(color[1]);
            int b = Integer.parseInt(color[2]);
            graphics.setColor(new Color(r,g,b));
            graphics.fillOval(newX - 2, -(newY + 2), 5, 5);
        }
        graphics.dispose();
        if (x != 1000) {
            HttpSession session = request.getSession();
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTimeInMillis());
            session.setAttribute(time, ((x - 100) / 74 * radius) + "@" + ((100 - y) / 74 * radius) + "@" + radius + "@" + time + "@" + "1" + "@" + text + "#" + re + "%" + gr + "%" + bl);
        }
        ImageIO.write(image, "jpeg", out);
        out.close();
    }
}
