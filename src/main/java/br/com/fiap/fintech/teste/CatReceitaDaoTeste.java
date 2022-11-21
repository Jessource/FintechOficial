package br.com.fiap.fintech.teste;



import java.util.List;

import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class CatReceitaDaoTeste {

	public static void main(String[] args) {
		//Cadastrando uma categoria de receita
			
				CrudDao<CatReceita> dao = DaoFactory.getCrudDaoCatReceita();
				//Cadastrar uma categoria de receita
				CatReceita catReceita = new CatReceita();
				catReceita.setNome("Categoria");
				
				try {
					dao.cadastrar(catReceita);;
					System.out.println("categoria cadastrada.");
				} catch (DBException e) {
					e.printStackTrace();
				}
				System.out.println("______________________________");
				//Buscar uma categoria pelo código e atualizar
				
				catReceita = (CatReceita) dao.buscarTodosPorCodigo(20);
				catReceita.setNome("Salário Pj");
				try {
					dao.atualizar(catReceita);
					System.out.println("categoria atualizada.");
				} catch (DBException e) {
					e.printStackTrace();
				}
				//Listar as categorias de receitas
				System.out.println("_________________________________");
				List<CatReceita> lista = dao.getAll();
				for(CatReceita item:lista) {
					System.out.println(item.getNome());
				}
				//remove uma categoria de receita
				System.out.println("_________________________________");
				try {
					dao.remover(27);
					System.out.println("Categoria removida");
				} catch (Exception e) {
					// TODO: handle exception
				}
	}

}
