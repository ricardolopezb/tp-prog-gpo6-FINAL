package TP.estadistica;

import TP.archivos.Archivo;
import TP.util.Scanner;
import de.fhpotsdam.unfolding.examples.marker.imagemarker.ImageMarker;
import de.fhpotsdam.unfolding.examples.marker.imagemarker.ImageMarkerApp;

import java.util.ArrayList;

public class StatisticsManager {

    ArrayList<Zona> zonas;
    ArrayList<Brote> brotes;
    BroteChecker broteChecker;

    public StatisticsManager() {
        ArrayList<String> zonasString = Archivo.listaDeZonas();
        this.zonas = new ArrayList<>();
        for (String s : zonasString) {
            zonas.add(new Zona(s));
        }
        broteChecker = new BroteChecker();
        this.brotes = new ArrayList<>();
    }
    public void mostrarMapa(){
        ImageMarkerApp map = new ImageMarkerApp();
        ImageMarkerApp.main(map.args);
    }

    public void menuZonas() {
        displayZonas();
        Zona zonaSeleccionada = seleccionarZona();
        zonaSeleccionada.printRanking();
    }

    public void showBrotes() {
        checkBrotes();

        if (brotes.size() == 0) {
            System.out.println("No se han encontrado brotes");

        } else {
            System.out.println("Se han encontrado un nuevo brote.\n");
            System.out.println(brotes.get(0).toString());
            //char seleccion = Character.toLowerCase(Scanner.getChar("--> "));
            //if(seleccion == 'n') return;
            //if(seleccion == 'y'){
            //    for (Brote brote: brotes) {
            //        System.out.println(brote.toString());
            //    }
        }

    }


    public void checkBrotes(){
        Brote broteEncontrado = broteChecker.checkBrotes();
        if(broteEncontrado == null) return;
        brotes.add(broteEncontrado);

    }

    private void displayZonas(){
        int i = 1;
        for (Zona zona : zonas) {
            System.out.println(i + ". " + zona.getNombre());
            i++;
        }
    }

    private Zona seleccionarZona() {
        Integer seleccion = Scanner.getInt("Escoja la Zona para ver \n--> ");
        return zonas.get(seleccion-1);
    }


}
