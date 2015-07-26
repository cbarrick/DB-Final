<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<center>
        <h3>Welcome <i>${sessionScope.user}</i>, you have logged in successfully!</h3>
       <form action="logout" method="post">
      <input type="submit" value="logout"/>		
   </form>
    </center>
</body>
</html>