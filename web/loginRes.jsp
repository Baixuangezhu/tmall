<%--
  Created by IntelliJ IDEA.
  User: 20466
  Date: 2020/2/27
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>login</title></head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    if(name.equals("admin") && password.equals("123456")) {
        //request.getRequestDispatcher("admin/listUserRes.jsp").forward(request,response);//与他一样：<jsp:forward page="admin/listUser.jsp"></jsp:forward>

    %>
<jsp:forward page="admin/listUserRes.jsp"></jsp:forward>

<%}
    else {
        out.print("登陆失败");
    }
%>
<%--  <jsp:forward page="index.jsp"></jsp:forward> --%>

</body>
</html>
