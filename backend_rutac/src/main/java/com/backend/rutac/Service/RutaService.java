package com.backend.rutac.Service;

import com.backend.rutac.Models.Ruta;
import com.backend.rutac.Dao.RutaDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;


@Service
public class RutaService {
    @Autowired
    private RutaDao dao;
    
    @Transactional(readOnly=false)
    public Ruta save(Ruta cuenta) {
        return dao.save(cuenta);
    }
    @Transactional(readOnly=false)
    public void delete(Integer id) {
        dao.deleteById(id);;
    }
    @Transactional(readOnly=true)
    public Ruta findById(Integer id) {
       return dao.findById(id).orElse(null);
    }
    @Transactional(readOnly=true)
    public List<Ruta> findByAll() {
        return (List<Ruta>) dao.findAll();
    }
    @Transactional(readOnly=true)
    public List<Ruta> consulta_viaje(String dcusu) {
        return (List<Ruta>) dao.consulta_viaje(dcusu);
    }

    @Transactional(readOnly=false)
    public void agregar_viaje(String cdrt,Integer ruta_añadida) {
        dao.agregar_viaje(cdrt, ruta_añadida);
    }

    @Transactional(readOnly=false)
    public void eliminar_viaje(String cdrt,Integer ruta_eliminada) {
        dao.eliminar_viaje(cdrt, ruta_eliminada);
    }
}
