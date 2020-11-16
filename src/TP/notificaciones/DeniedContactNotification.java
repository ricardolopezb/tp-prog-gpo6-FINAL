package TP.notificaciones;

import TP.usuarios.Ciudadano;
import TP.util.MetodosAuxiliares;

public class DeniedContactNotification extends Notification {


    public DeniedContactNotification(Ciudadano sender, Ciudadano receiver) {
        super(sender,receiver);
    }

    @Override
    public String serialize() {
        String toSerialize = "negacion@"+ receiver.getCUIL() + "@" +sender.getCUIL() ;
        return toSerialize;
    }

    @Override
    public void printNotification() {
        String toPrint = "Su solicitud de contacto ha sido negada por " + sender.getNombre();
        System.out.println(toPrint);
        MetodosAuxiliares.delay(2000);

    }

}
