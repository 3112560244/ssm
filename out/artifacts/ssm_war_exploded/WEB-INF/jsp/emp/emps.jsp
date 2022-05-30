<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 table{
    width:800px;
    margin:10px auto;
 }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
<script type="text/javascript">
 $(function(){
	 
	 //给修改超链接添加点击事件
	 $(".put2").click(function(){
		 var href=$(this).attr("href");
		// alert("update:"+href);
		 $("#put").attr("action",href).submit();
		 return false;
	 });
	 /* 在tomcat7及以下版本中可以支持PUT提交方式，在Tomcat8.5及9.0版本中不支持 */
	//给删除超链接添加点击事件
	 $(".delete").click(function(){
         if(confirm("你确定要删除该员工吗?")){
             var href=$(this).attr("href");
             $("#delete").attr("action",href).submit();
         }
         return false;
	 });


	
 });
</script>
</head>
<body>
<div align="center" >
    <form action="${pageContext.request.contextPath}/emp/findId" >
        <input type="text" name="id" >
        <input type="submit" value="查询">
        <a href="${pageContext.request.contextPath}/emps" >重置</a>
    </form>
</div>


<table border="1">
  <tr><th>序号</th><th>编号</th><th>姓名</th><th>性别</th><th>照片</th><th>入职日期</th><th>工资</th><th>所在部门</th><th>创建时间</th><th>修改时间</th><th>操作</th></tr>
  <c:choose>
     <c:when test="${!empty(emps) }">
        <c:forEach items="${emps }" var="emp" varStatus="empStatus">
        <tr>
           <td>${empStatus.count }</td>
           <td>${emp.id }</td>
           <td>${emp.name }</td>
           <td>${emp.gender?'男':'女' }</td>
            <td>${emp.img }</td>
           <td>${emp.hireDate }</td>
           <td>${emp.salary }</td>
           <td>${emp.dept.name }</td>
            <td>${emp.create_date }</td>
            <td>${emp.update_date }</td>
           <td><a class="put"  href="${pageContext.request.contextPath }/emp/${emp.id}">修改</a>|
            <a class="delete" href="${pageContext.request.contextPath }/delemp/${emp.id}">删除</a>
           </td>
        </tr>
        </c:forEach>
     </c:when>
     <c:when test="${empty(emps) }">
       <tr><td colspan="11">对不起，目前还没有任何的用户信息</td></tr>
     </c:when>
  </c:choose>
  <tr><td colspan="11" align="right"><a  href="${pageContext.request.contextPath }/emp/add">添加新员工</a></td></tr>
</table>
  <form id="put" action="" method="POST">
    <input type="hidden" name="_method" value="PUT"/>
  </form>
  <form id="delete" action="" method="DELETE">
    <input type="hidden" name="_method" value="DELETE"/>
  </form>
</body>
</html>