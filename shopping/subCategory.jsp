<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<body> 
<sql:setDataSource var="user" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/test"
     user="root"  password="mysql"/> 
<sql:query dataSource="${user}" var="result">
SELECT * from pizza where type="${param.cat}";
</sql:query>
<table border="1" width="100%">
<tr> 
   <th>Name</th>
   <th>Price</th>
</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td><c:out value="${row.pname}"/></td>
   <td><c:out value="${row.price}"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>

