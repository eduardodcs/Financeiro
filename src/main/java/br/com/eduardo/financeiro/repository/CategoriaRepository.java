package br.com.eduardo.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.financeiro.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
