package com.example.container2;

public class ContactosModel {
    private String tipo;
    private String intensidad;
    private int dificultad;

    public ContactosModel(String tipo, String intensidad, int dificultad) {
        this.tipo = tipo;
        this.intensidad = intensidad;
        this.dificultad = dificultad;
    }

    public String getTipo() { return tipo; }
    public String getIntensidad() { return intensidad; }
    public int getDificultad() { return dificultad; }
}
