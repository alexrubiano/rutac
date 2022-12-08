package com.backend.rutac.Models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//Para validar los errores
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;




@Getter
@Setter
@Entity
@Table(name="ruta")
public class Ruta implements Serializable {
    //Creamos los atributos de la clase de acuerdo a los
    //Campos de la tabla en la BD
    @Id
    @Column(name="codigo_rut")
    private int codigo_rut;
    @Column(name="nombre_conductor")
    private String nombre_conductor;
    @ManyToOne
    @JoinColumn(name="codigo_veh")
    private Vehiculo codigo_veh;
    @Column(name="estado_rut")
    private int estado_rut;
    @Column(name="punto_partida")
    private String punto_partida;
    @Column(name="fecha_rut")
    private Date fecha_rut;
    @Column(name="punto_destino")
    private String punto_destino;
    @Column(name="cupos_rut")
    private int cupos_rut;
    @Column(name="indicacion_origen")
    private String indicacion_origen;
    @Column(name="indicacion_llegada")
    private String indicacion_llegada;
    @Column(name="valor_cupo")
    private int valor_cupo;
    @ManyToOne
    @JoinColumn(name="documento_usu")
    private Usuario documento_usu;
    @Override
    public String toString() {
      return "Ruta [codigo_rut=" + codigo_rut + ", nombre_conductor=" + nombre_conductor + ", codigo_veh=" + codigo_veh
          + ", estado_rut=" + estado_rut + ", punto_partida=" + punto_partida + ", fecha_rut=" + fecha_rut
          + ", punto_destino=" + punto_destino + ", cupos_rut=" + cupos_rut + ", indicacion_origen=" + indicacion_origen
          + ", indicacion_llegada=" + indicacion_llegada + ", valor_cupo=" + valor_cupo + ", documento_usu="
          + documento_usu + "]";
    }
    
  }
 



   

