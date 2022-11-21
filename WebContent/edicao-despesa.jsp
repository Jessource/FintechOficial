<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 

    pageEncoding="ISO-8859-1"%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualiza��o de Despesa</title>
<%@ include file="header.jsp" %>
</head>
<body>
<%@ include file="menu.jsp" %>
<div class="container ">
	<div>
		<h1>Edi��o de Despesa</h1>
		<form action="despesa" method="post" >
		<input type ="hidden" value ="editar" name ="acao">
		<input type ="hidden" value ="${despesa.codigo }" name ="codigo">
			<div class="col-12 mt-5" >
				<label for="id-nome">Nome</label>
				<input type="text" name="nome" id="id-nome" class="form-control" value="${despesa.nome}" >
			</div>
			<div class="col-12 mt-5">
				<label for="id-descricao">Descri��o</label>
				<input type="text" name="descricao" id="id-descricao" class="form-control" value="${despesa.descricao}">
			</div>
			<div class="col-12 mt-5">
				<label for="id-valor">Valor</label>
				<input type="number" name="valor" id="id-valor" class="form-control" value="${despesa.valor}">
			</div>
			
			<div class="col-4 mt-5">
				<div class="form-group">
				    <label for="dataVenc">Data de Vencimento</label>
					<input type="text" name="dataVenc" value='<fmt:formatDate value="${despesa.dataVenc.time}" pattern="dd/MM/yyyy"/>' class="input-area form-text form-control" required id="date" required maxlength="10" formControlName="dataVenc"/>
				</div>
			</div>
			<div class="col-4 mt-5">
				<div class="form-group">
				    <label for="dataPag">Data de Pagamento</label>
					<input type="text" name="dataPag" value='<fmt:formatDate value="${despesa.dataPag.time}" pattern="dd/MM/yyyy"/>' class="input-area form-text form-control" required id="date" required maxlength="10" formControlName="dataPag"/>
				</div>
			</div>
			<div class="form-group">
			<label for="id-categoria">Categoria</label>
			<select name="catDespesa" id="id-categoria" class="form-control">
				<option value="0">Selecione</option>
				<c:forEach items="${categorias}" var="c">
					<c:if test="${c.codigo == despesa.catDespesa.codigo}">
						<option value="${c.codigo}" selected>${c.nome}</option>
					</c:if>
					<c:if test="${c.codigo != despesa.catDespesa.codigo}">
						<option value="${c.codigo}">${c.nome }</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
			<div class="col-12 mt-5">
			
			    <button type="submit"value="Salvar" class="btn btn-dark">Salvar</button>
			
				<a href="despesa?acao=listar" class="btn btn-danger ">Cancelar</a>
			
			</div>
			
		</form>
	</div>
	
	
</div>
<%@ include file="footer.jsp" %>
</body>
</html>