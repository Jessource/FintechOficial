package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.bean.CatDespesa;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;


public class OracleCatDespesaDao implements CrudDao<CatDespesa> {
	private Connection conexao;
    PreparedStatement pstmt = null;
    
	@Override
	public void cadastrar(CatDespesa catDespesa) throws DBException {
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			
			String sql = "INSERT INTO T_CAT_DESPESA(COD_CAT_DESPESA, NM_CAT_DESPESA) VALUES(SQ_CAT_DESPESA.NEXTVAL, ?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,catDespesa.getNome());
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
	public List<CatDespesa> getAll() {
		List <CatDespesa> listaCatDespesa = new ArrayList<CatDespesa>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_CAT_DESPESA");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_CAT_DESPESA");
			   String nome = rs.getString("NM_CAT_DESPESA");
			   
			   CatDespesa catDespesa = new CatDespesa(codigo, nome);
			   listaCatDespesa.add(catDespesa);
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
	   
		return listaCatDespesa;
	}

	@Override
	public Object buscarTodosPorCodigo(int codigo) {
		CatDespesa catDespesa = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_CAT_DESPESA WHERE COD_CAT_DESPESA = ?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
			    codigo = rs.getInt("COD_CAT_DESPESA");
				String nome = rs.getString("NM_CAT_DESPESA");
				
				catDespesa = new CatDespesa(codigo, nome);
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
		
		return catDespesa;
	}

	@Override
	public void atualizar(CatDespesa catDespesa) throws DBException {
		PreparedStatement pstmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CAT_DESPESA SET NM_CAT_DESPESA =? WHERE COD_CAT_DESPESA = ?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, catDespesa.getNome());
			pstmt.setInt(2, catDespesa.getCodigo());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		}finally {
			try {
				pstmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}

	@Override
	public void remover(int codigo)throws DBException {
		PreparedStatement pstmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_CAT_DESPESA WHERE COD_CAT_DESPESA = ?";
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


