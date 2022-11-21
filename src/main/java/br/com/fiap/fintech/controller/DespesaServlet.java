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

import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;


@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CrudDao<Despesa> dao;
       private CrudDao<CatDespesa> catDespesaDao;
       
    @Override
   	public void init() throws ServletException {
   		super.init();
   		dao = DaoFactory.getCrudDaoDespesa();
   		catDespesaDao = DaoFactory.getCrudDaoCatDespesa();
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
		carregarOpcoesCategoriaDesp(request);
		request.getRequestDispatcher("cadastro-despesa.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoriaDesp(HttpServletRequest request) {
		List<CatDespesa> lista = catDespesaDao.getAll();
		request.setAttribute("categorias", lista);
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Despesa despesa = (Despesa) dao.buscarTodosPorCodigo(codigo);
		request.setAttribute("despesa", despesa);
		carregarOpcoesCategoriaDesp(request);
		request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
	}

	
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Despesa> lista = dao.getAll();
			request.setAttribute("despesas", lista);
			request.getRequestDispatcher("lista-despesa.jsp").forward(request, response);	
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
			request.setAttribute("msg", "Despesa removido!");
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
			Calendar dataVenc = Calendar.getInstance();
			dataVenc.setTime(format.parse(request.getParameter("dataVenc")));
			
			SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPag = Calendar.getInstance();
			dataPag.setTime(format2.parse(request.getParameter("dataPag")));
			
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			
			int codigoCategoria = Integer.parseInt(request.getParameter("catDespesa"));
			
			CatDespesa catDespesa = new CatDespesa();
			catDespesa .setCodigo(codigoCategoria);
			Despesa despesa = new Despesa(codigo, nome,descricao, valor,dataVenc,dataPag); 
			despesa.setCatDespesa(catDespesa);
			dao.cadastrar(despesa);

			request.setAttribute("msg", "Despesa atualizado!");
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
			Calendar dataVenc = Calendar.getInstance();
			dataVenc.setTime(format.parse(request.getParameter("dataVenc")));
			
			SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPag = Calendar.getInstance();
			dataPag.setTime(format2.parse(request.getParameter("dataPag")));
			
			double valor = Double.parseDouble(request.getParameter("valor"));
			
			
			int codigo = Integer.parseInt(request.getParameter("catDespesa"));
			
			CatDespesa catDespesa = new CatDespesa();
			catDespesa .setCodigo(codigo);
			Despesa despesa = new Despesa(0, nome,descricao, valor,dataVenc,dataPag); 
			despesa.setCatDespesa(catDespesa);
			dao.cadastrar(despesa);
			
			request.setAttribute("msg", "Despesa cadastrada!");
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


