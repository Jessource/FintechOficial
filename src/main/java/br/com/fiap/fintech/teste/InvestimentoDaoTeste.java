package br.com.fiap.fintech.teste;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DaoFactory;

public class InvestimentoDaoTeste {

	public static void main(String[] args) {
		CrudDao<Investimento> dao = DaoFactory.getCrudDaoInvestimento();
		//Cadastra um investimento
		Investimento invest = new Investimento ();
		invest.setNome(" Carteira meu filho");
		invest.setValor(2000);
	
		
		
	
		try {
			dao.cadastrar(invest);;
		System.out.println("Investimento cadastrada.");
	} catch (DBException e) {
			e.printStackTrace();
		}
		System.out.println("______________________________");
		//Buscar um investimento pelo código e atualiza
		
		invest = (Investimento) dao.buscarTodosPorCodigo(21);
		invest.setNome("carteira para minha filha");
		invest.setValor(3000);
		
		try {
			dao.atualizar(invest);
			System.out.println("investimento atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		//Listar os investimentos
		System.out.println("_________________________________");
		System.out.println("Investimentos:");
		List<Investimento> lista = dao.getAll();
		for(Investimento item:lista) {
			System.out.println(item.getNome() +" valor: " + item.getValor());
		}
		//remove um investimento
      	System.out.println("_________________________________");
//		try {
//			dao.remover(23);
//			System.out.println("Investimento removido");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		

	}

}
