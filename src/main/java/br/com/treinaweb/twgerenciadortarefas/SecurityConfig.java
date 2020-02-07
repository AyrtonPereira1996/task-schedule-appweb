package br.com.treinaweb.twgerenciadortarefas;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration//define a classe de configuracao
public class SecurityConfig extends WebSecurityConfigurerAdapter {//classe extendida indica que esssa classe d ssecurity, e uma classe de configuracao do spring security
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;//ja esta habilitado porque o spring ja sabe fabricar beans deste tipo
	
	@Autowired
	private DataSource datasource;//consiste na conexao a base de dados, e a partir do datasource que o spring security ira acessar a tabela de usuarios
	
	//precisamos de duas strings, o string precisa dessas duas strings
	//uma para fazer select na tabela de usuarios e outras na tabela de perfis
	
	@Value("${spring.queries.users-query}")  //documento de configuracao do spring
	private String userQuery;
	
	@Value("${spring.queries.roles-query}")//documento de configuracao do spring
	private String roleQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {// configuracao de como ira chegar na tabela de usuario, qual consulta para carregar usuario e perfil
		auth
		.jdbcAuthentication()//
		.usersByUsernameQuery(userQuery)//consulta para carregar pelo user name
		.authoritiesByUsernameQuery(roleQuery)//consulta para carregar perfis
		.dataSource(datasource)//data source sera utilizado para se conectar ao banco
		.passwordEncoder(passwordEncoder);//para fazer a conferencia da senha
	}
	
	@Override//conficuracao da aplicacao
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()//verifica se qualquer requisicao esta devidamente autenticada
		.antMatchers("/login").permitAll()//url de login onde todos podem aceder
		.antMatchers("/registration").permitAll()//url padrao para cria;#ao de usuarios todos podem aceder
		.anyRequest().authenticated()//as rrestantes devem estar autenticados
		.and().csrf().disable()//desabilitar uma estrutura chamda csrf, e um procedimento de seguranca
		.formLogin()//onde o usuario vai fazer o login ira se definir, ira definir como o processo de login ira acontecer
		.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")//pagina de login onde todos podem acessar e caso deia erro onde sera redirecionada e caso deia certo onde sera redirecionado
		.usernameParameter("email").passwordParameter("senha") //os parametros que iram conter a senha e o usernme para o srping security
		.and().logout()//processo de logout
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");//url de logout e caso logout seja feito com sucesso
	
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{//subscrita para 
		web.ignoring().antMatchers("/webjars/**");
	}
	
}
