package pl.sda.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/hello")
    public void servletHello(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        final String name = request.getParameter("name");
        response.setStatus(200);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        final PrintWriter writer = response.getWriter();
        writer.println("<html><body>" +
                "<p>Hello " + name +". Today is " + LocalDate.now() +"</p>" +
                "</body></html>");
    }
}
