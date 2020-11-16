package TP.estadistica.mapa;

import TP.archivos.Archivo;
import de.fhpotsdam.unfolding.geo.Location;

import java.util.ArrayList;

public class InfoMapa {
    ArrayList<String> zonasStr;


    public InfoMapa() {
        this.zonasStr = Archivo.listaDeZonas();
    }

    public ArrayList<Location> searchLocation(){
        ArrayList<Location> toReturn = new ArrayList<>();
        for (String zona: zonasStr) {
            toReturn.add(BuscadorCoordenadas.getCoordenadas(zona));
        }
        return toReturn;
    }

}
