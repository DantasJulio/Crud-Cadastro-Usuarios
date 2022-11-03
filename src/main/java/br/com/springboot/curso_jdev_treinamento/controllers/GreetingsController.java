package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController // essa notação, assim como a do @SpringBootApplication, fica acima da classe.
public class GreetingsController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario);
    	
    	return "Olá, mundo!" + nome;
    }
    
    @GetMapping(value="listatodos") /**ESSE É O MEU PRIMERO MÉTODO DE API**/
    @ResponseBody // retorna os dados para o CORPO DA RESPOSTA. RETORNA UM JSON.
    public ResponseEntity<List<Usuario>> listaUsuarios(){ // o ResponseEntity é o que o nome diz, uma entidade de busca e resposta.
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); // criei uma lista de Usuarios que recebe o método findAll da interface JpaRepository, que executa a busca no banco de dados.
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retornei um ResponseEntity do tipo Lista de Usuarios passando como body a lista crianda acima e o status Http.
    	
    };/// como construir uma API. Precisa ser publica e SEMPRE retornar um JSON.
    
    @PostMapping(value="salvar") // mapeia a URL
    @ResponseBody // Descrição da resposta.
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario){ // @RequestBody recebe os dados para salvar.
//    	if(usuario.getId() == null) {
//    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
//    	}
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    			
    }
    
    @DeleteMapping(value="deletar") // deleta a URL
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long userid){
    	if(userid == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
    	}
    	usuarioRepository.deleteById(userid);
    	return new ResponseEntity<String>("User deleted sucessfully.", HttpStatus.OK);
    }
    
    @GetMapping(value="buscaruserid") /**ESSE É O MEU PRIMERO MÉTODO DE API**/
    @ResponseBody // retorna os dados para o CORPO DA RESPOSTA. RETORNA UM JSON.
    public ResponseEntity<?> buscarUserId(@RequestParam(name = "userid") Long userid){ // o ResponseEntity é o que o nome diz, uma entidade de busca e resposta.
    	if(userid == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
    	}
    	Usuario usuario = usuarioRepository.findById(userid).get(); // criei uma lista de Usuarios que recebe o método findAll da interface JpaRepository, que executa a busca no banco de dados.
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
   
    }
    
    @GetMapping(value="buscarpornome") /**ESSE É O MEU PRIMERO MÉTODO DE API**/
    @ResponseBody // retorna os dados para o CORPO DA RESPOSTA. RETORNA UM JSON.
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ // o ResponseEntity é o que o nome diz, uma entidade de busca e resposta.
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); // criei uma lista de Usuarios que recebe o método findAll da interface JpaRepository, que executa a busca no banco de dados.
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value="atualizar") // mapeia a URL
    @ResponseBody // Descrição da resposta.
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ // @RequestBody recebe os dados para salvar.
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.ACCEPTED);
    			
    }
    
}
