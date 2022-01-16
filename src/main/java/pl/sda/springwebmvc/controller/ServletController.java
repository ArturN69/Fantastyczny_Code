package pl.sda.springwebmvc.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ServletController {

    @RequestMapping("/servlet")
    public void servletMethod(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        final PrintWriter writer = response.getWriter();
        writer.println("<html><body>" +
                "<h1>" +
                "Hello from servlet! Zażółć gęślą jaźń"+
                "</h1></body></html>"
        );
    }

    @RequestMapping("/servlet/hello")
    public void servletHello(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        final String name = request.getParameter("name");
        final String birth = request.getParameter("birth");
        LocalDate birthDate = LocalDate.parse(birth);
        final int age = LocalDate.now().getYear() - birthDate.getYear();
        response.setStatus(200);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        final PrintWriter writer = response.getWriter();
        writer.println("<html><body>" +
                "<p>Hello " + name +"." + "Masz urodziny " + birth +". Today is " + LocalDate.now() +"</p>" +
                "Obecnie masz lat " + age +
                "</body></html>");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String springHello(@RequestParam(name = "name", defaultValue = "anonim") String name,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birth){
        if (birth == null){
            return "brak daty urodzin";
        }
        return "<h2>Zazółć " + name +". Masz urodziny " + birth.toString() + ". Dzisiaj jest" + LocalDate.now() +
                " Obecnie masz lat " + (LocalDate.now().getYear() - birth.getYear()) + "</h2>";
    }


    @RequestMapping("/power")
    @ResponseBody
    public String power(@RequestParam double value){
        return "Rezultat = " + value * value;
    }

    @RequestMapping("/power-form")
    public String showPowerForm(){
        return "power-form";
    }
}
