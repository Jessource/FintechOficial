package br.com.fiap.fintech.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TipoInvestimento {
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	
	
	

	public TipoInvestimento(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		
	}
	
	public TipoInvestimento() {};
	
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
		return "Tipo Investimento [codigo = " + codigo + " data criação = " + ", nome = " + nome + "]";
	};
	
	

}
