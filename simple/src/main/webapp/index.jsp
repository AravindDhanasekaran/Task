<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello world</title>
</head>
<body>
<h2>hello world</h2>

		<form action="/simple/Demo" method="post"> 
  			Name:
 			<input type="text" name="name" id="name" required="required" />
 			<br><br>
 			CompanyName
 			<input type="text" name="cname" id="cname">
 			<br><br>
 			First Name
 			<input type="text" name="fname" id="fname">
 			<br><br>
 			Last Name
 			<input type="text" name="lname" id="lname">
 			<br><br>
 			Email
 			<input type="text" name="mail" id="mail">
 			<br><br>
	
 			<input type="submit" value="Submit">
		</form> 
</body>
</html>


