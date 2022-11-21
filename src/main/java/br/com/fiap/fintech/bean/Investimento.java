package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Investimento implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nome;
	private double valor;
	private TipoInvestimento tipo;
	public Investimento() {}

	public Investimento(int codigo, String nome,  double valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	
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

	

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public TipoInvestimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoInvestimento tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Investimento [codigo = " + codigo + ", nome " + nome + ", codigo tipo = " 
				+   ", valor = " + valor + "]";
	};
	
	
	


}
