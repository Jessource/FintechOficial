package br.com.fiap.fintech.teste;

import java.util.List;

import br.com.fiap.fintech.bean.CatDespesa;
import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class CatDespesaDaoTeste {

	public static void main(String[] args) {
		CrudDao<CatDespesa> dao = DaoFactory.getCrudDaoCatDespesa();
		//Cadastrar uma categoria de despesa
		CatDespesa catDespesa = new CatDespesa();
		catDespesa.setNome("Outros");
		
		try {
			dao.cadastrar(catDespesa);;
		System.out.println("categoria cadastrada.");
	} catch (DBException e) {
			e.printStackTrace();
		}
		System.out.println("______________________________");
		//Buscar uma categoria pelo código e atualizar
		
		catDespesa = (CatDespesa) dao.buscarTodosPorCodigo(46);
		catDespesa.setNome("Cartão de Débito");
		try {
			dao.atualizar(catDespesa);
			System.out.println("categoria atualizada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		//Listar as categorias de receitas
		System.out.println("_________________________________");
		System.out.println("categorias:");
		List<CatDespesa> lista = dao.getAll();
		for(CatDespesa item:lista) {
			System.out.println(item.getNome());
		}
		//remove uma categoria de receita
		System.out.println("_________________________________");
		try {
			dao.remover(61);
			System.out.println("Categoria removida");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
