<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page session="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lab2 web</title>
    <link rel="stylesheet" href="static/CSS/style.css"/>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
    jQuery(function(){
        function countDigits(number) {
            var numberString = number.toString();

            if (numberString.length > 15){
                return true;
            }else{
                return false;
            }
        }
        function valid(){
            let x = jQuery("#vvodx").val();
            let y = jQuery("#vvody").val();

            //jQuery("#group").html(x.lenght());
            if(isNaN(y) || y=="" || countDigits(y) || !((y<=5 && y>=-5))){
                vvody.classList.add('invalid');
            }
            if(isNaN(x) || x=="" || !(x==-5 || x==-4 || x==-3 || x==-2 || x==-1 || x==0 || x==1 || x==2 || x==3)){
                vvodx.classList.add('invalid');
            }
            if ((y<=5 & y>=-5) && !(isNaN(y) || y=="" || countDigits(y))){
                vvody.classList.remove('invalid');
            }
            if ((x==-5 || x==-4 || x==-3 || x==-2 || x==-1 || x==0 || x==1 || x==2 || x==3 ) && !(isNaN(x) || x=="")){
                vvodx.classList.remove('invalid');
            }
            if ((y<=5 & y>=-5) & (x==-5 || x==-4 || x==-3 || x==-2 || x==-1 || x==0 || x==1 || x==2 || x==3) & !(isNaN(y) || y=="" || countDigits(y)) && !(isNaN(x) || x=="")){
                return true;}
            return false;

        };
        function keyevent(){
            if(!valid()){
                jQuery("#submit").prop("disabled", true);
            }else{
                jQuery("#submit").prop("disabled", false);
            }

        };


        jQuery(".xbutton").on("click", function(){jQuery("#vvodx").val(jQuery(this).html());
            event.preventDefault();
            keyevent();});
        jQuery("#vvodx").on("keypress", keyevent);
        jQuery("#vvody").on("keypress", keyevent);
        jQuery("#vvodx").on("change", keyevent);
        jQuery("#vvody").on("change", keyevent);
        jQuery("#vvodx").on("click", keyevent);
        jQuery("#vvody").on("click", keyevent);
        jQuery("#vvodx").on("hover", keyevent);
        jQuery("#vvody").on("hover", keyevent);
        jQuery("#vvodx").on("keyup", keyevent);
        jQuery("#vvody").on("keyup", keyevent);

        jQuery("#forma").on("submit", function(){
            if(!valid()){
                event.preventDefault();
                keyevent();
            };
        });
        //jQuery(".vvod").on("click", keyevent);

    });

</script>
<body>

<img src="static/img/back.jpg" alt="back" id="back">
<div class="intro" id="intro"></div>
<div class="container">
<div class="data">
    <img src="static/img/capy.jpg" alt="profile" class="profile-pic">
    <div class="header-info">
        <p style="font-size: 34px"><b>Mikhail Nachinkin</b></p>
        <p>Группа: P3206</p>
        <p>Вариант: 1734</p>
        <a href="https://vk.com/nachinkin1" ><img src="static/img/VK logo.png" alt="vk" class="contact"></a>
        <a href="https://github.com/Lisi4ka59" ><img src="static/img/github logo.png" alt="github" class="contact"></a>
        <a href="https://isu.ifmo.ru/pls/apex/f?p=2437:99:106462222053412" ><img src="static/img/ITMO logo.png" alt="itmo" class="contact"></a>
    </div>
</div>
    <form action="ControllerServlet" method="POST" id="forma">

        <div class="xy_table">
            <div id="da">
            <div id="d" >

            </div>
            <div id="resultat">
            </div>
            </div>
            <div class="xy"> <div id="x"> X </div><div class="input"><label for="vvodx"></label><input class="in" type="text" name="X" id="vvodx"/></div> <div class="button"> <button class="xbutton"> -5 </button> <button class="xbutton"> -4 </button>  <button class="xbutton"> -3 </button> <button class="xbutton"> -2 </button>  <button class="xbutton"> -1 </button> <button class="xbutton"> 0 </button>  <button class="xbutton"> 1 </button>  <button class="xbutton"> 2 </button>  <button class="xbutton"> 3 </button> </div>   </div>
            <div class="xy"> <div><label for="vvody">Y</label></div><div class="input"><input class="in" type="text" name="Y" id="vvody" placeholder="значение от -5, до 5"/> </div>  </div>
            <div class="xy"> <div> R </div><div class="input"><label>
                <input class="radi" type="radio" name="R" checked value="1"/>
            </label> 1
                <label>
                    <input class="radi" type="radio" name="R" value="1.5"/>
                </label> 1.5 <label>
                    <input class="radi" type="radio" name="R" value="2"/>
                </label> 2 <label>
                    <input class="radi" type="radio" name="R" value="2.5"/>
                </label> 2.5 <label>
                    <input class="radi" type="radio" name="R" value="3"/>
                </label> 3 </div>  </div>
            <div class="wrapper">
                <input class="sub" type="submit" disabled value="Submit" id="submit"/>
            </div>


        </div>



    </form>
    <div id="table">
        <table id="res">
            <tr >
                <th style="border-top-left-radius: 10px">Координата Х</th>
                <th>Координата У</th>
                <th>Значение R</th>
                <th>Время столкновения снаряда с объектом</th>
                <th>Время полета снаряда</th>
                <th style="border-top-right-radius: 10px">Результат</th>
            </tr>
        <%
            Enumeration<String> attributes = request.getSession().getAttributeNames();
            ArrayList<String> list = new ArrayList<>();
            while (attributes.hasMoreElements()) {
                String attribute = (String) attributes.nextElement();
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
        %>
    </div>
</div>



    <script>
        jQuery(function(){
            var S = $('input[name="R"]:checked').val();
            $('#d').bind('mousewheel', function(e){
                var wh = (e.originalEvent.wheelDeltaY / 300) - (-S);
                S = wh;
                let X = 1000;
                let Y = 1000;
                let R = wh;
                let random = Math.random();
                jQuery("#d").css("background-image", "url('GraphicsServlet?x=" + X + "&y=" + Y + "&r=" + R + "&rand=" + random + " ')");
                e.preventDefault();
            });
            jQuery("#d").click(function (event){
                let parentOffset = jQuery(this).offset();
                let X = event.pageX - parentOffset.left;
                let Y = event.pageY - parentOffset.top;
                let R = S;
                let random = Math.random();
                jQuery("#resultat").load("HitServlet?X=" + X + "&Y=" + Y + "&R=" + R + "&rand=" + random);
                jQuery("#d").css("background-image", "url('GraphicsServlet?x=" + X + "&y=" + Y + "&r=" + R + "&rand=" + random + " ')");
                setTimeout(function () {jQuery("#table").load("TableServlet");}, 10);
            });
            jQuery(".radi").change(function (event) {
                let parentOffset = jQuery(this).offset();
                let X = 1000;
                let Y = event.pageY - parentOffset.top;
                S = $('input[name="R"]:checked').val();
                let R = S;
                let random = Math.random();
                jQuery("#d").css("background-image", "url('GraphicsServlet?x=" + X + "&y=" + Y + "&r=" + R + "&rand=" + random + " ')");
            });
            $(window).on('scroll', function (ee){
                $('#back').css('top', $(window).scrollTop()/1.5);
            });
        });

    </script>


<script src="static/JS/three.r134.min.js"></script>
<script src="static/JS/vanta.birds.min.js"></script>
<script src="static/JS/app.js"></script>
</body>
</html>