<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 31125
  Date: 2022/5/3
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <h2 style="color: red">${error}</h2>
    <form:form modelAttribute="login" action="${pageContext.request.contextPath}/login" method="post">
        <table>
            <caption>登陆界面</caption>n
            <tr><td>用户名：</td>
                <td>
                    <form:input path="name"/>
                    <form:errors cssStyle="color: red" path="name"/>
                </td>
            </tr>
            <tr><td>用户密码：</td>
                <td>
                    <form:input path="pwd"/>
                    <form:errors cssStyle="color: red" path="pwd"/>
                </td>
            </tr>
            <tr><td colspan="2" align="center"><input type="submit" value="登陆"></td></tr>
        </table>
    </form:form>
</body>
</html>
