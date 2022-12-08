package com.backend.rutac.Controller;

import com.backend.rutac.Dao.RutaDao;
//import com.backend.rutac.Dao.UsuarioDao;
import com.backend.rutac.Models.Usuario;
import com.backend.rutac.Models.Ruta;
import com.backend.rutac.Security.Hash;
//import com.backend.rutac.Dao.CuentaDao;
import com.backend.rutac.Service.RutaService;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/ruta")
public class RutaController {
   //@Autowired
    //private ClienteDao dao; 
    @Autowired
    private RutaDao dao;

    @Autowired
    private RutaService servicio;
    
    @PostMapping(value="/")
    public ResponseEntity<Ruta> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid@RequestBody Ruta dato){   
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            return new ResponseEntity<>(servicio.save(dato), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }
        
           
    }
    
    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Ruta> eliminar(@PathVariable Integer id){ 
        Ruta objeto = servicio.findById(id); 
        if(objeto!=null) 
        servicio.delete(id); 
        else 
            return new ResponseEntity<>(objeto, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(objeto, HttpStatus.OK); 
    }

    @PutMapping(value="/") 
    public ResponseEntity<Ruta> editar(@RequestBody Ruta dato){ 
        Ruta objeto = servicio.findById(dato.getCodigo_rut()); 
        if(objeto!=null) {
            
            objeto.setCodigo_veh(dato.getCodigo_veh());
            objeto.setCupos_rut(dato.getCupos_rut());
            objeto.setDocumento_usu(dato.getDocumento_usu());
            objeto.setEstado_rut(dato.getEstado_rut());
            objeto.setFecha_rut(dato.getFecha_rut());
            objeto.setIndicacion_llegada(dato.getIndicacion_llegada());
            objeto.setIndicacion_origen(dato.getIndicacion_origen());
            objeto.setNombre_conductor(dato.getNombre_conductor());
            objeto.setPunto_destino(dato.getPunto_destino());
            objeto.setPunto_partida(dato.getPunto_partida());
            objeto.setValor_cupo(dato.getValor_cupo());
            servicio.save(objeto);
        } 
        else 
            return new ResponseEntity<>(objeto, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(objeto, HttpStatus.OK); 
    }

    @PutMapping(value="/agregar_viaje") 
    public void agregar_viaje(@RequestParam ("cdrt") String cdrt,@RequestParam ("ruta_añadida") Integer ruta_añadida,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            servicio.agregar_viaje(cdrt, ruta_añadida); 
        }
          
    }

    @PutMapping(value="/eliminar_viaje") 
    public void eliminar_viaje(@RequestParam ("cdrt") String cdrt,@RequestParam ("ruta_eliminada") Integer ruta_eliminada,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            servicio.eliminar_viaje(cdrt, ruta_eliminada); 
        }
        
    }

    

    @GetMapping("/list")
    public List<Ruta> consultarTodo(){
        return servicio.findByAll(); 
    }

    @GetMapping("/list/{id}") 
    public Ruta consultaPorId(@PathVariable Integer id){ 
        return servicio.findById(id); 
    }

    @GetMapping("/consulta_viaje")
    @ResponseBody
    public ResponseEntity<List<Ruta>> consulta_viaje(@RequestParam ("dcusu") String dcusu,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Usuario objeto=new Usuario();
        objeto=dao.login(usuario, Hash.sha1(clave));
        if (objeto!=null) {
            return new ResponseEntity<>(servicio.consulta_viaje(dcusu),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    }
}
