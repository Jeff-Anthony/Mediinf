package com.example.mediinf.models;

import com.orm.dsl.Table;

@Table
public class Medicamentos_gen {


    private Long id;
    private String detalle;
    private String nombre;
    private String imagen;
    private String informacion;
    private String precio;
    private String tipo;
    private String sintomas;

    public Medicamentos_gen(){



    }

    public Medicamentos_gen(String detalle, String nombre, String imagen, String informacion, String precio, String tipo, String sintomas) {
        this.detalle = detalle;
        this.nombre = nombre;
        this.imagen = imagen;
        this.informacion = informacion;
        this.precio = precio;
        this.tipo = tipo;
        this.sintomas = sintomas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    @Override
    public String toString() {
        return "Medicamentos_gen{" +
                "id=" + id +
                ", detalle='" + detalle + '\'' +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", informacion='" + informacion + '\'' +
                ", precio='" + precio + '\'' +
                ", tipo='" + tipo + '\'' +
                ", sintomas='" + sintomas + '\'' +
                '}';
    }
}
