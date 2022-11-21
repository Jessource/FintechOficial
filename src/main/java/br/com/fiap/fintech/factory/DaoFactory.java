package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.bean.CatDespesa;
import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.bean.Investimento;

import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.bean.TipoInvestimento;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.dao.UsuarioDao;
import br.com.fiap.fintech.dao.impl.OracleCatDespesaDao;
import br.com.fiap.fintech.dao.impl.OracleCatReceitaDao;
import br.com.fiap.fintech.dao.impl.OracleDespesaDao;
import br.com.fiap.fintech.dao.impl.OracleInvestimentoDao;

import br.com.fiap.fintech.dao.impl.OracleReceitaDao;
import br.com.fiap.fintech.dao.impl.OracleTipoInvestimentoDao;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDao;

public class DaoFactory {

	public static CrudDao<CatDespesa> getCrudDaoCatDespesa() {
		return new OracleCatDespesaDao();
	}
	
	public static CrudDao<CatReceita> getCrudDaoCatReceita() {
		return new OracleCatReceitaDao();
	}
	public static CrudDao<Despesa> getCrudDaoDespesa() {
		return new OracleDespesaDao();
	}
	public static CrudDao<Investimento> getCrudDaoInvestimento() {
		return new OracleInvestimentoDao();
	}
	
	public static CrudDao<Receita> getCrudReceitaDao() {
		return new OracleReceitaDao();
	}
	public static CrudDao<TipoInvestimento> getCrudTipoInvestimentoDao() {
		return new OracleTipoInvestimentoDao();
	}
	public static UsuarioDao  getUsuarioDao() {
		return new OracleUsuarioDao();
	}
	
}