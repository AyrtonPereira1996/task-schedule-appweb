package br.com.treinaweb.twgerenciadortarefas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twgerenciadortarefas.model.Usuario;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {

		Usuario findByEmail(String email);
	
}
