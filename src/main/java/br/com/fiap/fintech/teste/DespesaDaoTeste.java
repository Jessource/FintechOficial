
package br.com.fiap.fintech.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class DespesaDaoTeste {

	public static void main(String[] args) {
		CrudDao<Despesa> dao = DaoFactory.getCrudDaoDespesa();
		//Cadastra uma despesa
		Despesa despesa= new Despesa ();
		despesa.setNome("Aluguel da casa de angras");
		despesa.setDescricao("pagamento adiantado");
	
		
		Calendar calendar = new GregorianCalendar(2022,10,21);
		despesa.setDataVenc(calendar);
		Calendar calendar2 = new GregorianCalendar(2022,10,21);
		despesa.setDataPag(calendar2);
		

		
		
	
		try {
			dao.cadastrar(despesa);;
		System.out.println("Despesa cadastrada.");
	} catch (DBException e) {
			e.printStackTrace();
		}
		System.out.println("______________________________");
		//Buscar uma despesa pelo código e atualiza
		
		despesa = (Despesa) dao.buscarTodosPorCodigo(41);
		despesa.setNome("Aluguel casa de praia ");
		despesa.setDescricao("pagamento atrasado");
		
		try {
			dao.atualizar(despesa);
			System.out.println("despesa atualizada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		//Listar as metas
		System.out.println("_________________________________");
		System.out.println("Despesas:");
		List<Despesa> lista = dao.getAll();
		for(Despesa item:lista) {
			System.out.println(item.getNome());
		}
		//remove uma meta
//		System.out.println("_________________________________");
		try {
			dao.remover(44);
			System.out.println("Despesa removida");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
