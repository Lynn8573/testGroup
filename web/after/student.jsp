<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/12
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="0" align="center" width="50%">
    <tr bgcolor="#009688" height="50px">
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <%-- 通过c标签的forEach 循环，进行遍历后端传来的数据--%>
    <c:forEach var="stu" items="${students}">
        <tr bgcolor="#5FB878" height="50px">
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.sex}</td>
        </tr>
    </c:forEach>
    <%-- 通过父类对象接受后端数据传来的pageNo和pageCount，进行逻辑判断--%>
    <%-- 然后进行数据格式的转换--%>
    <%

        Object objNo = request.getAttribute("pageNo");
        Object objCount = request.getAttribute("pageCount");
        int pageNo = 0;
        int pageCount = 0;
        if(objNo != null){
            pageNo = Integer.parseInt(objNo.toString());
        }
        if(objCount != null){
            pageCount = Integer.parseInt(objCount.toString());
        }
    %>
    <tr bgcolor="#009688" height="30px">
        <td colspan="4" align="center" >

            <a href="<%=path%>/studentByPageServlet?pageNo=1">首页</a>

            <%
                if(pageNo > 1){
            %>
            <a href="<%=path%>/studentByPageServlet?pageNo=${pageNo-1}">上一页</a>
            <%
                }
            %>
            <%
                if(pageNo == 1){
            %>
            <a>上一页</a>
            <%
                }
            %>


            <%
                if(pageNo < pageCount){
            %>
            <a href="<%=path%>/studentByPageServlet?pageNo=${pageNo+1}">下一页</a>
            <%
                }
            %>
            <%
                if(pageNo == pageCount){
            %>
            <a>下一页</a>
            <%
                }
            %>

            <a href="<%=path%>/studentByPageServlet?pageNo=${pageContext}">尾页</a>

        </td>
    </tr>
</table>
</body>
</html>
