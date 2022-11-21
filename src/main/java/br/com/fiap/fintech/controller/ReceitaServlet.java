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

import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;


@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CrudDao<Receita> dao;
       private CrudDao<CatReceita> catReceitaDao;
    
    @Override
   	public void init() throws ServletException {
   		super.init();
   		dao = DaoFactory.getCrudReceitaDao();
   		catReceitaDao = DaoFactory.getCrudDaoCatReceita();
   	}

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastro":
			abrirFormCadastro(request, response);
			break;
		}
		
	}
    
    private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro-receita.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<CatReceita> lista = catReceitaDao.getAll();
		request.setAttribute("categorias", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Receita receita = (Receita) dao.buscarTodosPorCodigo(codigo);
		request.setAttribute("receita", receita);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
	}

	
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Receita> lista = dao.getAll();
			request.setAttribute("receitas", lista);
			request.getRequestDispatcher("lista-receita.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request,response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("msg", "receita removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}
	
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			double valor = Double.parseDouble(request.getParameter("valor"));
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(format.parse(request.getParameter("dtRecebimento")));
			int codigoCategoria = Integer.parseInt(request.getParameter("catReceita"));

			CatReceita catReceita = new CatReceita();
			catReceita.setCodigo(codigoCategoria);
			

			Receita receita = new Receita(codigo, nome, descricao,dtRecebimento,valor) ;
			receita.setCatReceita(catReceita);
			dao.atualizar(receita);

			request.setAttribute("msg", "Receita atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}
	
	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			double valor = Double.parseDouble(request.getParameter("valor"));
			Calendar dtRecebimento = Calendar.getInstance();
			dtRecebimento.setTime(format.parse(request.getParameter("dtRecebimento")));
			int codigo = Integer.parseInt(request.getParameter("catReceita"));
			
			CatReceita catReceita = new CatReceita();
			catReceita.setCodigo(codigo);
			Receita receita = new Receita(0, nome,descricao, dtRecebimento, valor); 
			receita.setCatReceita(catReceita);
			dao.cadastrar(receita);
			
			request.setAttribute("msg", "Receita cadastrada!");
		}catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("erro","Por favor, valide os dados");
		}
		listar(request, response);
	}
	}


