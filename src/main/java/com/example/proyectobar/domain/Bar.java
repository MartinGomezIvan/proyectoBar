package com.example.proyectobar.domain;

import javafx.beans.property.SimpleStringProperty;

public class Bar {
    public SimpleStringProperty bebida=new SimpleStringProperty();
    public SimpleStringProperty sabor=new SimpleStringProperty();
    public SimpleStringProperty mezcla=new SimpleStringProperty();
    public SimpleStringProperty marca=new SimpleStringProperty();
    public SimpleStringProperty complemento=new SimpleStringProperty();

    public Bar(){

    }

    public Bar(SimpleStringProperty bebida, SimpleStringProperty sabor, SimpleStringProperty mezcla, SimpleStringProperty marca, SimpleStringProperty complemento) {
        this.bebida = bebida;
        this.sabor = sabor;
        this.mezcla = mezcla;
        this.marca = marca;
        this.complemento = complemento;
    }

    public String getBebida() {
        return bebida.get();
    }

    public SimpleStringProperty bebidaProperty() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida.set(bebida);
    }

    public String getSabor() {
        return sabor.get();
    }

    public SimpleStringProperty saborProperty() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor.set(sabor);
    }

    public String getMezcla() {
        return mezcla.get();
    }

    public SimpleStringProperty mezclaProperty() {
        return mezcla;
    }

    public void setMezcla(String mezcla) {
        this.mezcla.set(mezcla);
    }

    public String getMarca() {
        return marca.get();
    }

    public SimpleStringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getComplemento() {
        return complemento.get();
    }

    public SimpleStringProperty complementoProperty() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento.set(complemento);
    }
}
