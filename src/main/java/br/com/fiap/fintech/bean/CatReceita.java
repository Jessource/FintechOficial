package br.com.fiap.fintech.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CatReceita {
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;

	
	public CatReceita() {};

	public CatReceita(int codigo, String nome) {
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
		
		return "CatReceita [codigo=" + codigo + ", nome=" + nome + "]";
	}
	
	
	
	
	
	

}
