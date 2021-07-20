package br.com.eduardo.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.repository.CategoriaRepository;

public class RequisicaoNovoLancamento {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@NotNull
	private String descricao;
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String vencimento;
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	private String valor;
	@NotNull
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
	
	

	public Lancamento toLancamento(CategoriaRepository categoriaRepository) {
		Categoria cat = categoriaRepository.findByDescricao(categoria);
		Lancamento lancamento = new Lancamento();
		lancamento.setDescricao(descricao);
		lancamento.setVencimento(LocalDate.parse(vencimento, formatter));
		lancamento.setValor(new BigDecimal(valor));
		lancamento.setCategoria(cat);
		return lancamento;
	}
	
	public String toString() {
		return getCategoria() + getDescricao() + getValor() + getVencimento();
	}
}
