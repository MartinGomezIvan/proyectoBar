package org.example;

public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    private boolean carnet;
    public Persona(){

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isCarnet() {
        return carnet;
    }

    public void setCarnet(boolean carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", carnet=" + carnet +
                '}';
    }
}