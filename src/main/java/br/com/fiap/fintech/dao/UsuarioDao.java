package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.exception.DBException;

public interface UsuarioDao {
	boolean validarUsuario(Usuario usuario);
	void cadastrar (Usuario usuario) throws DBException;
}
