package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.TipoInvestimento;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;


@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CrudDao<Investimento> dao;
       private CrudDao<TipoInvestimento> tipoInvestDao;
       
    @Override
   	public void init() throws ServletException {
   		super.init();
   		dao = DaoFactory.getCrudDaoInvestimento();
   		tipoInvestDao = DaoFactory.getCrudTipoInvestimentoDao();
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
		carregarOpcoesTipo(request);
		request.getRequestDispatcher("cadastro-investimento.jsp").forward(request, response);
	}

	private void carregarOpcoesTipo(HttpServletRequest request) {
		List<TipoInvestimento> lista = tipoInvestDao.getAll();
		request.setAttribute("categorias", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Investimento invest = (Investimento) dao.buscarTodosPorCodigo(codigo);
		request.setAttribute("invest", invest);
		carregarOpcoesTipo(request);
		request.getRequestDispatcher("edicao-investimento.jsp").forward(request, response);
	}

	
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Investimento> lista = dao.getAll();
			request.setAttribute("investimentos", lista);
			request.getRequestDispatcher("lista-investimento.jsp").forward(request, response);	
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
			request.setAttribute("msg", "Investimento removido!");
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
			
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			
			int codigoTipo = Integer.parseInt(request.getParameter("tipo"));
			
			TipoInvestimento tipo = new TipoInvestimento();
			tipo.setCodigo(codigoTipo);
			
			Investimento invest = new Investimento(codigo, nome, valor); 
			
			invest.setTipo(tipo);
			dao.atualizar(invest);

			request.setAttribute("msg", "Investimento atualizado!");
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
			
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			
			int codigoTipo = Integer.parseInt(request.getParameter("tipo"));
			
			TipoInvestimento tipo = new TipoInvestimento();
			tipo.setCodigo(codigoTipo);
			Investimento invest = new Investimento(0, nome, valor); 
			invest.setTipo(tipo);
			dao.cadastrar(invest);
			request.setAttribute("msg", "Investimento cadastrado!");
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


