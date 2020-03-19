<%@ page import="java.util.List" %>
<%@ page import="edu.fzu.tmall.pojo.User" %>
<%@ page import="edu.fzu.tmall.dao.UserDAO" %>
<%@ page import="edu.fzu.tmall.dao.UserDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: 20466
  Date: 2020/3/3
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listUserRes</title>
</head>
<body>
<%
    UserDAO userDAO = new UserDAOImpl();
    List<User> users = userDAO.list();
    request.setAttribute("users",users);
    request.getRequestDispatcher("listUser.jsp").forward(request,response);
%>

</body>
</html>
