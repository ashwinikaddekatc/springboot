<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REAL_NET</title>
<script type="text/javascript">
	function submitOnEnter(inputElement, event) {
		if (event.keyCode == 13) {
			ValidateLogOn();
		}
	}

	function ValidateLogOn() {

		var userName = document.getElementById('user_name').value;
		var password = document.getElementById('password').value;

		if (userName == "") {
			alert("Enter Username.");
		} else if (password == "") {
			alert("Enter Password.");
		} else {
			var forms = document.getElementById('myform');
			forms.submit();
		}
	}
</script>
<style type="text/css">
.ui-menu {
	width: 150px;
}
</style>
</head>
<body>
   <div class="wrap">
    <div style="color: white;">${message}</div>
    <div class="avatar">
       <img  src="<c:url value='/resources/Content/images/omfys.png' />"/>
 	</div>
 	<form id="myform" action="login" method="post">
        <input type="text" name="username" placeholder="User Name" class="inputText" id="user_name"/>
			   <div class="bar">
                    <i></i>    
               </div>
                   
        <input type="password" id="password" name="password" class="inputText" placeholder="Password" onKeyPress="submitOnEnter(this, event)"/>                
       <button type="submit" id="login" class="btn" >Log On</button>
     </form>
      </div>
	
</body>
</html>