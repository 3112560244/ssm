<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改员工信息</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath }/emp/update" modelAttribute="employee" method="post">
  <table border="1" width="75%">
   <tr><td colspan="2"><form:input   path="id" type="hidden"  /></td></tr>
   <tr><td>员工编号：</td><td><form:input path="id" disabled="true"/><span><form:errors path="id" cssStyle="color:red;" /></span></td></tr>
   <tr><td>员工姓名：</td><td><form:input path="name" /><span><form:errors path="name" cssStyle="color:red;"/></span></td></tr>
   <tr><td>员工性别：</td><td>
   <form:radiobuttons path="gender" items="${sexMap }"/><span><form:errors path="gender" cssStyle="color:red;"/></span>
   </td></tr>
   <tr><td>员工入职日期：</td><td><form:input path="hireDate"/><span><form:errors path="hireDate" cssStyle="color:red;"/></span></td></tr>
   <tr><td>选择员工所属的部门：</td><td>
      <form:select path="dept.id">
         <form:options items="${depts }" itemLabel="name" itemValue="id"/>
      </form:select>
    </td></tr>
    <tr><td>员工的工资</td><td><form:input path="salary" /><span><form:errors path="salary" cssStyle="color:red;"/></span></td></tr>
    <tr><td colspan="2" align="center"><input type="submit" value="更新员工信息"/></td></tr>
  </table>
 </form:form>
</body>
</html>