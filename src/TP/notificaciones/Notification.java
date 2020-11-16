package TP.notificaciones;


    //Hacer una jerarquia de notificaciones porque hay varias notificaciones que si bien tienen el mimsmo comportamiento,
    //tienen distintos mensajes y distintas respuestas esperadas. Algunas ni siquiera responden a una respuesta.
    //las que responden a respuesta podrian extender de otro tipo de notificacion, dividir por respbndibles o no. O podrian implemetar una interfaz
    //
    //las notif que encontre son
    //  -solicitud de contacto
    //      -Notif de contacto aceptado para el que lo manda
    //      -Notif de contacto rechazado para el qeu lo manda

    // -Cuando un cnontacto tiene sintomas en las 47 hs


import TP.archivos.Archivo;
import TP.entidades.Fecha;
import TP.usuarios.Ciudadano;

public abstract class Notification {
    protected Ciudadano sender;
    protected Ciudadano receiver;

    public Notification( Ciudadano receiver, Ciudadano sender) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public abstract String serialize();
    public void deleteNotification(){
        Archivo.removeLine(this.serialize(), "Notificaciones.txt");
    }

    public abstract void printNotification();
    public void send(){
        Archivo.writeFile(this.serialize(), "Notificaciones.txt");
    }

    public static Notification deserialize(String toDeserialize){
        String[]splitLine = toDeserialize.split("@");
        switch(splitLine[0]){
            case "solicitud":
                String[] splitFecha = splitLine[3].split("-");
                Fecha fecha = new Fecha(Integer.parseInt(splitFecha[0]),Integer.parseInt(splitFecha[1]),Integer.parseInt(splitFecha[2]));
                ContactNotification notification = new ContactNotification(Archivo.searchCUIL(splitLine[1]), Archivo.searchCUIL(splitLine[2]), fecha);
                return notification;
            case "negacion":
                DeniedContactNotification notification1 = new DeniedContactNotification(Archivo.searchCUIL(splitLine[1]), Archivo.searchCUIL(splitLine[2]));
                return notification1;
            case "accept":
                AcceptedContactNotification notification2 = new AcceptedContactNotification(Archivo.searchCUIL(splitLine[1]), Archivo.searchCUIL(splitLine[2]));
                return notification2;
            case "covid":
                SickContactNotification notification3 = new SickContactNotification(Archivo.searchCUIL(splitLine[1]), Archivo.searchCUIL(splitLine[2]));
                return notification3;
        }

        return null;
    }


}
