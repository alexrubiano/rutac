package com.backend.rutac.Controller;
import com.backend.rutac.Models.Usuario;
import com.backend.rutac.Security.Hash;
import com.backend.rutac.Service.UsuarioService;
import com.backend.rutac.Dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;
import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {
   
    @Autowired
    private UsuarioDao dao; 
    @Autowired
    private UsuarioService servicio;   
   

    @PostMapping(value="/")
    public ResponseEntity<Usuario> agregar(@RequestHeader("clave") String clave,
        @RequestHeader("usuario") String usuario, @Valid @RequestBody Usuario dato) {
      Usuario objeto = new Usuario();
      objeto = dao.login(usuario, Hash.sha1(clave));
      if (objeto != null) {
        dato.setClave_usu(Hash.sha1(dato.getClave_usu()));
        return new ResponseEntity<>(servicio.save(dato), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

    }
     
  @DeleteMapping(value="/{id}")
  public ResponseEntity<Usuario> eliminar(@PathVariable String id,@RequestHeader("clave") 
  String clave, @RequestHeader("usuario") String usuario) {
    Usuario objeto = new Usuario();
    objeto = dao.login(usuario, Hash.sha1(clave));
    if (objeto != null) {
      Usuario obj = servicio.findById(id);
      if (obj != null)
        servicio.delete(id);
      else
        return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
      return new ResponseEntity<>(obj, HttpStatus.OK);

    } else {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
       
  
                
    @GetMapping("/list") 
    @ResponseBody
    public ResponseEntity<List<Usuario>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            return new ResponseEntity<>(servicio.findAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }  
          
    }
    
    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<Usuario> consultaPorId(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            return new ResponseEntity<>(servicio.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }

   //Método Put para Modificar datos en la tabla de la BD
    @PutMapping(value="/") 
    @ResponseBody
    public ResponseEntity<Usuario> editar(@RequestHeader("clave") String clave,
        @RequestHeader("usuario") String usuario, @Valid @RequestBody Usuario dato) {
      Usuario objeto = new Usuario();
      objeto = dao.login(usuario, Hash.sha1(clave));
      if (objeto != null) {
        dato.setClave_usu(Hash.sha1(dato.getClave_usu()));
        Usuario obj = servicio.findById(dato.getDocumento_usu());
        if (obj != null) {
          obj.setApellido_usu(dato.getApellido_usu());
          obj.setCelular_usu(dato.getCelular_usu());
          obj.setClave_usu(dato.getClave_usu());
          obj.setCodigo_mun(dato.getCodigo_mun());
          obj.setEmail_usu(dato.getEmail_usu());
          obj.setEstado_usu(dato.getEstado_usu());
          obj.setFoto_usu(dato.getFoto_usu());
          obj.setGenero_usu(dato.getGenero_usu());
          obj.setNombre_usu(dato.getNombre_usu());
          obj.setTipo_identificacion_usu(dato.getTipo_identificacion_usu());

          servicio.save(dato);
        } else
          return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(obj, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

    }

    
    @GetMapping("/login")
    @ResponseBody
    public Usuario ingresar(@RequestParam ("usuario") String usuario,@RequestParam ("clave") String clave) {
        clave=Hash.sha1(clave);
        return servicio.login(usuario, clave); 
    }
}