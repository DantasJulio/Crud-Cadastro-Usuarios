package br.com.springboot.curso_jdev_treinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // a notação fica acima da classe.
public class Application {
	
    public static void main(String[] args) {
    
    	SpringApplication.run(Application.class, args); // essa é a linha que roda o projeto SpringBoot.
    
    }
}
