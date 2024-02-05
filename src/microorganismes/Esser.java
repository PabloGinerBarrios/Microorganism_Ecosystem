/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

/**
 *
 * @author pablo
 * Superclasse del sistema. 
 */
public abstract class Esser implements Alimentacio, Poblacio {
    //declaraciò del atributs.
    private static int totalEssers; //Atribur de classe. Contador del nombre total d'essers.
    private static int consecutiu; //tribut de classe. Identificador per als noms dels essers creats. 
    private String nom; //Atribut d'instancia. Nom de cada esser. 
    private int pes; //Atribut d'instancia. Pes de cada esser. 
    
    /**
     * Contructor de la clase esser. Recibeix el nom y el pes de les subclasses per a crear un nou esser y suma 1 al total de essers y al consecutiu, que, ademès, concatena al final del nom recibit.
     * @param nom Nom de cada esser.
     * @param pes Pes de cada esser. 
     */
    public Esser (String nom, int pes) {
        this.nom = nom + (consecutiu+1); //Es concatena, al nom recibit desde les subclasses, el consecutiu com a identificador de cada nou esser. 
        this.pes= pes; //S'estableix el pes, recibit desde les subclases, com a pes del nou esser. 
        totalEssers++; //S'actualitza l'atribut totalEssers sumant'li 1 amb la creaciò de cada nou esser. 
        consecutiu++; //S'actualitza l'atribut consecutiu sumant'li 1 amb la creaciò de cada nou esser.
    }
    
    /**
     * Mètode per a modificar el contador del nombre total d'essers del sistema. 
     * @param totalEssers nova quantitat total d'essers en el sistema.
     */
    public static void setTotalEssers(int totalEssers) {
        Esser.totalEssers = totalEssers;
    }
    
    /**
     * mètode pero a coneixer el valor de l'atribut consetutiu.
     * @return retorna el valor de l'atribut consecutiu.
     */
    public static int getConsecutiu() {
        return consecutiu;
    }
    
    /**
     * Mètode Getter de l'atribut nom.
     * @return Retorna el valor de l'atribut nom d'un esser.
     */
    public final String dirNom() {
        return nom;
    }

    /**
     * Mètode Getter de l'atribut Pes.
     * @return Retorna el valor de l'atribut pes d'un esser.
     */
    public final int dirPes() {
        return pes;
    }
    
    /**
     * Mètode per a canviar el pes d'un esser en una quantitat recibida com argument.
     * @param quantitat quantitat en la que serà canviat el pes d'un esser.
     */
    public final void canviaPes(int quantitat)  {
            this.pes+=quantitat;   
    }
    
    /**
     * Mètode per a generar una quantitat concreta de nombres enters recibida com argument des de un nombre inicial recibit con a argument.
     * @param inicial minim per a aleatori.
     * @param quantitat quantitat de nombres posibles desde l'inicial.
     * @return 
     */
    public static final int generaAleatori(int inicial, int quantitat) {
        int aleatori = (int)(inicial+(Math.random()*quantitat));
        return aleatori;
    }
    
    /**
     * Mètode Getter per a l'atribut de classe totalEssers.
     * @return Retorna el valor de l'atribut totalEssers. 
     */
    public static int dirPoblacio() {
        return totalEssers; 
    }
    
    //Declaració dels métodes abstractes de la clase Esser que deuran ser implementats per les subclases. 
    public abstract String mostrarEstat();
    public abstract String mostrarDetall();
}
