package br.com.qm.loja.peca.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import br.com.qm.loja.peca.api.entity.Peca;


public class PecaDTO {

	@NotNull
	private long codBarras;

	@NotBlank
	private String nome;

	@NotBlank
	private String modeloCarro;

	@NotBlank
	private String fabricante;

	private float precoVenda;

	private int qtdEstoque;

	private String categoria;

	public PecaDTO(long codBarras, String nome, String modeloCarro, String fabricante, float precoVenda, int qtdEstoque,
			String categoria) {
		this.codBarras = codBarras;
		this.nome = nome;
		this.modeloCarro = modeloCarro;
		this.fabricante = fabricante;
		this.precoVenda = precoVenda;
		this.qtdEstoque = qtdEstoque;
		this.categoria = categoria;
	}
	
	public PecaDTO() {
	}

	public PecaDTO fromObject(Peca peca) {
		PecaDTO pecaDto = new PecaDTO();
		BeanUtils.copyProperties(peca, pecaDto);
		
		return pecaDto;
	}

	public long getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(long codBarras) {
		this.codBarras = codBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	

}