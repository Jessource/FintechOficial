
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 

    pageEncoding="ISO-8859-1"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de investimento</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container ">
	<div>
		<h1>Edição de investimento</h1>
		<form action="investimento" method="post" >
		<input type ="hidden" value ="editar" name ="acao">
		<input type ="hidden" value ="${invest.codigo }" name ="codigo">
			<div class="col-12 mt-5" >
				<label for="id-nome">Nome</label>
				<input type="text" name="nome" id="id-nome" class="form-control" value="${invest.nome}" >
			</div>
			
			<div class="col-12 mt-5">
				<label for="id-valor">Valor</label>
				<input type="number" name="valor" id="id-valor" class="form-control" value="${invest.valor}">
			</div>
			
			
			<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<select name="tipo" id="id-categoria" class="form-control">
				<option value="0">Selecione</option>
				<c:forEach items="${categorias}" var="c">
					<c:if test="${c.codigo == invest.tipo.codigo}">
						<option value="${c.codigo}" selected>${c.nome}</option>
					</c:if>
					<c:if test="${c.codigo != invest.tipo.codigo}">
						<option value="${c.codigo}">${c.nome }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
			<div class="col-12 mt-5">
			
			    <button type="submit"value="Salvar" class="btn btn-dark">Salvar</button>
			
				<a href="investimento?acao=listar" class="btn btn-danger ">Cancelar</a>
			
			</div>
			
		</form>
	</div>
	
	
</div>
<%@ include file="footer.jsp" %>
</body>
</html>