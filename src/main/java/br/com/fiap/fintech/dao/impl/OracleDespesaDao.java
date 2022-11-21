package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.CatDespesa;
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleDespesaDao implements CrudDao<Despesa> {
     private Connection conexao;
     PreparedStatement pstmt = null;
     
	@Override
	public void cadastrar(Despesa despesa) throws DBException {
	
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_DESPESA(COD_DESPESA, NM_DESPESA,DESCRICAO,VAL_DESPESA,DT_VENCIMENTO,DT_PAGAMENTO,COD_CAT_DESPESA) VALUES(SQ_T_DESPESA.NEXTVAL, ?,?,?,?,?,?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,despesa.getNome());
			pstmt.setString(2, despesa.getDescricao());
			pstmt.setDouble(3, despesa.getValor());
			
			java.sql.Date dataVenc = new java.sql.Date(despesa.getDataVenc().getTimeInMillis());
			pstmt.setDate(4, dataVenc);
			java.sql.Date dataPag = new java.sql.Date(despesa.getDataPag().getTimeInMillis());
			pstmt.setDate(5, dataPag);
			
			pstmt.setInt(6, despesa.getCatDespesa().getCodigo());
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
	public List<Despesa> getAll() {
		List <Despesa> listaDespesa = new ArrayList<Despesa>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_DESPESA INNER JOIN T_CAT_DESPESA ON T_DESPESA.COD_CAT_DESPESA = T_CAT_DESPESA.COD_CAT_DESPESA");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_DESPESA");
			   String nome = rs.getString("NM_DESPESA");
			   String descricao = rs.getString("DESCRICAO");
			   double valor = rs.getDouble("VAL_DESPESA");
			   
			   
			   int codCatDesp = rs.getInt("COD_CAT_DESPESA");
			   String nomeCategoria = rs.getString("NM_CAT_DESPESA");
				
			    
			   java.sql.Date data = rs.getDate("DT_VENCIMENTO");
			   Calendar dataVenc = Calendar.getInstance();
			   dataVenc.setTimeInMillis(data.getTime());
			    
			   java.sql.Date data2 = rs.getDate("DT_PAGAMENTO");
			   Calendar dataPag = Calendar.getInstance();
			   dataPag.setTimeInMillis(data2.getTime());
			   
			  
			   
			   Despesa despesa = new Despesa(codigo, nome, descricao, valor, dataVenc, dataPag);
			   CatDespesa catDespesa = new CatDespesa(codCatDesp, nomeCategoria);
			   despesa.setCatDespesa(catDespesa);
			   listaDespesa.add(despesa);
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
	   
		return listaDespesa;
	}
	

	@Override
	public Despesa buscarTodosPorCodigo(int codigo) {
		Despesa despesa = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_DESPESA  INNER JOIN T_CAT_DESPESA ON T_DESPESA.COD_CAT_DESPESA = T_CAT_DESPESA.COD_CAT_DESPESA WHERE T_DESPESA.COD_DESPESA=?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
			    codigo = rs.getInt("COD_DESPESA");
				String nome = rs.getString("NM_DESPESA");
				String descricao = rs.getString("DESCRICAO");
				double valor = rs.getDouble("VAL_DESPESA");
				
				java.sql.Date data = rs.getDate("DT_VENCIMENTO");
				Calendar dataVenc = Calendar.getInstance();
				dataVenc.setTimeInMillis(data.getTime());
				
				java.sql.Date data2 = rs.getDate("DT_PAGAMENTO");
				Calendar dataPag = Calendar.getInstance();
				dataPag.setTimeInMillis(data2.getTime());
				
				
				int codCatDesp = rs.getInt("COD_CAT_DESPESA");
				String nomeCategoria = rs.getString("NM_CAT_DESPESA");
				
				
				
				
				   
				   despesa = new Despesa(codigo, nome,descricao,valor,dataVenc,dataPag);
				   CatDespesa catDespesa = new CatDespesa(codCatDesp, nomeCategoria);
				   despesa.setCatDespesa(catDespesa);
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
		
		return despesa;
	}
	

	@Override
	public void atualizar(Despesa despesa)throws DBException {
		PreparedStatement pstmt = null; 

	      try { 

	    	  conexao = ConnectionManager.getInstance().getConnection();

	        String sql = "UPDATE T_DESPESA SET NM_DESPESA = ?, DESCRICAO = ?, VAL_DESPESA = ?, DT_VENCIMENTO = ?,DT_PAGAMENTO = ?,COD_CAT_DESPESA = ? WHERE COD_DESPESA = ?"; 

	        pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,despesa.getNome());
			pstmt.setString(2, despesa.getDescricao());
			pstmt.setDouble(3, despesa.getValor());
			
			java.sql.Date dataVenc = new java.sql.Date(despesa.getDataVenc().getTimeInMillis());
			pstmt.setDate(4, dataVenc);
			
			java.sql.Date dataPag = new java.sql.Date(despesa.getDataPag().getTimeInMillis());
			pstmt.setDate(5, dataPag);
			

			pstmt.setInt(6, despesa.getCatDespesa().getCodigo());
			pstmt.setInt(7, despesa.getCodigo());
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
			String sql = "DELETE FROM T_DESPESA WHERE COD_DESPESA = ?";
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
