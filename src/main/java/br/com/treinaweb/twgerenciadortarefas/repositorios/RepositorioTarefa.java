package br.com.treinaweb.twgerenciadortarefas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinaweb.twgerenciadortarefas.model.Tarefa;

public interface RepositorioTarefa extends JpaRepository<Tarefa, Long> {

	@Query("Select t from Tarefa t where t.usuario.email = :emailUsuario ") // jpql
	List<Tarefa> carregarTarefasPorUsuario(@Param("emailUsuario") String email);// usaremos a anotacao @param que ira
																				// pegar o valor ira para um paramentro
																				// que foi definido acima
}
  