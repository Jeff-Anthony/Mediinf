package com.example.mediinf.models;

import com.orm.dsl.Table;

@Table
public class User {

    private Long id;
    private String Nombre;
    private String Apellido;
    private String DNI;
    private String Correo;
    private String Alergia;
    private String Contraseña;


    public User() {
    }

    public User(String nombre, String apellido, String dni, String correo, String alergia, String contraseña) {
        Nombre = nombre;
        Apellido = apellido;
        DNI = dni;
        Correo = correo;
        Alergia = alergia;
        Contraseña = contraseña;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getAlergia() {
        return Alergia;
    }

    public void setAlergia(String alergia) {
        Alergia = alergia;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", DNI='" + DNI + '\'' +
                ", Correo='" + Correo + '\'' +
                ", Alergia='" + Alergia + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                '}';
    }
}
