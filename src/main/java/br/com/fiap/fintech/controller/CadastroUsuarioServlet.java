package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.CatDespesa;
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;


@WebServlet("/cadastroUser")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UsuarioDao dao;
      
       
    @Override
   	public void init() throws ServletException {
   		super.init();
   		dao = DaoFactory.getUsuarioDao();
   		
   	}

   
	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
	
			Usuario usuario = new Usuario( email,senha); 
			dao.cadastrar(usuario);
			
			request.setAttribute("msg", "Usuário cadastrado!");
		}catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	}


