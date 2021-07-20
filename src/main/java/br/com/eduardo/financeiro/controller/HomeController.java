package br.com.eduardo.financeiro.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eduardo.financeiro.dto.RequisicaoNovoLancamento;
import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.modelo.Situacao;
import br.com.eduardo.financeiro.repository.CategoriaRepository;
import br.com.eduardo.financeiro.repository.LancamentoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public String home(Model model) {
		Categoria cat = categoriaRepository.findByDescricao("Moradia");
		System.out.println(cat.getId());
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		List<Categoria> categorias = categoriaRepository.findAll();
		BigDecimal total = new BigDecimal(0);
		for (Lancamento conta : lancamentos) {
			total = total.add(conta.getValor());
		}
		
		model.addAttribute("lancamentos", lancamentos);
		model.addAttribute("categorias", categorias);
		model.addAttribute("total", total);
		return "home";
	}
	
	@GetMapping("formulario")
	public String novo(Model model, RequisicaoNovoLancamento requisicao) {
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		return "lancamento/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoLancamento requisicao, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect:/home/formulario";
		}
		Lancamento lancamento = requisicao.toLancamento(categoriaRepository);
		lancamentoRepository.save(lancamento);
		return "redirect:/home";
	}
	
}
