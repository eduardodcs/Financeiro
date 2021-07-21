package br.com.eduardo.financeiro.controller.api;

import java.net.URI;
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

import br.com.eduardo.financeiro.controller.form.AtualizaLancamentoForm;
import br.com.eduardo.financeiro.controller.form.LancamentoForm;
import br.com.eduardo.financeiro.dto.LancamentoDto;
import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.repository.CategoriaRepository;
import br.com.eduardo.financeiro.repository.LancamentoRepository;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoControllerApi {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<LancamentoDto> categorias(@RequestParam(required = false) String nomeLancamento, @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		if(nomeLancamento == null) {
			Page<Lancamento> lancamentos = lancamentoRepository.findAll(paginacao);
			return LancamentoDto.converter(lancamentos);
		} else {
			Page<Lancamento> lancamentos = lancamentoRepository.findByDescricao(nomeLancamento, paginacao);
			return LancamentoDto.converter(lancamentos);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LancamentoDto> novoLancamento(@Valid @RequestBody LancamentoForm form, UriComponentsBuilder uriBuilder) {
		Lancamento lancamentos = form.converter(categoriaRepository);
		lancamentoRepository.save(lancamentos);
		
		URI uri = uriBuilder.path("/api/categoria/{id}").buildAndExpand(lancamentos.getId()).toUri();
		return ResponseEntity.created(uri).body(new LancamentoDto(lancamentos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LancamentoDto> detalhar (@PathVariable Long id){
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new LancamentoDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LancamentoDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizaLancamentoForm form){
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		if(optional.isPresent()) {
			Lancamento lancamento = form.atualizar(id, lancamentoRepository, categoriaRepository);
			return ResponseEntity.ok(new LancamentoDto(lancamento));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover (@PathVariable Long id){
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		if (optional.isPresent()) {
			lancamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} 
		return ResponseEntity.notFound().build();
	}
}
