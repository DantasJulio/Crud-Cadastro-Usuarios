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
@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;
	
    @RequestMapping(value = "/projeto/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return  name;
    }
    
    @RequestMapping(value = "/projeto/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario);
    	
    	return nome;
    }
	    
    @GetMapping(value="listatodos") /**ESSE É O MEU PRIMERO MÉTODO DE API**/
    @ResponseBody 
    public ResponseEntity<List<Usuario>> listaUsuarios(){ 
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); 
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); 
    	
    };
    
    @PostMapping(value="salvar")
    @ResponseBody
    public ResponseEntity<?> salvar(@RequestBody Usuario usuario){ // 
//    	if(usuario.getId() == null) {
//    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
//    	}
    	
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    			
    }
    
    @DeleteMapping(value="deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long userid){
    	if(userid == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE); // retorna erro caso o ID do usuário não seja informado.	
    	}
    	usuarioRepository.deleteById(userid);
    	return new ResponseEntity<String>("User deleted sucessfully.", HttpStatus.OK);
    }
    
    @GetMapping(value="buscaruserid") 
    @ResponseBody 
    public ResponseEntity<?> buscarUserId(@RequestParam(name = "userid") Long userid){ 
    	if(userid == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
    	}
    	Usuario usuario = usuarioRepository.findById(userid).get(); 
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
   
    }
    
    @GetMapping(value="buscarpornome") 
    @ResponseBody 
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){ 
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); 
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value="atualizar") 
    @ResponseBody 
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ // @RequestBody recebe os dados para salvar.
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("O ID não foi informado.", HttpStatus.NOT_ACCEPTABLE);	
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.ACCEPTED);
    			
    }
    
}
