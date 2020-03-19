package edu.fzu.tmall.servlet;

import edu.fzu.tmall.dao.UserDAO;
import edu.fzu.tmall.dao.UserDAOImpl;
import edu.fzu.tmall.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取列表
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.list();
        request.setAttribute("users",users);
        request.getRequestDispatcher("admin/listUser.jsp").forward(request,response);

        //重置密码
        String flag = request.getParameter("flag");
        if(flag.equals("reset")){
            String uid = request.getParameter("uid");
            resetPassword(Integer.valueOf(uid));
            request.getRequestDispatcher("admin/listUser.jsp").forward(request,response);
        }

    }


    /*
    重置密码
     */
    void resetPassword(int id){
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.list();
        User user;
        for(User tmp:users){
            if(tmp.getId() == id){
                user = tmp;
                user.setPassword("123456");
                userDAO.update(user);
                break;
            }
        }

    }


    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        super.destroy();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(req, resp);
    }






}
