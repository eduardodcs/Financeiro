package br.com.eduardo.financeiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.financeiro.modelo.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	Page<Lancamento> findByDescricao(String nomeLancamento, Pageable paginacao);

}
