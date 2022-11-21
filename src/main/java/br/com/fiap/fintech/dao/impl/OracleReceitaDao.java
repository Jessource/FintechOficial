package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleReceitaDao implements CrudDao<Receita> {
    private Connection conexao;
    PreparedStatement pstmt = null;
    
	@Override
	public void cadastrar(Receita receita)throws DBException {
	
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_RECEITA(COD_RECEITA, NM_RECEITA,DESCRICAO,VAL_RECEITA,DT_RECEBIMENTO,COD_CAT_RECEITA) VALUES(SQ_T_RECEITA.NEXTVAL, ?,?,?,?,?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,receita.getNome());
			pstmt.setString(2, receita.getDescricao());
			pstmt.setDouble(3, receita.getValor());
		
			java.sql.Date dtRecebimento = new java.sql.Date(receita.getDtRecebimento().getTimeInMillis());
			pstmt.setDate(4, dtRecebimento);
			
			pstmt.setInt(5, receita.getCatReceita().getCodigo());
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
	public List<Receita> getAll() {
		List <Receita> listaReceita = new ArrayList<Receita>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_RECEITA INNER JOIN T_CAT_RECEITA ON T_RECEITA.COD_CAT_RECEITA = T_CAT_RECEITA.COD_CAT_RECEITA ");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_RECEITA");
			   String nome = rs.getString("NM_RECEITA");
			   String descricao = rs.getString("DESCRICAO");
			   double valor = rs.getDouble("VAL_RECEITA");
			 
			   int codCatRec = rs.getInt("COD_CAT_RECEITA");
			   String nomeCategoria = rs.getString("NM_CAT_RECEITA");
			   
		


			   java.sql.Date dataReceita = rs.getDate("DT_RECEBIMENTO");
			   Calendar dtRecebimento = Calendar.getInstance();
			   dtRecebimento.setTimeInMillis(dataReceita.getTime());
			   
			   
			   Receita receita = new Receita(codigo, nome, descricao, dtRecebimento,valor);
			   CatReceita catReceita = new CatReceita(codCatRec, nomeCategoria);
			   receita.setCatReceita(catReceita);
			   listaReceita.add(receita);
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
	   
		return listaReceita;
	}
	

	@Override
	public Receita  buscarTodosPorCodigo(int codigo) {
		Receita receita = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_RECEITA INNER JOIN T_CAT_RECEITA ON T_RECEITA.COD_CAT_RECEITA = T_CAT_RECEITA.COD_CAT_RECEITA WHERE T_RECEITA.COD_RECEITA =?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				   codigo = rs.getInt("COD_RECEITA");
				   String nome = rs.getString("NM_RECEITA");
				   String descricao = rs.getString("DESCRICAO");
				   double valor = rs.getDouble("VAL_RECEITA");
				   
				   java.sql.Date dataReceita = rs.getDate("DT_RECEBIMENTO");
				   Calendar dtRecebimento = Calendar.getInstance();
				   dtRecebimento.setTimeInMillis(dataReceita.getTime());
	
				   int codCatRec = rs.getInt("COD_CAT_RECEITA");
				   String nomeCategoria = rs.getString("NM_CAT_RECEITA");
				   
				
				  
				   
				  
				   
				   receita = new Receita(codigo, nome, descricao, dtRecebimento,valor);
				   CatReceita catReceita = new CatReceita(codCatRec, nomeCategoria);
				   receita.setCatReceita(catReceita);
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
		
		return receita;
	}
	

	@Override
	public void atualizar(Receita receita)throws DBException {
		PreparedStatement pstmt = null; 

	      try { 

	    	  conexao = ConnectionManager.getInstance().getConnection();

	    	  String sql = "UPDATE T_RECEITA SET NM_RECEITA = ?, DESCRICAO = ?, VAL_RECEITA = ?, DT_RECEBIMENTO = ?,COD_CAT_RECEITA =? WHERE COD_RECEITA = ?"; 

	        pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,receita.getNome());
			pstmt.setString(2, receita.getDescricao());
			pstmt.setDouble(3, receita.getValor());
			
			java.sql.Date dtRecebimento = new java.sql.Date(receita.getDtRecebimento().getTimeInMillis());
			pstmt.setDate(4, dtRecebimento);
			pstmt.setInt(5, receita.getCatReceita().getCodigo());
			pstmt.setInt(6, receita.getCodigo());
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
			String sql = "DELETE FROM T_RECEITA WHERE COD_RECEITA = ?";
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