package TP.entidades;

import TP.archivos.Archivo;
import TP.usuarios.Ciudadano;

public class Encuentro {
    private final String ciud1Cuil;
    private final String ciud2Cuil;
    Ciudadano ciud1;
    Ciudadano ciud2;

    //Fecha fechaInicio;
    Fecha fechaFin;
    Fecha finMas24hs;
    Fecha finMas48hs;

                            //Fecha fechaInicio,
    public Encuentro(String cuil1, String cuil2, Fecha fechaFin) {
        //this.ciud1 = Archivo.searchCUIL(cuil1);
        this.ciud1Cuil = cuil1;
        this.ciud2Cuil = cuil2;
        //this.ciud1 = Archivo.searchCUIL(ciud1Cuil);
        //this.ciud2 = Archivo.searchCUIL(ciud2Cuil);


        //this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.finMas24hs = fechaFin.add24hs();
        finMas48hs = fechaFin.add48hs();
    }
    public void initializeCiudadanos(){
        this.ciud1 = Archivo.searchCUIL(ciud1Cuil);
        this.ciud2 = Archivo.searchCUIL(ciud2Cuil);

    }

    public String toString(){
        //(2002049954(CUIL CIUD 1),1924894392(CUIL CIUD 2),100920(Fecha de Inicio en numero),250920(fecha de fin en numero)
        return ciud1Cuil+","+ciud2Cuil+","+fechaFin.toString();

        //fechaInicio.toString()+","+
    }


    public String getOtherCUIL(Ciudadano ciudadano) {
        if(ciudadano.getCUIL().equals(ciud1Cuil)){
            return ciud2Cuil;
        } else{
            return ciud1Cuil;
        }
    }

    public Fecha getFinMas24hs() {
        return finMas24hs;
    }

    public Fecha getFinMas48hs() {
        return finMas48hs;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }
}
