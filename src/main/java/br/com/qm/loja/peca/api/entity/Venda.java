package br.com.qm.loja.peca.api.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Venda {

	@Id
	@Column(name = "id_venda")
	private long idVenda;
	
	@Column(name = "cod_barras")
	private long codBarras;
	
	private int quantidade;
	
	@Column(name = "nome_vendedor")
	private String nomeVendedor;

	@Column(name = "data_venda")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataVenda;

	@Column(name = "forma_pagamento")
	private String formaPagamento;
	
	@Column(name = "valor_venda")
	private Float valorVenda;

	public Venda(long idVenda, long codBarras, int quantidade, String nomeVendedor, LocalDate dataVenda,
			String formaPagamento) {
		this.idVenda = idVenda;
		this.codBarras = codBarras;
		this.quantidade = quantidade;
		this.nomeVendedor = nomeVendedor;
		this.dataVenda = dataVenda;
		this.formaPagamento = formaPagamento;
	}

	public Venda() {
	}

	public long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(long idVenda) {
		this.idVenda = idVenda;
	}

	public long getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(long codBarras) {
		this.codBarras = codBarras;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Float valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codBarras ^ (codBarras >>> 32));
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + (int) (idVenda ^ (idVenda >>> 32));
		result = prime * result + ((nomeVendedor == null) ? 0 : nomeVendedor.hashCode());
		result = prime * result + quantidade;
		result = prime * result + ((valorVenda == null) ? 0 : valorVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codBarras != other.codBarras)
			return false;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (idVenda != other.idVenda)
			return false;
		if (nomeVendedor == null) {
			if (other.nomeVendedor != null)
				return false;
		} else if (!nomeVendedor.equals(other.nomeVendedor))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (valorVenda == null) {
			if (other.valorVenda != null)
				return false;
		} else if (!valorVenda.equals(other.valorVenda))
			return false;
		return true;
	}
	
	
}