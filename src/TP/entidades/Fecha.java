package TP.entidades;

public class Fecha {
    int day;
    int month;
    int year;

    public Fecha(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Fecha add48hs(){

        switch(this.month){
            //meses con 31
            case 1,4,5,7,8,10:
                if(day == 30){
                    return new Fecha(1, this.month+1, this.year);
                }
                else if(day == 31){
                    return new Fecha(2, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+2, this.month, this.year);
                }

            //diciembre
            case 12:
                if(day == 30){
                    return new Fecha(1, 1, this.year+1);
                }
                else if(day == 31){
                    return new Fecha(2, 1, this.year+1);
                }
                else{
                    return new Fecha(this.day+2, this.month, this.year);
                }

            //meses con 30
            case 3,6,9,11:
                if(day == 29){
                    return new Fecha(1, this.month+1, this.year);
                }
                else if(day == 30){
                    return new Fecha(2, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+2, this.month, this.year);
                }


            //febrero
            case 2:
                if(day == 27){
                    return new Fecha(1, this.month+1, this.year);
                }
                else if(day == 28){
                    return new Fecha(2, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+2, this.month, this.year);
                }


        }
        return null;
    }

    public Fecha add24hs(){
        switch(this.month){
            //meses con 31
            case 1,4,5,7,8,10:
                if(day == 31){
                    return new Fecha(1, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+1, this.month, this.year);
                }

                //diciembre
            case 12:
                if(day == 31){
                    return new Fecha(1, 1, this.year+1);
                }
                else{
                    return new Fecha(this.day+1, this.month, this.year);
                }

                //meses con 30
            case 3,6,9,11:
                if(day == 30){
                    return new Fecha(1, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+1, this.month, this.year);
                }


                //febrero
            case 2:
                if(day == 28){
                    return new Fecha(1, this.month+1, this.year);
                }
                else{
                    return new Fecha(this.day+1, this.month, this.year);
                }


        }
        return null;

    }



    public String toString(){
        return day + "-" + month + "-" + year;


    }

}
