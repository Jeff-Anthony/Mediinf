package com.example.mediinf.models;

import com.orm.dsl.Table;

@Table
public class Medicamentos_gen {


    private int codigo;
    private String trata_enfermedad;
    private String tipo;
    private String nombre;
    private String imagen;
    private String informacion;
    private String precio;

    public Medicamentos_gen(){



    }

    public Medicamentos_gen(int codigo, String trata_enfermedad, String tipo, String nombre, String imagen, String informacion, String precio) {
        this.codigo = codigo;
        this.trata_enfermedad = trata_enfermedad;
        this.tipo = tipo;
        this.nombre = nombre;
        this.imagen = imagen;
        this.informacion = informacion;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTrata_enfermedad() {
        return trata_enfermedad;
    }

    public void setTrata_enfermedad(String trata_enfermedad) {
        this.trata_enfermedad = trata_enfermedad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return "Medicamentos_gen{" +
                "codigo=" + codigo +
                ", trata_enfermedad='" + trata_enfermedad + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", informacion='" + informacion + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
