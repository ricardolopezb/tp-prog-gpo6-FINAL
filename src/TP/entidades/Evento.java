package TP.entidades;

public class Evento {
    String nombre;
    Fecha fechaDeAlta;
    //relacion entre eventos???

    public Evento(String nombre, Fecha fechaDeAlta){
        this.fechaDeAlta = fechaDeAlta;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public Fecha getFecha() {
        return fechaDeAlta;
    }
}
