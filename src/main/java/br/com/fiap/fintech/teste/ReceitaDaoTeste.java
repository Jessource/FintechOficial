package br.com.fiap.fintech.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class ReceitaDaoTeste {

	public static void main(String[] args) {
		CrudDao<Receita> dao = DaoFactory.getCrudReceitaDao();
		//Cadastra uma receita
		Receita receita = new Receita ();
		receita.setNome(" MXtech");
		receita.setDescricao("pagamento adiantado");
		receita.setValor(2000);
		
		Calendar calendar = new GregorianCalendar(2022,10,23);
		receita.setDtRecebimento(calendar);
		
		
		
	
		try {
			dao.cadastrar(receita);;
		System.out.println("Receita cadastrada.");
	} catch (DBException e) {
			e.printStackTrace();
		}
		System.out.println("______________________________");
		//Buscar uma receita pelo código e atualiza
		
		receita = (Receita) dao.buscarTodosPorCodigo(1);
		receita.setNome("Mxntech ");
		receita.setDescricao("pagamento atrasado");
		
		try {
			dao.atualizar(receita);
			System.out.println("Receita atualizada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		//Listar as receitas
		System.out.println("_________________________________");
		System.out.println("Receitas:");
		List<Receita> lista = dao.getAll();
		for(Receita item:lista) {
			System.out.println(item.getNome()+" Valor: "+ item.getValor());
		}
		//remove uma meta
      	System.out.println("_________________________________");
//		try {
//			dao.remover(2);
//			System.out.println("Receita removida");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		

	}

}
