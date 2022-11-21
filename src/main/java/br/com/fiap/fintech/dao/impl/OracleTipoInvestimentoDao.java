package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import br.com.fiap.fintech.bean.TipoInvestimento;
import br.com.fiap.fintech.dao.CrudDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleTipoInvestimentoDao implements CrudDao<TipoInvestimento> {
	private Connection conexao;
    PreparedStatement pstmt = null;
	@Override
	public void cadastrar(TipoInvestimento tipoInvestimento)throws DBException  {
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_TIPO_INVESTIMENTO(COD_TIPO_INVEST, NM_TIPO) VALUES(SQ_TIPO_INVESTIMENTO.NEXTVAL, ?)";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1,tipoInvestimento.getNome());
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
	public List<TipoInvestimento> getAll() {
		List <TipoInvestimento> listaTipoInvest = new ArrayList<TipoInvestimento>();
		ResultSet rs = null;
	   try {
		   conexao = ConnectionManager.getInstance().getConnection();
		   pstmt = conexao.prepareStatement("SELECT * FROM T_TIPO_INVESTIMENTO");
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   int codigo = rs.getInt("COD_TIPO_INVEST");
			   String nome = rs.getString("NM_TIPO");
			  
			   
			   TipoInvestimento tipoInvestimento = new TipoInvestimento(codigo,nome);
			   listaTipoInvest.add(tipoInvestimento);
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
	   
		return listaTipoInvest;
	}
	

	@Override
	public Object buscarTodosPorCodigo(int codigo) {
		TipoInvestimento tipoInvestimento = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			pstmt = conexao.prepareStatement("SELECT * FROM T_TIPO_INVESTIMENTO WHERE COD_TIPO_INVEST = ?");
			pstmt.setInt(1, codigo);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
			    codigo = rs.getInt("COD_TIPO_INVEST");
				String nome = rs.getString("NM_TIPO");

				tipoInvestimento = new TipoInvestimento(codigo,nome);
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
		
		return tipoInvestimento;
	}

	@Override
	public void atualizar(TipoInvestimento tipoInvestimento)throws DBException {
		PreparedStatement pstmt = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_TIPO_INVESTIMENTO SET NM_TIPO =? WHERE COD_TIPO_INVEST = ?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, tipoInvestimento.getNome());
			pstmt.setInt(2, tipoInvestimento.getCodigo());
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
			String sql = "DELETE FROM T_TIPO_INVESTIMENTO WHERE COD_TIPO_INVEST = ?";
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
