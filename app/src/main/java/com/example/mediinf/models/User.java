package com.example.mediinf.models;

public class User {

    private String Nombre;
    private String Apellido;
    private Integer DNI;
    private String Correo;
    private String Alergia;
    private String Contraseña;


    public User() {
    }

    public User(String nombre, String apellido, Integer DNI, String correo, String alergia, String contraseña) {
        Nombre = nombre;
        Apellido = apellido;
        this.DNI = DNI;
        Correo = correo;
        Alergia = alergia;
        Contraseña = contraseña;
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

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
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
                "Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", DNI=" + DNI +
                ", Correo='" + Correo + '\'' +
                ", Alergia='" + Alergia + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                '}';
    }
}
