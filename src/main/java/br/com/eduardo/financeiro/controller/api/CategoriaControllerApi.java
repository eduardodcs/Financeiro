package br.com.eduardo.financeiro.controller.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.eduardo.financeiro.controller.form.AtualizaCategoriaForm;
import br.com.eduardo.financeiro.controller.form.CategoriaForm;
import br.com.eduardo.financeiro.dto.CategoriaDto;
import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaControllerApi {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<CategoriaDto> categorias(@RequestParam(required = false) String nomeCategoria, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		if(nomeCategoria == null) {
			Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converter(categorias);
		} else {
			Page<Categoria> categorias = categoriaRepository.findByDescricao(nomeCategoria, paginacao);
			return CategoriaDto.converter(categorias);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> novaCategoria(@Valid @RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.converter();
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/api/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar (@PathVariable Long id){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizaCategoriaForm form){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			Categoria categoria = form.atualizar(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover (@PathVariable Long id){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} 
		return ResponseEntity.notFound().build();
	}
	

}
