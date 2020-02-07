package br.com.treinaweb.twgerenciadortarefas.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twgerenciadortarefas.model.Tarefa;
import br.com.treinaweb.twgerenciadortarefas.model.Usuario;
import br.com.treinaweb.twgerenciadortarefas.repositorios.RepositorioTarefa;
import br.com.treinaweb.twgerenciadortarefas.servicos.ServicoUsuario;

@Controller // anotação que permite a configuração de uma classe em um controller
@RequestMapping("/tarefas") // para puder aceder qualque metodo/actions deste controller apenas poderei em
							// /tarefas
public class TarefasController {

	@Autowired
	private RepositorioTarefa rt;

	@Autowired
	private ServicoUsuario su;

	@GetMapping("/listar") // usarei o verbo get do http porque precisarei pegar algo da
							// aplicacao(servidor) e devolver ao usuario, se eu quiser pegar a minha lista
							// de tarefas terei que estar em tarefas/listar
	public ModelAndView listar(HttpServletRequest request) {// actions de listar
		// toda a requisicao para uma aplicao web java e feita pela interface
		// httpservelerequest
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/listar");// nome da view ou documento html
		String emailUsuario = request.getUserPrincipal().getName();// na configuracao do spring security ja foi
																	// configurado que o nome do usario e o seu email
		mv.addObject("tarefas", rt.carregarTarefasPorUsuario(emailUsuario));// adicionamos um oobjecto tarefas, com o
																			// valor repositorio pegando tods
		// os dados com um select *
		return mv;// retorna o objecto
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de expiração obrigatória.");
		} else {
			if (tarefa.getDataExpiracao().before(new Date())) {
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
						"A data de expiração não pode ser anterior à data actual.");
			}
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/inserir");
			mv.addObject(tarefa);
		} else {
			String emailUsuario = request.getUserPrincipal().getName();// ira pegar as informações do usuario mais
																		// especificamente o email como esta configurado
																		// no spring security
			Usuario usuarioLogado = su.encontrarPorEmail(emailUsuario);//pegará o usuario que esta actualmente conectado
			tarefa.setUsuario(usuarioLogado);//antes de salval ira setar
			rt.save(tarefa);//ao salvar  ja tem usuario setado
			mv.setViewName("redirect:/tarefas/listar");

		}
		return mv;
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Tarefa tarefa = rt.getOne(id);
		mv.addObject("tarefa", tarefa);
		mv.setViewName("tarefas/alterar");
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida", "A data de expiração obrigatória.");
		} else {
			if (tarefa.getDataExpiracao().before(new Date())) {
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
						"A data de expiração não pode ser anterior à data actual.");
			}
		}
		if (result.hasErrors()) {
			mv.setViewName("tarefas/alterar");
			mv.addObject(tarefa);
		} else {
			mv.setViewName("redirect:/tarefas/listar");
			rt.save(tarefa);
		}
		return mv;
	}

	@GetMapping("/excluir/{id}") // o id seja passado para o id do parametro
	public String excluir(@PathVariable("id") Long id) {// ira receber o id da tarefa a ser excluida, pathvariable
														// porque o id vem da url
		rt.deleteById(id);
		return "redirect:/tarefas/listar";

	}

	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id") Long id) {
		Tarefa tarefa = rt.getOne(id); // ira pegar apenas um valor de um respectivo campo
		tarefa.setConcluida(true);
		rt.save(tarefa);
		return "redirect:/tarefas/listar";
	}

}
