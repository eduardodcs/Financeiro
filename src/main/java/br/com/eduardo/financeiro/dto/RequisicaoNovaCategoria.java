package br.com.eduardo.financeiro.dto;

import javax.validation.constraints.NotBlank;

import br.com.eduardo.financeiro.modelo.Categoria;

public class RequisicaoNovaCategoria {
	
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria toCategoria() {
		Categoria categoria = new Categoria();
		categoria.setDescricao(descricao);
		return categoria;
	}
}
