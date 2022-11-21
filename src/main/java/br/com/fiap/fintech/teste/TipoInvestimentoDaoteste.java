package br.com.fiap.fintech.teste;

import java.util.List;
import br.com.fiap.fintech.bean.TipoInvestimento;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class TipoInvestimentoDaoteste {

	public static void main(String[] args) {
		CrudDao<TipoInvestimento> dao = DaoFactory.getCrudTipoInvestimentoDao();
		//Cadastrar um tipo de Investimento
		TipoInvestimento tipo = new TipoInvestimento ();
		tipo.setNome("Outros");
		
		try {
			dao.cadastrar(tipo);;
		System.out.println("tipo cadastrado.");
	} catch (DBException e) {
			e.printStackTrace();
		}
		System.out.println("______________________________");
		//Buscar um tipo pelo código e atualizar
		
		tipo = (TipoInvestimento) dao.buscarTodosPorCodigo(43);
		tipo.setNome("Cbd");
		try {
			dao.atualizar(tipo);
			System.out.println("Tipo atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		//Listar os tipos de investimentos
		System.out.println("_________________________________");
		System.out.println("tipos:");
		List<TipoInvestimento> lista = dao.getAll();
		for(TipoInvestimento item:lista) {
			System.out.println(item.getNome());
		}
		//remove um tipo de investimento
		System.out.println("_________________________________");
		try {
			dao.remover(61);
			System.out.println("Categoria removida");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	}


