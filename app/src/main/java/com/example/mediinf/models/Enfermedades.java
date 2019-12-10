package com.example.mediinf.models;

public class Enfermedades {

    Long id;
    String nombre;
    String info;
    String descripcion;
    String sintomas;
    String apto;
    String medicamento_g;
    String medicamento_l;
    String medicamento_n;
    String enfermedad_img;

    public Enfermedades(){



    }

    public Enfermedades(Long id, String nombre, String info, String descripcion, String sintomas, String apto, String medicamento_g, String medicamento_l, String medicamento_n, String enfermedad_img) {
        this.id = id;
        this.nombre = nombre;
        this.info = info;
        this.descripcion = descripcion;
        this.sintomas = sintomas;
        this.apto = apto;
        this.medicamento_g = medicamento_g;
        this.medicamento_l = medicamento_l;
        this.medicamento_n = medicamento_n;
        this.enfermedad_img = enfermedad_img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getApto() {
        return apto;
    }

    public void setApto(String tipo) {
        this.apto = apto;
    }

    public String getMedicamento_g() {
        return medicamento_g;
    }

    public void setMedicamento_g(String medicamento_g) {
        this.medicamento_g = medicamento_g;
    }

    public String getMedicamento_l() {
        return medicamento_l;
    }

    public void setMedicamento_l(String medicamento_l) {
        this.medicamento_l = medicamento_l;
    }

    public String getMedicamento_n() {
        return medicamento_n;
    }

    public void setMedicamento_n(String medicamento_n) {
        this.medicamento_n = medicamento_n;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnfermedad_img() {
        return enfermedad_img;
    }

    public void setEnfermedad_img(String enfermedad_img) {
        this.enfermedad_img = enfermedad_img;
    }

    @Override
    public String toString() {
        return "Enfermedades{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", info='" + info + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", sintomas='" + sintomas + '\'' +
                ", apto='" + apto + '\'' +
                ", medicamento_g='" + medicamento_g + '\'' +
                ", medicamento_l='" + medicamento_l + '\'' +
                ", medicamento_n='" + medicamento_n + '\'' +
                ", enfermedad_img='" + enfermedad_img + '\'' +
                '}';
    }

}