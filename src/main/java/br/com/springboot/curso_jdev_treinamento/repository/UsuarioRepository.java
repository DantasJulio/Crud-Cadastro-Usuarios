package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;

// criar uma interface de repositório referente a cada uma das classes criadas.
@Repository // sempre usar a notação @Repository para definir como repositório.
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ // preciso criar uma interface, estender o JpaRepository<> e passar como parametro a classe referente a essa interace com o tipo de dados referente à primary key da classe.
	
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") // essa notação @Query é para acessar o banco de dados.
	List<Usuario> buscarPorNome(String name);							// u é tipo uma variável para Usuário "where" u.nome (oo) "like" para pesquisar partes e a %?1% se for só um parametro.
	
}
