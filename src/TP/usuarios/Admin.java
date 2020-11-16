package TP.usuarios;

import TP.archivos.Archivo;
import TP.estadistica.StatisticsManager;
import TP.interfaz.InterfazConsola;
import TP.util.Check;
import TP.util.MetodosAuxiliares;
import TP.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Admin {
    private StatisticsManager stats;

    public Admin(){
        this.stats = new StatisticsManager();
    }

    public void desbloquear(Ciudadano c) {
        c.setBloqueado(false);
        c.setSolicitudesRechazadas(0);
    }

    public void bloquear(Ciudadano c) {
        c.setBloqueado(true);
    }

    public void manejarBloqueados(){
        ArrayList<String> lineas = Archivo.collectFileLines("CiudadanosBloqueados.txt");
        int i = 1;
        if(lineas.size() == 0){
            System.out.println("No hay ciudadanos bloqueados...");
            return;
        }
        for (String linea : lineas) {
            Ciudadano ciudadano = Archivo.decode(linea);
            System.out.println(i+". Nombre: "+ciudadano.getNombre()+" - CUIL: "+ciudadano.getCUIL() + " - Zona: "+ ciudadano.getZona());
            i++;

        }
        System.out.println("Desbloquear Ciudadano? Y/N");
        char opcion = Character.toLowerCase(Scanner.getChar("-->"));
        if(opcion == 'n') return;

        else if(opcion == 'y'){
            this.eliminarBloqueado();
        }

    }

    public void verBrotes(){
        stats.showBrotes();
    }

    public void eliminarBloqueado(){
        String cuilAEliminar = Scanner.getString("Introduzca el CUIL del Ciudadano a desbloquear:\n--> ");
        Ciudadano ciudadanoADesbloquear = Archivo.searchCUIL(cuilAEliminar);
        Archivo.removeLine(cuilAEliminar, "CiudadanosBloqueados.txt");
        desbloquear(ciudadanoADesbloquear);
    }

    public void generarEvento(){
        Archivo.printFileLines("SintomasTotales.txt");
        ArrayList<String> sintomas = Archivo.collectFileLines("SintomasTotales.txt");
        Integer seleccion = Scanner.getInt("Seleccione el sintoma a agregar\n--> ");
        switch(seleccion){
            case 1: Archivo.writeFile(sintomas.get(0), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 2: Archivo.writeFile(sintomas.get(1), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 3: Archivo.writeFile(sintomas.get(2), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 4: Archivo.writeFile(sintomas.get(3), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 5: Archivo.writeFile(sintomas.get(4), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 6: Archivo.writeFile(sintomas.get(5), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 7: Archivo.writeFile(sintomas.get(6), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 8: Archivo.writeFile(sintomas.get(7), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;
            case 9: Archivo.writeFile(sintomas.get(8), "SintomasGenerados.txt");
                    System.out.println("Evento generado con exito\n");
                    InterfazConsola.printAdminSubEvents();
                    break;

            default:
                System.out.println("Ingrese una opcion valida");
                generarEvento();

        }
        generarEvento();
    }

    public void eliminarEvento(){
        Archivo.printFileLines("SintomasGenerados.txt");
        ArrayList<String> sintomasGenerados = Archivo.collectFileLines("SintomasGenerados.txt");
        Integer seleccion = Scanner.getInt("--> ");
        if(seleccion > 0 && seleccion <= sintomasGenerados.size()){
            Archivo.removeLine(sintomasGenerados.get(seleccion-1), "SintomasGenerados.txt");
        }
    }

    public void agregarCiudadano() {
        String CUIL = MetodosAuxiliares.pedirCUIL();
        System.out.println();
        String celular = MetodosAuxiliares.pedirCel();

        if (Check.checkAnses(CUIL, celular)) {
            Archivo.addToLocal(new Ciudadano(CUIL, celular));
            System.out.println("Ciudadano registrado con exito.");
        } else {
            System.out.println("Datos Invalidos");
            MetodosAuxiliares.delay(1500);
          //  InterfazConsola.clearScreen();
            agregarCiudadano();
        }


    }

    public void agregarPassword(String password){
        Archivo.writeFile(password, "PassAdmin");
    }
    public void deletePassword(String password){
        Archivo.removeLine(password, "PassAdmin");
    }

    public void eliminarCiudadano() {
        int opcion = Scanner.getInt("1. Buscar por CUIL\n2. Buscar por Celular\n--> ");

        switch (opcion) {
            case 1:
                String CUIL = Scanner.getString("Ingrese el CUIL del Ciudadano a eliminar\n--> ");
                Ciudadano c = Archivo.searchCUIL(CUIL);
                Archivo.removeLocal(c);
                break;
            case 2:
                String celular = Scanner.getString("Ingrese el Celular del Ciudadano a eliminar\n--> ");
                Ciudadano x = Archivo.searchCelular(celular);
                Archivo.removeLocal(x);
                break;
        }

    /*public void buscarInfo(String cuil_o_cel){
        Ciudadano c = buscar(cuil_o_cel);
        c.printCiudadano();

    }*/


    }

    public void buscarCiudadano(){
        System.out.println("Buscar ciudadano por: \n1. CUIL\n2. Celular");
        Integer seleccion = Scanner.getInt("--> ");
        Ciudadano buscado = null;
        switch (seleccion) {
            case 1:
                String cuil_de_buscado = Scanner.getString("Ingrese el CUIL del ciudadano:\n--> ");
                buscado = Archivo.searchCUIL(cuil_de_buscado);
                break;
            case 2:
                String celular_de_buscado = Scanner.getString("Ingrese el Celular del ciudadano:\n--> ");
                buscado = Archivo.searchCUIL(celular_de_buscado);
                break;
            default:
                System.out.println("Ingrese una opcion valida");
                buscarCiudadano();
        }

        buscado.printCiudadano();

        MetodosAuxiliares.delay(3000);
        InterfazConsola.printAdminMenu();
    }

    public void verMapa(){
        stats.mostrarMapa();
    }

    public void rankingZona() {
        stats.menuZonas();
    }
}





