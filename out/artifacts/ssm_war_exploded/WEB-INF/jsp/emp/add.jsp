<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加新员工</title>
</head> 
<body>
 <form:form action="${pageContext.request.contextPath }/emp/add" modelAttribute="employee" method="post" enctype="multipart/form-data">
  <table border="1" width="75%">
   <tr><td>员工编号：</td><td><form:input path="id" /><span><form:errors path="id" cssStyle="color:red;"/></span></td></tr>
   <tr><td>员工姓名：</td><td><form:input path="name" /><span><form:errors path="name" cssStyle="color:red;"/></span></td></tr>
   <tr><td>员工性别：</td><td>
   <form:radiobuttons path="gender" items="${sexMap }"/><span><form:errors path="gender" cssStyle="color:red;"/></span>

   </td></tr><tr><td>选择上传的头像：</td><td><form:input TYPE="file" path="multipartFile"/></td></tr>

   <tr><td>员工入职日期：</td><td><input type="date" name="hireDate"/><span><form:errors path="hireDate" cssStyle="color:red;"/></span></td></tr>
   <tr><td>选择员工所属的部门：</td><td>
      <select name="dept.id">
         <option value="">--请选择--</option>
         <c:forEach items="${depts }" var="dept">
           <option value="${dept.id }">${dept.name }</option>
         </c:forEach>
      </select>
    </td></tr>
    <tr><td>员工的工资</td><td><form:input path="salary" /><span><form:errors path="salary" cssStyle="color:red;"/></span></td></tr>
    <tr><td colspan="2" align="center"><input type="submit" value="添加新员工"/></td></tr>
  </table>
 </form:form>
</body>
</html>