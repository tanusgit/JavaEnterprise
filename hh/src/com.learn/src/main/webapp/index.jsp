<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<html>
   <head>
      <title>JINSERT Operation</title>
   </head>
   
   <body>
      <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://127.0.0.1:3306/mysql" 
         user = "root"  password = "Tanusree1@"/>

      <sql:query dataSource = "${snapshot}" var = "result">
        USE databaseEmployee;
        SELECT * from employees;
      </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>Emp ID</th>
            <th>Name</th>
            <th>salary</th>
         </tr>

         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.name}"/></td>
               <td><c:out value = "${row.id}"/></td>
               <td><c:out value = "${row.salary}"/></td>
            </tr>
         </c:forEach>
      </table>
 


<h2>What is jsp?</h2>
<p>JavaServer Pages is a collection of technologies 
that helps software developers create dynamically generated 
web pages based on HTML, XML, SOAP, or other document types. 
Released in 1999 by Sun Microsystems, JSP is similar to PHP 
and ASP, but uses the Java programming languag</p>
<img src= "https://docs.oracle.com/cd/A97688_16/generic.903/a97681/mvc.gif"></img>
</body>
</html>
