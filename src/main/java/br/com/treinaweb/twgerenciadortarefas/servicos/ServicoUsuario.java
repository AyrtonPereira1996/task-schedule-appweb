package br.com.treinaweb.twgerenciadortarefas.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinaweb.twgerenciadortarefas.model.Usuario;
import br.com.treinaweb.twgerenciadortarefas.repositorios.RepositorioUsuarios;

@Service//anotacao que indica que e uma classe de servico para o eclipse
public class ServicoUsuario {
	@Autowired
	private RepositorioUsuarios ru;//iremos precisar da interface para pegar o email do repositorio
	
	@Autowired//o spring nao sabe como injectar, temos que ensinar a injectar atraves de classes de configuracao
	private BCryptPasswordEncoder passwordEncoder;//classe que Permite fazer o hash da senha
	
	public Usuario encontrarPorEmail(String email) {//metodo publico que retorna usuario que recebe string do usuario
		
		return ru.findByEmail(email);
		
	}
	
	
	public void salvar(Usuario usuario) {//metodo que insere usuario
		 usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));//para gerar o hash do usuario, que ele informou
		 ru.save(usuario);
	}
	
	
}
