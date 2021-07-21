package br.com.eduardo.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.modelo.Situacao;

public class LancamentoDto {
	private Long id;
	private String descricao;
	private LocalDate vencimento;
	private BigDecimal valor;
	private Categoria categoria;
	private Situacao situacao;
	private LocalDateTime data_criacao;

	public LancamentoDto(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.descricao = lancamento.getDescricao();
		this.vencimento = lancamento.getVencimento();
		this.valor = lancamento.getValor();
		this.categoria = lancamento.getCategoria();
		this.situacao = lancamento.getSituacao();
		this.data_criacao = lancamento.getData_criacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getVencimento() {
		return vencimento;
	}

	public void setVencimento(LocalDate vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(LocalDateTime data_criacao) {
		this.data_criacao = data_criacao;
	}

	public static Page<LancamentoDto> converter(Page<Lancamento> lancamentos) {
		return lancamentos.map(LancamentoDto::new);
	}

}
