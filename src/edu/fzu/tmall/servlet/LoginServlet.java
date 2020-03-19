package edu.fzu.tmall.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String result=null;
        if(name.equals("admin") && password.equals("123456")) {
            result="/userServlet";
            HttpSession session = request.getSession();//获取对话
            session.setAttribute("user",name);
        }
        else {
            result="/login.jsp";
        }
        request.getRequestDispatcher(result).forward(request,response);//请求转发
    }
}
