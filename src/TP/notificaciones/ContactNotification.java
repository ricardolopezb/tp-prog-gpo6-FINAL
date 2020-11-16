package TP.notificaciones;

import TP.archivos.Archivo;
import TP.entidades.Encuentro;
import TP.entidades.Fecha;
import TP.usuarios.Ciudadano;
import TP.util.Scanner;

public class ContactNotification extends Notification {


    private Fecha fechaDeFin;

    public ContactNotification(Ciudadano receiver,Ciudadano sender,  Fecha fechaDeFin) {
        super(receiver, sender);
        this.fechaDeFin = fechaDeFin;
    }

    @Override
    public String serialize() {
        String serialized = "solicitud@"+ receiver.getCUIL() + "@" + sender.getCUIL() + "@" + fechaDeFin.toString();
        return serialized;
    }


    @Override
    public void printNotification() {
        String[] fechaDividida = fechaDeFin.toString().split("-");
        String toShow = "--- Solicitud de Encuentro ---" + "\n" +
                sender.getNombre() + " dice que tuvieron contacto el " + fechaDividida[0] +
                "/"+fechaDividida[1];
        System.out.println(toShow);
    }

    public void getResponse(){
        String[] fechaDividida = fechaDeFin.toString().split("-");
        char seleccion = Character.toLowerCase(Scanner.getChar("Acepta? Y/N\n--> "));
        switch(seleccion){
            case 'y':
                Encuentro nuevoEncuentro = new Encuentro(sender.getCUIL(), receiver.getCUIL(), new Fecha(Integer.parseInt(fechaDividida[0]),Integer.parseInt(fechaDividida[1]),Integer.parseInt(fechaDividida[2])));
                if(receiver.getAnterior() == null){
                    receiver.setAnterior(nuevoEncuentro);
                } else {
                    receiver.setAnte_anterior(receiver.getAnterior());
                    receiver.setAnterior(nuevoEncuentro);
                }

                if(sender.getAnterior() == null){
                    sender.setAnterior(nuevoEncuentro);

                } else{
                    sender.setAnte_anterior(sender.getAnterior());
                    sender.setAnterior(nuevoEncuentro);
                }
                AcceptedContactNotification notifAceptada = new AcceptedContactNotification(sender, receiver);
                notifAceptada.send();
                sender.overwrite();
                receiver.overwrite();
                break;
            case 'n':
                receiver.addSolicitudRechazada();
                sender.addSolicitudRechazada();
                DeniedContactNotification notifDeNegacion = new DeniedContactNotification(sender, receiver);
                notifDeNegacion.send();
                sender.overwrite();
                receiver.overwrite();
        }
    }

}
