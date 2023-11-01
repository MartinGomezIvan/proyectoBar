package com.example.proyectobar.domain;

import javafx.beans.property.SimpleStringProperty;

public class Bar {
    public String bebida;
    public String sabor;
    public String mezcla;
    public String marca;
    public String complemento;
    public Bar(){

    }
    public Bar(String bebida, String sabor, String mezcla, String marca, String complemento) {
        this.bebida = bebida;
        this.sabor = sabor;
        this.mezcla = mezcla;
        this.marca = marca;
        this.complemento = complemento;

    }

    public String getBebida() {
        return bebida;
    }

    public String getSabor() {
        return sabor;
    }

    public String getMezcla() {
        return mezcla;
    }

    public String getMarca() {
        return marca;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public void setMezcla(String mezcla) {
        this.mezcla = mezcla;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }



    @Override
    public String toString() {
        return "Beb: " + bebida + ", Sab: " + sabor + ", Mez: " + mezcla + ", Mar: " + marca + ", Comp: " + complemento;
    }
}