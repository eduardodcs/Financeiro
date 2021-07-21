package br.com.eduardo.financeiro.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.repository.CategoriaRepository;
import br.com.eduardo.financeiro.repository.LancamentoRepository;

public class LancamentoForm {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@NotEmpty
	private String descricao;
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotEmpty
	private String vencimento;
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotEmpty
	private String valor;
	@NotEmpty
	private String categoria;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Lancamento atualizar(Long id, LancamentoRepository lancamentoRepository, CategoriaRepository categoriaRepository) {
		Lancamento lancamento = lancamentoRepository.getById(id);
		Categoria objCategoria = categoriaRepository.findByDescricao(descricao);
		lancamento.setDescricao(this.descricao);
		lancamento.setValor(new BigDecimal(this.valor));
		lancamento.setCategoria(objCategoria);
		lancamento.setVencimento(LocalDate.parse(this.vencimento, formatter));		
		return lancamento;
	}

	public Lancamento converter(CategoriaRepository categoriaRepository) {
		Categoria objCategoria = categoriaRepository.findByDescricao(descricao);
		LocalDate vencimentoNovo = LocalDate.parse(this.vencimento, formatter);
		BigDecimal valorNovo = new BigDecimal(valor);
		return new Lancamento(descricao, vencimentoNovo, valorNovo, objCategoria);
	}
	
}
