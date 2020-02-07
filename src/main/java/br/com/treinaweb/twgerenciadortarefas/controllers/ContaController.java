package br.com.treinaweb.twgerenciadortarefas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.twgerenciadortarefas.model.Usuario;
import br.com.treinaweb.twgerenciadortarefas.servicos.ServicoUsuario;

@Controller
public class ContaController {//e neste controller que o usuario ira fazer login e logout
	
	@Autowired
	private ServicoUsuario servicoUsuario;
	
	
	@GetMapping("/login")//acessamos via get por estarmos a ler algo do servidor como esta configurado sera /login
	public String login() {
		return "conta/login"; //pagina de login
	}
	
	@GetMapping("/registration")
	public ModelAndView registrar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("conta/registrar");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registrar(@Valid Usuario usuario, BindingResult result) {// binding para puder fazer a validacao
		ModelAndView mv = new ModelAndView();
		Usuario usr = servicoUsuario.encontrarPorEmail(usuario.getEmail());// ira verificar se existe um email semelhante se existir ira guardar em usr
		 if (usr!=null) {// se existir sera diferente de null
			result.rejectValue("email", "", "Usuario ja cadastrado");
		}
		 
		 if(result.hasErrors()) {//caso o resultado tenha erros
			 mv.setViewName("conta/registrar");
			 mv.addObject("usuario", usuario);//ira reencaminhar para registro
			 
	}else {
		servicoUsuario.salvar(usuario);
		mv.setViewName("redirect:/login");//caso n tenha erro ira salvar o usuario e ira redirenconar para o login
		}
		 return mv;
	}
	
	
}
