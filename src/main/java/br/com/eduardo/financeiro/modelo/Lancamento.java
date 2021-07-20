package br.com.eduardo.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private LocalDate vencimento;
	private BigDecimal valor;
	@ManyToOne
	private Categoria categoria;
	@Enumerated(EnumType.STRING)
	private Situacao situacao = Situacao.ABERTO;
	private LocalDateTime data_criacao = LocalDateTime.now();
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

	
}
