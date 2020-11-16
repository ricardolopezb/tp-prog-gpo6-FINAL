package TP.interfaz;

import TP.archivos.Archivo;
import TP.entidades.Evento;
import TP.usuarios.Admin;
import TP.usuarios.Ciudadano;
import TP.util.Check;
import TP.util.MetodosAuxiliares;
import TP.util.Scanner;


public class InterfazConsola {
    static Admin admin = new Admin();
    static Ciudadano ciudadano;

    public static void main(String[] args) {
    /*
        "menu de entrada e interacicon
     */
        Admin admin = new Admin();
        System.out.println("********** TraceIt **********\n");
        System.out.println("Ingrese el numero de la accion que desee ejecutar:");
        switch (printInicio()) {
            case 1:
              //  clearScreen();
                System.out.println("Ingrese su CUIL");
                String cuil_ingresado = Scanner.getString("--> ");
                if(Archivo.checkCuilInLocal(cuil_ingresado)) {
                    ciudadano = Archivo.searchCUIL(cuil_ingresado);
                    if(ciudadano.isBloqueado()){
                        System.out.println("Ciudadano Bloqueado. Contacte a Soporte.");
                        System.exit(0);
                    }
                    printLogeoExistoso();
                }
                break;

            case 2:
                //clearScreen();
                System.out.println("Ingrese su Celular");
                String cel_ingresado = Scanner.getString("--> ");
                if(Archivo.checkCelInLocal(cel_ingresado)) {
                    ciudadano = Archivo.searchCelular(cel_ingresado);
                    if(ciudadano.isBloqueado()){
                        System.out.println("Ciudadano Bloqueado. Contacte a Soporte.");
                        System.exit(0);
                    }
                    printLogeoExistoso();
                }
                break;

            case 3:
                //clearScreen();
                admin.agregarCiudadano();
                MetodosAuxiliares.delay(1500);
                main(args);
                break;

            case 9:
                //clearScreen();
                printPassAdmin();
                break;

        }


    }

    private static void printPassAdmin() {
        /*
        validacion de contraseña de administrador
         */
        String pass = Scanner.getString("Ingrese su contraseña: ");
        if(Check.checkPassAdmin(pass) ){
            // si la contraseña es valida accede al menu especifico
            printAdminMenu();
        }else {
            System.out.println("Datos invalidos");
            MetodosAuxiliares.delay(1500);
            printPassAdmin();
        }
    }

    //*************************** entrada del case 1 y 2 **************************************************//
    public static void printLogeoExistoso() { //para ingreso por CUIL o celular
      /*
      para una vez que se complete el registro e ingreso:
       */
        System.out.println("********** TraceIt **********");
        System.out.println("Bienvenido, "+ciudadano.getNombre()+"\n");
        System.out.println("1. Síntomas");
        System.out.println("2. Reportar contacto cercano");
        System.out.println("3. Notificaciones\n");
        System.out.println("9. Salir");

        switch (Scanner.getInt("--> ")) {
            case 1:
                printSintoma();
                break;
            case 2:
                ciudadano.solicitudDeContacto();
                break;
            case 3:
                ciudadano.showNotifications();
                break;
            case 9:
                ciudadano.overwrite();
                System.exit(0);
            default:
                System.out.println("Por favor, ingrese una opcion válida.");
                printLogeoExistoso();
                break;
        }
    }

    private static void printSintoma() {
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Reportar sintoma");
        System.out.println("2. Bajar sintoma");
        System.out.println("0. Regresar");

        switch (Scanner.getInt("--> ")){
            case 0:
                printLogeoExistoso();
                break;
            case 1:
                darAltaSintoma();
                break;
            case 2:
                darBajaSintoma();
                break;
            default:
                printSintoma();
                break;
        }
    }
    private static void darAltaSintoma() {
        ciudadano.agregarSintoma();
        MetodosAuxiliares.delay(1500);
        printSintoma();
    }
    private static void darBajaSintoma(){
        ciudadano.removerSintoma();
        MetodosAuxiliares.delay(1500);
        printSintoma();
    }


//******************************* cierre entrada 1 y 2 ******************************************//

//******************************* menu del administrador ****************************************//
    public static void printAdminMenu(){
        /*
        para administrador:
         */
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Manejar Ciudadanos");
        System.out.println("2. Notificaciones");
        System.out.println("3. Manejar Eventos");
        System.out.println("4. Buscar Ciudadanos");
        System.out.println("5. Ver Estadisticas");
        System.out.println("6. Manejar Acceso\n");
        System.out.println("9. Salir");

        switch(Scanner.getInt("--> ")){
            case 1:
                printAdminSubManejar();
                break;
            case 2:
                printAdminSubNotif();
                break;
            case 3:
                printAdminSubEvents();
                break;
            case 4:
                admin.buscarCiudadano();
                break;
            case 5:
                printAdminEstadistica();
                break;
            case 6:
                printManejarAcceso();
                break;
            case 9:
                System.exit(0);

            default:
                printAdminMenu();
                break;
        }

    }
    public static void printAdminEstadistica(){
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Ver Ranking de Sintomas por Zona");
        System.out.println("2. Ver listado de Brotes actuales");
        System.out.println("3. Ver Mapa\n");
        System.out.println("0. Regresar");

        switch (Scanner.getInt("--> ")){
            case 1:
                admin.rankingZona();
                printAdminMenu();
                break;
            case 2:
                admin.verBrotes();
                printAdminMenu();
                break;
            case 3:
                admin.verMapa();
                printAdminMenu();
                break;
            case 0:
                printAdminMenu();
                break;
        }
    }

    public static void printManejarAcceso(){
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Agregar Nueva Contraseña");
        System.out.println("2. Eliminar Contraseña");
        System.out.println("\n0. Regresar");

        switch(Scanner.getInt("--> ")){
            case 1:
                String newPassword = Scanner.getString("Ingrese la nueva contraseña: \n--> ");
                admin.agregarPassword(newPassword);
                printAdminMenu();
                break;
            case 2:
                Archivo.printFileLines("PassAdmin");
                System.out.println("\nEscriba la contraseña que quiere eliminar:");
                String passwordToDelete = Scanner.getString("--> ");
                admin.deletePassword(passwordToDelete);
                printAdminMenu();
                break;
            case 0:
                printAdminMenu();
                break;
        }


    }


    public static void printAdminSubManejar() {
        /*
        manejo de los ciudadanos
         */
        //bloquear / desbloquear / agregar / eliminar
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Desbloquear Ciudadanos");
        System.out.println("2. Agregar Ciudadanos");
        System.out.println("3. Eliminar Ciudadanos");
        System.out.println("\n0. Regresar");

        Admin admin = new Admin();

        switch(Scanner.getInt("--> ")){
            case 0:
                printAdminMenu();
                break;
            case 1:
                admin.manejarBloqueados();


                //admin.desbloquear(c);

            case 2:
                admin.agregarCiudadano();
                break;
            case 3:
                //borra all:(
                admin.eliminarCiudadano();
                break;
            default:
                System.out.println("Por favor, ingrese una opcion válida.");
                printAdminSubManejar();
                break;
        }


    }


    public static void printAdminSubNotif() {
        /*
        para tener acceso a las notificaciones
         */
        //brotes / usuarios bloqueados
        System.out.println("********** TraceIt **********\n");
        System.out.println("1. Notificaciones de brote por zona (a implementar)");
        System.out.println("2. Notificaciones de ciudadanos bloqueados (a implementar)");
        System.out.println("\n0. Regresar");

        switch (Scanner.getInt("--> ")){
            case 0:
                printAdminMenu();
                break;
            case 1:
                printNotifBrotes();
                break;
            case 2:
                printCiudadanosBloqueados();
                break;
            default:
                printAdminSubNotif();
                break;

        }
    }
    private static void printNotifBrotes() {
        //donde hay brotes segun la relacion con los sintomas
    }
    private static void printCiudadanosBloqueados() {
        //cuantos y quienes son los usuarios bloqueados
    }


    public static void printAdminSubEvents() {
        /*
        para el manejo de eventos
         */
        //crear / eliminar / ver E / top 3 eventos por zona
        //manejo de eventos
        System.out.println("********** TraceIt **********\n");
        System.out.println("Manejo de eventos");
        System.out.println("1. Generar evento");
        System.out.println("2. Eliminar evento");
        System.out.println("3. Ver eventos existentes");
        System.out.println("4. Ver top de eventos por zona");
        System.out.println("\n0. Regresar");

        switch (Scanner.getInt("--> "))   {
            case 0:
                printAdminMenu();
                break;
            case 1:
                admin.generarEvento();
                break;
            case 2:
                admin.eliminarEvento();
                printAdminSubEvents();
                break;
            case 3:
                Archivo.printFileLines("SintomasGenerados.txt");
                MetodosAuxiliares.delay(3000);
                printAdminSubEvents();
                break;
            case 4:
                admin.rankingZona();
                break;
            default:
                System.out.println("Ingrese una opcion valida. ");
                printAdminSubEvents();
                break;

        }
    }

    private static void printTopEvent() {
        //top 3 de eventos
    }


    public static void printAdminSubBuscar() {
        //busqueda por cuil o celular
    }


    public static void printAdminMapa() {
        //para ver el mapa
    }

//************************************** cierre menu del administrador ******************************************//




    public static int printInicio(){
        /*
        menu inicial para el ingreso y registro, tanto como ciudadano como administrador
         */
        System.out.println("1. Ingresar con CUIL");
        System.out.println("2. Ingresar con Celular");
        System.out.println("3. Registrarse\n");
        System.out.println("9. Ingresar como Administrador");
        return Scanner.getInt("--> ");
    }

   public static void clearScreen(){
        for (int i = 0; i < 30 ; i++) {
            System.out.println();

        }
    }


}
