<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
 <form action="signupaction" method="post">
      Email:<br/><input type="text" name="email"/><br/>
      Password:<br/><input type="password" name="password"/><br/>
      Role:<br/><input type="text" name="Role"/><br/>
      <input type="submit" value="signup"/>		
   </form>
   <form action="loginaction" method="post">
      Email:<br/><input type="text" name="user"/><br/>
      Password:<br/><input type="password" name="password"/><br/>
      <input type="submit" value="Login"/>		
   </form>
</body>
</html>