package TP.notificaciones;

import TP.archivos.Archivo;
import TP.usuarios.Ciudadano;
import TP.util.MetodosAuxiliares;

public class AcceptedContactNotification extends Notification {

    public AcceptedContactNotification(Ciudadano sender, Ciudadano receiver) {
        super(sender,receiver);
        Archivo.writeFile(sender.getCUIL()+"-"+receiver.getCUIL(), "EncuentrosAceptados.txt");
    }

    @Override
    public String serialize() {
        String toSerialize = "accept@"+ receiver.getCUIL() + "@" +sender.getCUIL() ;
        return toSerialize;

    }

    @Override
    public void printNotification() {
        String toPrint = "Su solicitud de contacto ha sido aceptada por " + sender.getNombre();
        System.out.println(toPrint);
        MetodosAuxiliares.delay(2000);

    }
}
