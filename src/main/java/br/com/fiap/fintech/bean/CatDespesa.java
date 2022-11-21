package br.com.fiap.fintech.bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CatDespesa {
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	
	
	
	public CatDespesa() {};

	public CatDespesa(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public String toString() {
		return "Categoria Despesa [codigo =" + codigo +", nome = " + nome + "]";
	}
	
	
	

}
