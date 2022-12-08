package com.backend.rutac.Dao;

import com.backend.rutac.Models.Ruta;
import com.backend.rutac.Models.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RutaDao extends CrudRepository<Ruta,Integer>{
   //Operación para seleccionar cuentas de un cliente en particular (SELECT)
   @Transactional(readOnly=true)//No afecta integridad base de datos
   @Query(value="SELECT * FROM ruta WHERE documento_usu= :dcusu", nativeQuery=true)
   public List<Ruta> consulta_viaje(@Param("dcusu") String dcusu); 
   //Agregar Ruta
   @Transactional(readOnly=false)
   @Modifying
   @Query(value="UPDATE ruta SET codigo_rut=codigo_rut + :ruta_añadida WHERE codigo_rut like :cdrt", nativeQuery=true)
   public void agregar_viaje(@Param("cdrt") String cdrt,@Param("ruta_añadida") Integer ruta_añadida); 
   //Eliminar ruta
   @Transactional(readOnly=false)
   @Modifying
   @Query(value="UPDATE ruta SET codigo_rut=codigo_rut - :ruta_eliminada WHERE codigo_rut like :cdrt", nativeQuery=true)
   public void eliminar_viaje(@Param("cdrt") String cdrt,@Param("ruta_eliminada") Integer ruta_eliminada);
  public Usuario login(String usuario, String sha1); 
}
