package TP.estadistica;

import TP.archivos.Archivo;
import TP.entidades.Evento;
import TP.usuarios.Ciudadano;

import java.util.*;
import java.util.Map.*;

public class Zona {
    private final String nombre;
    ArrayList<Ciudadano> residentes;
    HashMap<String, Integer> ranking;


    public Zona(String nombre){
        this.nombre = nombre;
        residentes = Archivo.searchCiudadanosPorZona(nombre);
        ranking = new HashMap<>();
        refreshRanking();

    }



    public String getNombre() {
        return nombre;
    }

    public void refreshRanking(){
        this.ranking = getRankingSintomas();
        sortByValue();
    }

    public void printRanking(){
        int i = 0;
        Iterator it = ranking.entrySet().iterator();
        while(it.hasNext() && i < 3) {
            Map.Entry obj = (Entry) it.next();
            System.out.println(obj.getKey() + " - Afectados: "+obj.getValue());
            i++;
        }

    }

    private HashMap<String, Integer> getRankingSintomas(){
        ArrayList<String> sintomasActuales = Archivo.collectFileLines("SintomasGenerados.txt");
        HashMap<String, Integer> ranking = new HashMap<>();
        for (String sintoma : sintomasActuales) {
            Integer veces = 0;
            for (Ciudadano ciudadano: residentes) {
                ArrayList<Evento> sintomasDelCiudadano = ciudadano.getSintomas();
                for (Evento sintomaCiudadano: sintomasDelCiudadano) {
                    if(sintoma.equals(sintomaCiudadano.getNombre())){
                        veces++;
                    }

                }
            }
            ranking.put(sintoma, veces);
        }
        return ranking;

    }

    public void sortByValue() {
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        ranking.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        this.ranking = reverseSortedMap;

    }

    public Integer residentesPositivosCounter(){
        Integer positivos = 0;
        for (Ciudadano ciud: residentes) {
            if(ciud.getCovid()){
                positivos++;
            }
        }
        return positivos;
    }

    public void printResidentes(){
        for (Ciudadano residente : residentes) {
            residente.printCiudadano();

        }
    }




}
