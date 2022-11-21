package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Despesa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private String descricao;
	private Calendar dataVenc;
	private Calendar dataPag;
	private double valor;

	
	private CatDespesa catDespesa;
	
	public Despesa() {};


	public Despesa(int codigo, String nome, String descricao, Double valor, Calendar dataVenc, Calendar dataPag
			 ) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.dataVenc = dataVenc;
		this.dataPag = dataPag;
		this.valor = valor;
		
		
	}



	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Calendar getDataVenc() {
		return dataVenc;
	}


	public void setDataVenc(Calendar dataVenc) {
		this.dataVenc = dataVenc;
	}


	public Calendar getDataPag() {
		return dataPag;
	}


	public void setDataPag(Calendar dataPag) {
		this.dataPag = dataPag;
	}

	



	public CatDespesa getCatDespesa() {
		return catDespesa;
	}


	public void setCatDespesa(CatDespesa catDespesa) {
		this.catDespesa = catDespesa;
	}


	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return "Despesa [codigo =" + codigo + ", nome =" + nome + ", descricao =" + descricao +", valor = " + valor + ", vencimento = " + sdf.format(dataVenc.getTime())
				+ ", pagamento =" + sdf.format(dataPag.getTime())  + "]";
	}


	

}
