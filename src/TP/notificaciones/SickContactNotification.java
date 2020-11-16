package TP.notificaciones;

import TP.usuarios.Ciudadano;

public class SickContactNotification extends Notification {

    public SickContactNotification(Ciudadano receiver, Ciudadano sender) {
        super(receiver, sender);
    }

    @Override
    public String serialize() {
        String toSerialize = "covid@"+ receiver.getCUIL() + "@" +sender.getCUIL() ;
        return toSerialize;

    }

    @Override
    public void printNotification() {
        String toPrint = "Su contacto " + sender.getNombre() + " ha presentado sintomas. Recomendamos precaucion.";
        System.out.println(toPrint);

    }
}
