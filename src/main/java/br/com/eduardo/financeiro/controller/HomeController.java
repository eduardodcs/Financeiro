package br.com.eduardo.financeiro.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eduardo.financeiro.modelo.Lancamento;
import br.com.eduardo.financeiro.repository.LancamentoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@GetMapping
	public String home(Model model) {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		BigDecimal total = new BigDecimal(0);
		for (Lancamento conta : lancamentos) {
			total = total.add(conta.getValor()) ;
		}
		model.addAttribute("lancamentos", lancamentos);
		model.addAttribute("total", total);
		return "home";
		
	}
	
}
