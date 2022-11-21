package br.com.fiap.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.bo.EmailBO;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.exception.EmailException;
import br.com.fiap.fintech.factory.DaoFactory;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao dao;
	private EmailBO bo;
  
	public LoginServlet() {
        dao = DaoFactory.getUsuarioDao();
        bo = new EmailBO();
    }
	
	protected void doGet2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		
		case "abrir-form-cadastro":
			abrirFormCadastro(request, response);
			break;
		}
		
	}
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.getRequestDispatcher("cadastro-usuario.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(email, senha);
		
		if (dao.validarUsuario(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
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