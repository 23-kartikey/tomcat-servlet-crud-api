package org.karti.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.karti.model.User;
import org.karti.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String id = httpServletRequest.getParameter("id");
        String name = httpServletRequest.getParameter("name");
        String email = httpServletRequest.getParameter("email");
        String mobile = httpServletRequest.getParameter("mobile");

        if(id==null || name==null || email==null || mobile ==null){
            httpServletResponse.setStatus(400);
            httpServletResponse.setContentType("plain/text");
            httpServletResponse.getWriter().write("Fields can't be null");
            return;
        }

        User user = new User(Integer.parseInt(id), name, email, mobile);
        User createdUser = userService.createUser(user);

        httpServletResponse.setStatus(201);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write("{\"message\":\"User assed successfully\"}");
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException{
        String idParam = httpServletRequest.getParameter("id");

        if(idParam==null){
            List<User> users = userService.getAllUsers();
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("applicaton/json");
            httpServletResponse.getWriter().write(userService.getListJson(users));
        }

        Integer id = Integer.parseInt(idParam);
        User user = userService.getUserById(id);

        if(user==null){
            //return status 404(user not found)
            httpServletResponse.setStatus(404);
            httpServletResponse.setContentType("application/json");
        }

        //(200)return user
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(userService.getJson(user));
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String id = httpServletRequest.getParameter("id");
        String email = httpServletRequest.getParameter("email");
        String name = httpServletRequest.getParameter("name");
        String mobile = httpServletRequest.getParameter("mobile");

        if(id==null || name==null || email==null || mobile ==null){
            httpServletResponse.setStatus(400);
            httpServletResponse.setContentType("plain/text");
            httpServletResponse.getWriter().write("Fields can't be null");
            return;
        }
        User user = new User(Integer.parseInt(id), name, email, mobile);
        user = userService.putUser(user);

        httpServletResponse.setStatus(201);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(userService.getJson(user));
    }

    @Override
    protected void doPatch(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String id = httpServletRequest.getParameter("id");
        if(id==null){
            httpServletResponse.setStatus(400);
            httpServletResponse.setContentType("text/plain");
            httpServletResponse.getWriter().write("ID can't be null");
            return;
        }
        User user = userService.getUserById(Integer.parseInt(id));
        String email = httpServletRequest.getParameter("email");
        String name = httpServletRequest.getParameter("name");
        String mobile = httpServletRequest.getParameter("mobile");
        if(name!=null)
            user.setName(name);
        else if(email!=null)
            user.setEmail(email);
        else if(mobile!=null)
            user.setMobile(mobile);
        userService.deleteUserById(user.getId());
        userService.createUser(user);
        httpServletResponse.setStatus(201);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(userService.getJson(user));
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String id = httpServletRequest.getParameter("id");
        if(id==null){
            httpServletResponse.setStatus(400);
            httpServletResponse.setContentType("text/plain");
            httpServletResponse.getWriter().write("ID can't be null");
            return;
        }

        User user = userService.deleteUserById(Integer.parseInt(id));

        httpServletResponse.setStatus(204);

    }

}
