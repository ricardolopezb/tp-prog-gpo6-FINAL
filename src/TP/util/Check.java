package TP.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Check {


    public static boolean checkCUIL(String cuil){
        //chequear que el numero que se ingrese es valido en forma, o sea que tenga la cant de numeros que son, que no haya espacios,
        return checkNumero(cuil, 11);

    }

    public static boolean checkCelular(String celular) {
        return checkNumero(celular, 10);
    }

    private static boolean checkNumero(String numero, int digitos){
        //lo mismo que arriba. un numero de tlf tiene 10 digitos
        if (numero == null || numero.length() != digitos) {
            return false;
        }
        try {
            long x = Long.parseLong(numero);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
        }

    public static boolean checkAnses(String CUIL, String celular){ //este metodo se usa solo en el registro
        //checkea que el cuil y el numero esten en el .txt y que esten juntos.
        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\TP\\archivos\\BaseAnses.txt"));
            String line = br.readLine();
            while(line != null){
                String[] x = line.split("\t");
                if(CUIL.equals(x[1]) && celular.equals(x[2])){
                    return true;
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.getMessage();
        }
        //System.out.println("no entró al try");
        return false;
    }

    public static boolean checkPassAdmin(String pass){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\TP\\archivos\\PassAdmin"));
            String line = br.readLine();
            while(line != null){
                if(line.equals(pass)){
                    return true;
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.getMessage();
        }
        return false;
    }


}
