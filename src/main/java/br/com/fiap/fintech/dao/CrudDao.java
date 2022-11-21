package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.exception.DBException;



public interface CrudDao<T> {
	void cadastrar (T object) throws DBException;
	List<T> getAll();
	Object buscarTodosPorCodigo(int codigo);
	void atualizar(T object)throws DBException;
	void remover(int codigo)throws DBException;
}
 