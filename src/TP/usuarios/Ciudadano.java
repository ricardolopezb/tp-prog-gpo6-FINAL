 package TP.usuarios;

import TP.archivos.Archivo;
import TP.entidades.Encuentro;
import TP.entidades.Evento;
import TP.entidades.Fecha;
import TP.interfaz.InterfazConsola;
import TP.notificaciones.ContactNotification;
import TP.notificaciones.Notification;
import TP.notificaciones.SickContactNotification;
import TP.util.MetodosAuxiliares;
import TP.util.Scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ciudadano {
    private boolean bloqueado;
    private final String nombre;
    private final String CUIL;
    private final String celular;
    private Encuentro anterior;
    private Encuentro ante_anterior;
    private ArrayList<Evento> sintomas;
    private String zona;
    private int solicitudesRechazadas;
    private boolean covid;

    //  CUIL\t Celular\t Bloqueado\t Zona\t Rechazos\t Sintoma1,Sintoma2,Sintoma3\t(2002049954(CUIL CIUD 1),1924894392(CUIL CIUD 2),100920(Fecha de Inicio en numero),
    //                      250920(fecha de fin en numero)


    //este se va a usar cuando se cree un ciudadano por primera vez
    public Ciudadano(String CUIL, String celular) {
        this.CUIL = CUIL;
        this.celular = celular;
        this.bloqueado = false;
        solicitudesRechazadas = 0;
        this.zona = MetodosAuxiliares.zonaEnAnses(CUIL);
        anterior = null;
        ante_anterior = null;
        this.nombre = MetodosAuxiliares.nombreEnAnses(CUIL);
        sintomas = new ArrayList<>();
        this.covid = false;


    }

    //Este constructor se va a usar cuando se recarguen todos los ciudadanos a partir de nuestra base local
    public Ciudadano(String CUIL, String celular, boolean bloqueado, String zona, int solicitudesRechazadas, ArrayList<Evento> sintomas, Encuentro anterior, Encuentro posterior) {
        this.bloqueado = bloqueado;
        this.CUIL = CUIL;
        this.celular = celular;
        this.anterior = anterior;
        this.ante_anterior = posterior;
        this.sintomas = sintomas;
        this.zona = zona;
        this.nombre = MetodosAuxiliares.nombreEnAnses(CUIL);
        this.solicitudesRechazadas = solicitudesRechazadas;
        checkCovid();
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
        overwrite();
    }

    public void setSolicitudesRechazadas(int solicitudesRechazadas) {
        this.solicitudesRechazadas = solicitudesRechazadas;
    }

    public void addSolicitudRechazada(){
        this.solicitudesRechazadas++;
        checkSolicitudes();
        overwrite();
    }

    private void checkSolicitudes() {
        if(this.solicitudesRechazadas == 5){
            this.bloqueado = true;
            Archivo.writeFile(this.toString(), "CiudadanosBloqueados.txt");
        }
    }

    public void printCiudadano(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("CUIL: " + this.CUIL);
        System.out.println("Celular: " + this.celular);
        System.out.println("Zona: " + this.zona);


    }

    public String getNombre() {
        return nombre;
    }

    public String toString(){
        String strSintomas = "";
        String anteriorStr;
        String posteriorStr;
        if(sintomas.size() == 0) {
            strSintomas = "null";
        } else{
            for (Evento e : sintomas) {
                strSintomas += e.getNombre() +"@"+ e.getFecha().toString() +",";

            }
        }
        //(2002049954(CUIL CIUD 1),1924894392(CUIL CIUD 2),100920(Fecha de Inicio en numero),250920(fecha de fin en numero)
        try{
            anteriorStr = anterior.toString();
        } catch (NullPointerException e){
            anteriorStr = "null";
        }
        try{
            posteriorStr = ante_anterior.toString();
        } catch (NullPointerException e){
            posteriorStr = "null";
        }

        String display = CUIL +"\t"+ celular +"\t"+ bloqueado +"\t"+ zona +"\t"+ solicitudesRechazadas +"\t"+ strSintomas +"\t" + anteriorStr +"\t" + posteriorStr;

        return display;

    }

    public void checkCovid(){
        if(sintomas.size() >= 2){
            this.covid = true;
            Archivo.writeFile(this.CUIL+"@"+this.zona, "CasosPositivos.txt");
            chequearCercaniaSintomas();
            overwrite();
        }
    }

    public void initializeEncuentro(){
        if(this.ante_anterior != null){
            ante_anterior.initializeCiudadanos();
        }
        if(this.anterior != null){
            anterior.initializeCiudadanos();
        }
    }

    public String getCUIL(){
        return this.CUIL;
    }

    public String getCelular() {
        return celular;
    }

    public void agregarSintoma(){
        String file = "SintomasGenerados.txt";
        Archivo.printFileLines(file);
        ArrayList<String> sintomasPosibles = Archivo.collectFileLines(file);
        Integer seleccion = Scanner.getInt("Seleccione su sintoma\n--> ");
        Fecha fechaDeSintoma = MetodosAuxiliares.pedirFecha();
        switch(seleccion){
            case 1:
                sintomas.add(new Evento(sintomasPosibles.get(0), fechaDeSintoma));
                break;
            case 2: sintomas.add(new Evento(sintomasPosibles.get(1), fechaDeSintoma));
                break;
            case 3:sintomas.add(new Evento(sintomasPosibles.get(2), fechaDeSintoma));
                break;
            case 4:sintomas.add(new Evento(sintomasPosibles.get(3), fechaDeSintoma));
                break;
            case 5:sintomas.add(new Evento(sintomasPosibles.get(4), fechaDeSintoma));
                break;
            case 6:sintomas.add(new Evento(sintomasPosibles.get(5), fechaDeSintoma));
                break;
            case 7:sintomas.add(new Evento(sintomasPosibles.get(6), fechaDeSintoma));
                break;
            case 8: sintomas.add(new Evento(sintomasPosibles.get(7), fechaDeSintoma));
                break;
            case 9: sintomas.add(new Evento(sintomasPosibles.get(8), fechaDeSintoma));
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
        checkCovid();
        overwrite();
    }

    private void chequearCercaniaSintomas(){
        Evento ultimoSintoma = sintomas.get(sintomas.size() - 1);
        for (int i = 0; i < sintomas.size()-1 ; i++) {
            if(ultimoSintoma.getFecha().toString().equals(sintomas.get(i).getFecha().toString())){
                if(anterior != null){
                    SickContactNotification sickNotif = new SickContactNotification(Archivo.searchCUIL(anterior.getOtherCUIL(this)), this);
                    sickNotif.send();
                }
                if(ante_anterior != null){
                    SickContactNotification sickNotif = new SickContactNotification(Archivo.searchCUIL(ante_anterior.getOtherCUIL(this)), this);
                    sickNotif.send();
                }
                return;

            }

            if(anterior != null){
                String fechaDeSintoma = ultimoSintoma.getFecha().toString();
                if (fechaDeSintoma.equals(anterior.getFechaFin().toString()) || fechaDeSintoma.equals(anterior.getFinMas24hs().toString()) || fechaDeSintoma.equals(anterior.getFinMas48hs().toString())) {
                    SickContactNotification sickNotif = new SickContactNotification(Archivo.searchCUIL(anterior.getOtherCUIL(this)), this);
                    sickNotif.send();
                }
            }
            if(ante_anterior != null){
                String fechaDeSintoma = ultimoSintoma.getFecha().toString();
                if (fechaDeSintoma.equals(ante_anterior.getFechaFin().toString()) || fechaDeSintoma.equals(ante_anterior.getFinMas24hs().toString()) || fechaDeSintoma.equals(anterior.getFinMas48hs().toString())) {
                    SickContactNotification sickNotif = new SickContactNotification(Archivo.searchCUIL(ante_anterior.getOtherCUIL(this)), this);
                    sickNotif.send();
                }
            }

        }





    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void removerSintoma(){
        System.out.println("Seleccione el Sintoma a remover: ");
        for (int i = 0; i < sintomas.size(); i++) {
            System.out.println((i+1)+ ". " + sintomas.get(i).getNombre());
        }
        Integer seleccion = Scanner.getInt("--> ");
        if(seleccion >0 && seleccion <= sintomas.size()){
            sintomas.remove(seleccion-1);
        }
    }

    public void overwrite(){
        Archivo.removeLocal(this);
        Archivo.addToLocal(this);
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public ArrayList<Evento> getSintomas() {
        return sintomas;
    }

    public void solicitudDeContacto(){
        System.out.println("Buscar ciudadano por: \n1. CUIL\n2. Celular");
        Integer seleccion = Scanner.getInt("--> ");
        Ciudadano buscado = null;
        switch (seleccion){
            case 1:
                String cuil_de_buscado = Scanner.getString("Ingrese el CUIL del ciudadano:\n--> ");
                buscado = Archivo.searchCUIL(cuil_de_buscado);
                break;
            case 2:
                String celular_de_buscado = Scanner.getString("Ingrese el Celular del ciudadano:\n--> ");
                buscado = Archivo.searchCelular(celular_de_buscado);
                break;
            default:
                System.out.println("Ingrese una opcion valida\n");
                //clearScreen();
                solicitudDeContacto();

        }
        Fecha fechaDeEncuentro = MetodosAuxiliares.pedirFecha();
        ContactNotification notifDeContacto = new ContactNotification(buscado, this, fechaDeEncuentro);
        notifDeContacto.send();
        System.out.println("Contacto realizado con exito\n");
        InterfazConsola.printLogeoExistoso();

    }

    public Notification checkNotifications(){
        try(BufferedReader br = new BufferedReader(new FileReader("src\\TP\\archivos\\Notificaciones.txt"));){
            String line = br.readLine();
            while(line != null){
                String[] lineContents = line.split("@");
                if(lineContents[1].startsWith(this.CUIL)){
                    return Notification.deserialize(line);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void showNotifications(){
        Notification notificacion = checkNotifications();
        if (notificacion == null){
            System.out.println("No hay notificaciones para mostrar...\n");
            InterfazConsola.printLogeoExistoso();
        } else{
            if(notificacion instanceof ContactNotification){
                notificacion.printNotification();
                notificacion.deleteNotification();
                ((ContactNotification) notificacion).getResponse();
            } else{
                notificacion.printNotification();
                notificacion.deleteNotification();

            }
            InterfazConsola.printLogeoExistoso();
        }


    }

    public Encuentro getAnterior() {
        return anterior;
    }

    public void setAnterior(Encuentro anterior) {
        this.anterior = anterior;
    }

    public Encuentro getAnte_anterior() {
        return ante_anterior;
    }

    public void setAnte_anterior(Encuentro ante_anterior) {
        this.ante_anterior = ante_anterior;
    }

    public boolean getCovid() {
        return this.covid;
    }
}
