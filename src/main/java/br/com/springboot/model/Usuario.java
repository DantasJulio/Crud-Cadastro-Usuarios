package br.com.springboot.curso_jdev_treinamento.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity // sempre definir com a notação @Entity
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1) //@SequenceGenerator para criar uma sequencia de id's no banco de dados. Os parametros name, sequenceName, allocationSize, initialValue devem sempre ser passados.
public class Usuario implements Serializable { // a classe de usuario deve sempre implementar a interface Serializable.

	private static final long serialVersionUID = 1L; // após implementar a interface, importar o serialVersionUID.
	
		@Id // após definir uma variável como o id, usar a notação @Id para definir como primary key.
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario") // usar a notação GenerateValue para criar a sequencia de ids de cada usuario, passando como parametro um sequenceGenerator e o nome da sequenca definido no @SequenceGenerator
		private Long id;
		private String nome;
		private int idade;
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getIdade() {
			return idade;
		}
		public void setIdade(int idade) {
			this.idade = idade;
		}
		
}
