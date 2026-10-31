package org.karti.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    public HelloServlet(){
        System.out.println("HelloServlet Constructor Called");
    }

    @Override
    public void init(){
        System.out.println("Initialization Callback called");
    }

    @Override
    public void destroy(){
        System.out.println("Destruction Callback called");
    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("Inside doGet method of HelloServlet");
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("text/plain");
        httpServletResponse.getWriter().write("Get Response");
    }
}
