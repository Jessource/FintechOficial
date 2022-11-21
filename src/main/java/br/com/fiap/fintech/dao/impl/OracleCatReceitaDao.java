package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.bean.CatReceita;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;


public class OracleCatReceitaDao implements CrudDao<CatReceita> {
	private Connection conexao;
    PreparedStatement pstmt = null;
    
	@Override
	public void cadastrar(CatReceita catReceita)throws DBException {
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_CAT_RECEITA(COD_CAT_RECEITA, NM_CAT_RECEITA) VALUES(SQ_CAT_RECEITA.NEXTVAL, ?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,catReceita.getNome());
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
	public List<CatReceita> getAll() {
		List <CatReceita> listaCatReceita = new ArrayList<CatReceita>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_CAT_RECEITA");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_CAT_RECEITA");
			   String nome = rs.getString("NM_CAT_RECEITA");
			   
			
			   CatReceita catReceita = new CatReceita(codigo, nome);
			   listaCatReceita.add(catReceita);
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
	   
		return listaCatReceita;
	}

	@Override
	public Object buscarTodosPorCodigo(int codigo) {
		CatReceita catReceita = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_CAT_RECEITA WHERE COD_CAT_RECEITA = ?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
			    codigo = rs.getInt("COD_CAT_RECEITA");
				String nome = rs.getString("NM_CAT_RECEITA");
				
				catReceita = new CatReceita(codigo, nome);
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
		
		return catReceita;
	}

	@Override
	public void atualizar(CatReceita catReceita)throws DBException {
		PreparedStatement pstmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CAT_RECEITA SET NM_CAT_RECEITA =? WHERE COD_CAT_RECEITA = ?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, catReceita.getNome());
			pstmt.setInt(2, catReceita.getCodigo());
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
			String sql = "DELETE FROM T_CAT_RECEITA WHERE COD_CAT_RECEITA = ?";
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