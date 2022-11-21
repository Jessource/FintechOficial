package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.bean.TipoInvestimento;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleInvestimentoDao implements CrudDao<Investimento>{
	private Connection conexao;
    PreparedStatement pstmt = null;
    
	@Override
	public void cadastrar(Investimento investimento)throws DBException {
	
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_INVESTIMENTO(COD_INVEST, NM_INVEST,VAL_INVEST,COD_TIPO_INVEST) VALUES(SQ_T_INVESTIMENTO.NEXTVAL, ?,?,?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,investimento.getNome());
			pstmt.setDouble(2, investimento.getValor());
			pstmt.setInt(3, investimento.getTipo().getCodigo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar.");
		}finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				// TODO: handle exception
			}
	}
		
	
	@Override
	public List<Investimento> getAll() {
		List <Investimento> listaInvest = new ArrayList<Investimento>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_INVESTIMENTO INNER JOIN T_TIPO_INVESTIMENTO ON T_INVESTIMENTO.COD_TIPO_INVEST = T_TIPO_INVESTIMENTO.COD_TIPO_INVEST");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_INVEST");
			   String nome = rs.getString("NM_INVEST");
			   double valor = rs.getDouble("VAL_INVEST");
			   int codTipoInvest = rs.getInt("COD_TIPO_INVEST");
			   String nomeTipo = rs.getString("NM_TIPO");

			  
			   
			   Investimento investimento = new Investimento(codigo, nome, valor);
			   TipoInvestimento tipo = new TipoInvestimento(codTipoInvest,nomeTipo);
			   investimento.setTipo(tipo);
			   listaInvest.add(investimento);
		   }
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	   
		return listaInvest;
	}
	

	@Override
	public Investimento buscarTodosPorCodigo(int codigo) {
		Investimento investimento = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_INVESTIMENTO INNER JOIN T_TIPO_INVESTIMENTO ON T_INVESTIMENTO.COD_TIPO_INVEST = T_TIPO_INVESTIMENTO.COD_TIPO_INVEST WHERE T_INVESTIMENTO.COD_INVEST = ?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				   codigo = rs.getInt("COD_INVEST");
				   String nome = rs.getString("NM_INVEST");
				   double valor = rs.getDouble("VAL_INVEST");
				   int codTipoInvest = rs.getInt("COD_TIPO_INVEST");
				   String nomeTipo = rs.getString("NM_TIPO");

				   
				   investimento = new Investimento(codigo, nome, valor);
				   TipoInvestimento tipo = new TipoInvestimento(codTipoInvest,nomeTipo);
				   investimento.setTipo(tipo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conexao.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return investimento;
	}
	

	@Override
	public void atualizar(Investimento investimento)throws DBException {
		PreparedStatement pstmt = null; 

	      try { 

	    	  conexao = ConnectionManager.getInstance().getConnection();

	        String sql = "UPDATE T_INVESTIMENTO SET NM_INVEST = ?, VAL_INVEST = ?, COD_TIPO_INVEST = ? WHERE COD_INVEST = ?"; 

	        pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,investimento.getNome());
			pstmt.setDouble(2,investimento.getValor());
			pstmt.setInt(3, investimento.getTipo().getCodigo());
			pstmt.setInt(4, investimento.getCodigo());
			
			
			pstmt.executeUpdate();

	      } catch (SQLException e) { 
	        e.printStackTrace(); 
	        throw new DBException("Erro ao atualizar.");

	      } finally { 

	        try { 

	          pstmt.close(); 

	          conexao.close(); 

	        } catch (SQLException e) { 

	          e.printStackTrace(); 

	        } 

	      } 

	    } 
		
	@Override
	public void remover(int codigo)throws DBException {
		PreparedStatement pstmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_INVESTIMENTO WHERE COD_INVEST = ?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		}finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	

}
