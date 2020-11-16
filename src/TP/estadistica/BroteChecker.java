package TP.estadistica;

import TP.archivos.Archivo;
import TP.usuarios.Ciudadano;

import java.util.ArrayList;

public class BroteChecker {
    // caso positivo:  cuil@zona
    // encuentrosAceptados: sender.getCUIL()+"-"+receiver.getCUIL()


    public Brote checkBrotes(){
        if(!checkQuantity()) return null;
        Ciudadano intermediarioBrote = checkPositiveContact();
        if(intermediarioBrote != null){
            Brote broteEncontrado = new Brote(new Zona(intermediarioBrote.getZona()));
            return broteEncontrado;
        }
        return null;

    }


    private boolean checkQuantity(){
        ArrayList<String> casosPositivos = Archivo.collectFileLines("CasosPositivos.txt");
        return casosPositivos.size() >= 5;

    }


    public String[] checkIntermediario(){
        ArrayList<String> lineasEncuentros = Archivo.collectFileLines("EncuentrosAceptados.txt");
        String[] toReturn = new String[3];
        for (int i = 0; i < lineasEncuentros.size(); i++) {
            String[] cuilsInvolucrados = lineasEncuentros.get(i).split("-");
            for (int j = 0; j < lineasEncuentros.size(); j++) {
                String[] otraLinea_cuils = lineasEncuentros.get(j).split("-");
                if(cuilsInvolucrados[0].equals(otraLinea_cuils[1])){
                    toReturn[0] = cuilsInvolucrados[0];
                    toReturn[1] = cuilsInvolucrados[1];
                    toReturn[3] = otraLinea_cuils[0];
                    return toReturn;
                }
            }
        }
        return null;
    }

    public Ciudadano checkPositiveContact(){
        String[] datosContagio = checkIntermediario();
        if(datosContagio == null) return null;

        Ciudadano intermediario = Archivo.searchCUIL(datosContagio[0]);
        Ciudadano receptorDeCovid = Archivo.searchCUIL(datosContagio[1]);
        Ciudadano vectorDeCovid = Archivo.searchCUIL(datosContagio[3]);

        if(intermediario.getCovid() && vectorDeCovid.getCovid() && receptorDeCovid.getCovid()){
            return intermediario;
        }

        return null;



    }





}
