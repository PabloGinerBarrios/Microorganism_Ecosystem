/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;

/**
 * Clase per a la creació y control de les Algues dins del sistema. Subclase d'Esser.
 * @author pablo
 */
public final class Alga extends Esser {
    private static int totalAlgues; //Atribut de classe. Nombre total de bacteris dins del sistema.
    private Aliment aliment; //Atribut d'instancia. Aliment del que s'alimenta un alga concreta.
    private boolean reproduccio; //Atribut de clase per a indicar si un alga es capaz de reproduirse.
    
    /**
     * Constructor per a la creacio d'una nova Alga. No recibeix res com a argument. La creació d'una nova Alga es fa de forma automàtica.
     */
    public Alga() {
        super("ALGA", pesAlga); //Invocaciò al mètode constructor de la superclase Esser. S'envia el valor del nom y del pes que, en aquest cas, son iguals per a totes les Algues.
        this.aliment = Aliment.Nutrient; //Els bacteris sols menjen nutrients.
        this.reproduccio = true; //Les algues es reprodueixen.
        totalAlgues++; //Es suma 1 al total de algues amb la creació de cada nova Alga.
    }

    /**
     * Mètode Getter per a l'atribut totalAlgues.
     * @return Retorna el valor de l'atribut totalAlgues.
     */
    public static int dirPoblacio() {
        return totalAlgues;
    }
    
    /**
     * Mètode que redueix en 1 el valor de totalAlgues y crida al mètode setTotalEssers per a disminuir el atribut totalEssers en 1.
     */
    @Override
    public void reduirPoblacio() {
        totalAlgues--;
        setTotalEssers(Esser.dirPoblacio()-1);
    }
    
    /**
     * Mètode que retorna una String per a informar sobre el nom y el pes d'un esser. Es crida als mètodes dirNom() y dirPes() perque ambdos son atributs privats de la classe Esser. 
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarEstat() {
        return String.format("#%s => PES: %d%n",this.dirNom(), this.dirPes());   
    }
    
    /**
     * Mètode que retorna una String per a informar el nom, el pes, el aliment y la reproduccio d'un esser concret. 
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarDetall() {
        return "#" + this.dirNom()+ " => " + "PES: " + this.dirPes() + " - " + "ALIMENTACIÓ: " + this.aliment + " - " + "REPRODUCCIÓ: SI";
    }
    
    /**
     * Mètode que controla com menjen les algues.
     * Les algues sempre tenen nutrients a la seua disposicó, per aço sempre que intenten menjar el consegueixen.
     * El alga que menja suma al seu pes el pes base del nutrient menjat.
     * S'imprimeix una frase informativa sobre el menjar y el nou pes de l'alga que ha menjat.
     * Si el alga, amb el nou pes, compleix la condició de reproducció es crida al mètode reproduir() per a que es reproduixca.
     * @param essers Recibeix com a parametre la lista que conté tots els essers del sistema.
     * @throws Exception Te el throws Exception per que crida a un métode que genera excepcions. 
     */
    @Override
    public void menjar(ArrayList essers) throws Exception {
          this.canviaPes(this.dirPes()+pesNutrients);
          System.out.printf("ALIMENTACIÓ**** %s: m'he menjat un NUTRIENT. Ara pese %d%n", this.dirNom(), this.dirPes());
          if (this.dirPes() > (2*pesAlga*pesReproduccio)) {
            this.reproduir(essers);
        }
    }
    
    /**
     * Mètode que controla com es reprodueixen les algues. 
     * Si compleix la condició marcada, les algues poden reproduirse varies vegades fins que deixen de complir un altra condició. 
     * En el cas de que es reproduixca, en cada iteració del bucle es crea una nova alga amb el pes base de les algues.
     * El pes del Alga que es rerodueix es veu reduit en el pes de la nova alga.
     * S'informa de la reproducció amb la correspondent frase.
     * @param essers Llista d'essers. 
     * @throws Exception 
     */
    @Override
    public void reproduir(ArrayList essers) throws Exception {
        //Condició principal per a entrar en el bucle de reproducció desenfrenada.
        if (this.dirPes() > (2*pesAlga)*pesReproduccio) {
            //Condició per a mantenirse en el bucle de desenfreno.
            while (this.dirPes() >= pesAlga*pesReproduccio) {
                //Es crea una nova alga dins de la llista.
                essers.add(new Alga());
                //Es canvia el pes de l'alga que es reprodueix.
                this.canviaPes(-pesAlga);
                //Intanciació d'un esser per a poder cridar al mètode dirNom() en la frase informativa.
                Alga planta = (Alga) essers.get(essers.size()-1);
                //S'imprimeix la frase informativa de la reproducció.
                System.out.printf("REPRODUCCIÓ**** %s m'he reproduit y he creat a %s. Ara pese %d%n", this.dirNom(), planta.dirNom(), this.dirPes());
            } 
        //Es genera una excepció si l'alga que intenta reproduirse no compleix la condició principal. 
        }else {
            throw new Exception(String.format("REPRODUCCIÓ**** %s amb un pes de %d no em puc reproduir", this.dirNom(), this.dirPes()));
        }
    }
}
