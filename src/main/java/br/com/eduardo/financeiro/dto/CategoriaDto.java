package br.com.eduardo.financeiro.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Status;

public class CategoriaDto {
	private Long id;
	private String Descricao;
	private Status status;
	private LocalDateTime data_criacao;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		Descricao = categoria.getDescricao();
		this.status = categoria.getStatus();
		this.data_criacao = categoria.getData_criacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(LocalDateTime data_criacao) {
		this.data_criacao = data_criacao;
	}

	public static Page<CategoriaDto> converter(Page<Categoria> categorias) {
		return categorias.map(CategoriaDto::new);
	}
	
	
	
}
