package TP.estadistica.mapa;

import TP.archivos.Archivo;
import de.fhpotsdam.unfolding.geo.Location;
import TP.util.ScriptPython;
import TP.util.MetodosAuxiliares;

import java.io.File;
import java.util.ArrayList;

public class BuscadorCoordenadas {

    public static Location getCoordenadas(String zona){
        Archivo.writeFile(zona, "Zona.txt");
        MetodosAuxiliares.delay(1500);
        ScriptPython scriptPython = new ScriptPython();
        scriptPython.runScript();
        MetodosAuxiliares.delay(2000);
        ArrayList<String> coordenadas = Archivo.collectFileLines("Coordenadas.txt");
        System.out.println(coordenadas.get(0));

        String strCoord = coordenadas.get(0);
        String[] coordsDivididas = strCoord.split(",");
        float latitud = Float.parseFloat(coordsDivididas[0]);
        float longitud = Float.parseFloat(coordsDivididas[1]);
        Archivo.removeLine(zona, "Zona.txt");
        Archivo.removeLine(strCoord, "Coordenadas.txt");

        return new Location(latitud, longitud);
    }

    public static ArrayList<Location> getLocations(ArrayList<String> zonas){
        ArrayList<Location> toReturn = new ArrayList<>();
        for (String zona : zonas) {
            getCoordenadas(zona);
            ArrayList<String> coords = Archivo.collectFileLines("Coordenadas.txt");
            for (String coord : coords) {
                String[] coordsSplit = coord.split(",");
                Location toAdd = new Location(Float.parseFloat(coordsSplit[0]), Float.parseFloat(coordsSplit[1]));
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }




}
