<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 

    pageEncoding="ISO-8859-1"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container ">
	<div>
		<h1>Cadastro de usuário</h1>
			<c:if test="${not empty msg }"> 

				<div class="alert alert-success">${msg}</div> 

			</c:if> 

			<c:if test="${not empty erro }"> 
		   
				<div class="alert alert-danger">${erro}</div> 
		
			</c:if> 
			
		<form action="login" method="post" >
		<input type="hidden" value ="cadastrar" name="acao">
			<div class="col-12 mt-5" >
				<label for="id-nome">Email</label>
				<input type="text" name="email" id="id-nome" class="form-control">
			</div>
			<div class="col-12 mt-5">
				<label for="id-senha">Senha</label>
				<input type="text" name="senha" id="id-senha" class="form-control">
			</div>
		
			<div class="col-12 mt-5">
			
				<button type="submit" value="Salvar" class="btn btn-dark">Salvar</button>
			
			</div>
			
		</form>
	</div>
	
	
</div>
<%@ include file="footer.jsp" %>
</body>
</html>