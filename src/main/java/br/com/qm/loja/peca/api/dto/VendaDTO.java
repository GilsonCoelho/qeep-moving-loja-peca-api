package br.com.qm.loja.peca.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.qm.loja.peca.api.entity.Venda;



public class VendaDTO {

	@NotNull
	private Long idVenda;
	
	@NotNull
	private Long codBarras;
	
	@NotNull
	@Min(value = 1)
	private Integer quantidade;
	
	@NotBlank
	private String nomeVendedor;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataVenda;

	@NotBlank
	private String formaPagamento;

	public VendaDTO(long idVenda, long codBarras, int quantidade, String nomeVendedor, LocalDate dataVenda,
			String formaPagamento) {
		this.idVenda = idVenda;
		this.codBarras = codBarras;
		this.quantidade = quantidade;
		this.nomeVendedor = nomeVendedor;
		this.dataVenda = dataVenda;
		this.formaPagamento = formaPagamento;
	}

	public VendaDTO() {
	}

	public Venda toObject() {
		
		Venda venda = new Venda();
		BeanUtils.copyProperties(this, venda);
		
		return venda;
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
	
}