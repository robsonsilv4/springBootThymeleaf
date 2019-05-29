package com.robson.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robson.domain.Estudante;
import com.robson.repository.EstudanteRepository;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	private final EstudanteRepository repository;

	@Autowired
	public EstudanteController(EstudanteRepository repository) {
		this.repository = repository;
	}

	@GetMapping("cadastro")
	public String cadastro(Estudante estudante) {
		return "cadastro";
	}

	@GetMapping("listar")
	public String listar(Model model) {
		model.addAttribute("estudantes", repository.findAll());
		return "inicio";
	}

	@PostMapping("cadastro")
	public String cadastro(@Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cadastro";
		}

		repository.save(estudante);
		return "redirect:listar";
	}

	@GetMapping("atualizar/{matricula}")
	public String atualizar(@PathVariable("matricula") long matricula, Model model) {
		model.addAttribute("estudante", encontrarEstudante(matricula));
		return "atualizar";
	}

	@PostMapping("atualizar/{id}")
	public String atualizar(@PathVariable("matricula") long matricula, @Valid Estudante estudante, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			estudante.setMatricula(matricula);
			return "atualizar";
		}

		repository.save(estudante);
		model.addAttribute("estudantes", repository.findAll());
		return "inicio";
	}

	@GetMapping("deletar/{matricula}")
	public String deletar(@PathVariable("matricula") long matricula, Model model) {
		repository.delete(encontrarEstudante(matricula));
		model.addAttribute("estudantes", repository.findAll());
		return "inicio";
	}

	public Estudante encontrarEstudante(long matricula) {
		Estudante estudante = repository.findById(matricula)
				.orElseThrow(() -> new IllegalArgumentException("Matrícula do estudante inválida: " + matricula + "!"));
		return estudante;
	}
}
