package br.com.eduardo.financeiro.controller.form;

import javax.validation.constraints.NotEmpty;

import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.repository.CategoriaRepository;

public class CategoriaForm {
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getById(id);
		categoria.setDescricao(this.descricao);
		return categoria;
	}
	
	public Categoria converter() {
		return new Categoria(descricao);
	}
}
