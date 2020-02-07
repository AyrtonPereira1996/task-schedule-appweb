package br.com.treinaweb.twgerenciadortarefas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usr_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private Long id;

	@Column(name = "usr_email", nullable = false, length = 100)
	@NotNull(message = "O email e obrigatorio")
	@Length(min = 5, max = 100, message = "O email deve conter entre 5 a 100 caracteres")
	private String email;

	@Column(name = "usr_senha", nullable = false, length = 100)
	@NotNull(message = "A senha e obrigatoria")
	private String senha;

	// sabemos que um usuario tem uma lista de tarefas porque um usuario pode ter
	// ter varias tarefas associadas a ele e podemos mapear com uma anotation
	// atraves
	// da anotacao @oneyoMany porque um usuario pode ter varias tarefas
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Tarefa> tarefas;

	// dentro dos parametros da anotacao utilizaremos
	// mappedby=usuario porque as entidades vao
	// relacionar-se atraves da propriedade usuario que esta
	// dentro de tarefas, iremos a propriedade fetch
	// determina como essa entidade ira ser carregada: Eager
	// toda a vez que fizer um select de um usuario a jpa
	// vai fazer automaticamente um join e carregar a lista
	// de todas as tarefas, se fizer um lazy: quando fizer
	// um select as tarefas n#ao serao carrregadas
	// automaticamente. quando temos poucos dados o eader,
	// quando temos muitos dados o mais indicado seria o
	// lazy
	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
