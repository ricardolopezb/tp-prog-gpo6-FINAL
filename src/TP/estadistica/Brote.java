package TP.estadistica;

public class Brote {
    private final Zona zona;
    private Integer personasAfectadas;

    public Brote(Zona zona) {
        this.zona = zona;
        personasAfectadas = zona.residentesPositivosCounter();

    }

    public Zona getZona() {
        return zona;
    }

    public Integer getPersonasAfectadas() {
        return personasAfectadas;
    }

    public String toString(){
        return "Brote en " + zona.getNombre() + " - Afectados: " + personasAfectadas;
    }
}
