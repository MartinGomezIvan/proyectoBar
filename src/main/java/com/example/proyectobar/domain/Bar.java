package com.example.proyectobar.domain;

public class Bar {
    String bebida;
    String sabor;
    String mezcla;
    String marca;
    String complemento;

    public Bar(){

    }

    public Bar(String bebida, String sabor, String mezcla, String marca, String complemento){
        this.bebida=bebida;
        this.sabor=sabor;
        this.mezcla=mezcla;
        this.marca=marca;
        this.complemento=complemento;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getMezcla() {
        return mezcla;
    }

    public void setMezcla(String mezcla) {
        this.mezcla = mezcla;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
