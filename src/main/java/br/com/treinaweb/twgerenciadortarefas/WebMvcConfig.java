package br.com.treinaweb.twgerenciadortarefas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//anotcao que diz que e uma classe de configuracao
public class WebMvcConfig implements WebMvcConfigurer{//implementa uma interface que fornece uma serie de metodos e convencoes que indica que e uma classe de configuracao
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {//esse metodo esta a ensinar o spring a criar o bean do tipo BcryptPasswordEncoder, vai fabricar passwordencoder
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
		
	}
}

