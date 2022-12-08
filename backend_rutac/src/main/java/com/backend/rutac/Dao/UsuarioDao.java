package com.backend.rutac.Dao;

import com.backend.rutac.Models.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioDao extends CrudRepository<Usuario,String> {
    //Operación de Autentiiicación (SELECT)
    @Transactional(readOnly=true)//No afecta integridad base de datos
    @Query(value="SELECT * FROM cliente WHERE documento_usu= :usuario AND clave_usu= :clave", nativeQuery=true)
    public Usuario login(@Param("usuario") String usuario, @Param("clave") String clave); 

}
