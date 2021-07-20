package br.com.eduardo.financeiro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eduardo.financeiro.dto.RequisicaoNovaCategoria;
import br.com.eduardo.financeiro.modelo.Categoria;
import br.com.eduardo.financeiro.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public String categoria(Model model) {
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		return "categoria/categoria";
	}
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovaCategoria requisicao) {
		return "categoria/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovaCategoria requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "categoria/formulario";
		}
		Categoria categoria = requisicao.toCategoria();
		categoriaRepository.save(categoria);
		return "redirect:/categoria";
	}
}
