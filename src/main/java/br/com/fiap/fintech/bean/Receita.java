package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Receita implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private String descricao;
	private Calendar dtRecebimento;
	private double valor;

	
	private CatReceita catReceita;
	
	
	public Receita(int codigo, String nome, String descricao, Calendar dtRecebimento, 
			double valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.dtRecebimento = dtRecebimento;

		this.valor = valor;

	}
	
	
	
	public Receita() {}
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(Calendar dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public CatReceita getCatReceita() {
		return catReceita;
	}
	public void setCatReceita(CatReceita catReceita) {
		this.catReceita = catReceita;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return "Receita [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", dtRecebimento =" + sdf.format(dtRecebimento.getTime()) + ", codUsuario=" +  ", valor=" + valor
				+ ", dataCriacao=" +  "]";
	};
	
	
	
	

}
