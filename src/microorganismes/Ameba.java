/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;

/**
 *Clase por a la creació y control de les Amebes dins del sistema. Subclase de Esser. 
 * @author pablo
 */
public final class Ameba extends Esser{
    //Declaració del atributs.
    private static int totalAmebes; //Atribut de classe. Nombre total d'amebes dins del sistema.
    private Aliment aliment; //Atribut d'instancia. Aliment del que s'alimenta una ameba concreta. 
    private boolean reproduccio; //Atribut de clase per a indicar si una ameba es capaz de reproduirse. 
    
    /**
     * Constructor per a la creacio de una nova Emeba. No recibeix res con a argument. La creació d'una nova ameba es fa de forma automàtica. 
     */
    public Ameba() {
        super("AMEBA", pesAmeba); //Invocaciò al mètode constructor de la superclase Esser. S'envia el valor del nom y del pes que, en aquest cas, son iguals per a totes les amebes. 
        this.aliment = Aliment.Tot; //Les amebes s'ho mejen tot. 
        reproduccio = true; //Les amebes es reprodueixen.
        totalAmebes++; //Actualització de l'atribut de classe totalAmebes. Es suma 1 amb la creaciò de cada nova Ameba. 
    }
   
    /**
     * Mètode Getter per a l'atribut totalAmebes.
     * @return Retorna el valor de l'atribut totalAmebes.
     */
    public static int dirPoblacio() {
        return totalAmebes;
    }
    
    /**
     * Mètode que redueix en 1 el valor de totalAmebes y crida al mètode setTotalEssers pera disminuir el atribut totalEssers en 1.
     */
    @Override
    public void reduirPoblacio() {
        totalAmebes--;
        setTotalEssers(Esser.dirPoblacio()-1); //s'utilitza com argument el mètode dirPoblacio de la classe Esser menys 1.
    }
    
    /**
     * Mètode que retorna una String per a informar sobre el nom y el pes d'un esser. 
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarEstat() {
        return String.format("@%s => PES: %d%n",this.dirNom(), this.dirPes());
    }
    
    /**
     * Mètode que retorna una String per a informar el nom, el pes, el aliment y la reproduccio d'un esser concret.  
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarDetall() {
        return "@" + this.dirNom()+ " => " + "PES: " + this.dirPes() + " - " + "ALIMENTACIÓ: " + this.aliment + " - " + "REPRODUCCIÓ: SI";
    }
    
    /**
     * Mètode que controla com menjen les amebes. 
     * Es genera n boolea per a controlar un bucle. Dins del bucle es genera un aleatori y es busca l'esser que tinga el aleatori com indice. Si te el mateix nom que l'esser que
     * intenta menjar es dona un atra volta. Si no, l'esser buscat es menjar. 
     * @param essers Recibeix com a parametre la lista que conté tots els essers del sistema. 
     */
    @Override
    public void menjar(ArrayList essers) {
        //declare un boolea per a controlar el bucle. 
        boolean valido = false;
        //inici del bucle per a encontrar un alimento valid, ja que una ameba menja de tot però no es pot menjar a si mateixa.
        do {
            //genere un aleatori amb el mètode de la classe Esser per a encontrar un element aleatori dins de la lista. 
            int aleatori = Esser.generaAleatori(0, essers.size());
            //genere un esser seleccionant el index indicar per l'aleatori generat.
            Esser comido = (Esser) essers.get(aleatori);
            /*si el Esser escogit no s'anomena com la Ameba que vol menjar el bucle es repetirà. Si no s'anomenen de la mateixa manera entrarà en la condició
            Una vegada dins de la condiciò 
            Es crida al mètode canviaPes pera sumar al pes de la ameba que menja el per de l'esser menjat.
            S'utilitza el mètode reduir poblaciò de l'esser menjat.
            S'imprimeix la frase correspondent per a informar sobre el process.
            S'elimina l'esser menjat de la lista. 
            Finalmente es modifica el boolea per a eixir del bucle. */
            if (!comido.dirNom().equals(this.dirNom())) {
                this.canviaPes((int)comido.dirPes());
                comido.reduirPoblacio();
                System.out.printf("ALIMENTACIÓ**** %s: m'he menjat a %s. Ara pese %d%n", this.dirNom(), comido.dirNom(), this.dirPes());
                essers.remove(aleatori);
                valido=true;
            } 
        } while (valido == false);
    }
    
    /**
     * Mètode que controla com es reprodueixen les amebes. 
     * @param essers Recibeix com a parametre la lista d'essers. 
     * @throws Exception Genera una excepciò si la Ameba no compleix amb les condicions necessaries per a reproduirse. 
     */
    @Override
    public void reproduir(ArrayList essers) throws Exception {
        //L'esser ha de tindre un per major que el pes base de la ameba multiplicat per la constant multiplicadora de la reproducciò. //
        //Cuan compleix amb la condiciò es crea una nova ameba dins de la lista, se li resta el pes base de les amebes al pes de la ameba que es reprodueix i s'informa de la reproducciò amb la frase correspondent.
        if (this.dirPes()>(pesAmeba*pesReproduccio)) {
            essers.add(new Ameba());
            this.canviaPes(-pesAmeba);
            //Genere un Esser temporal per a poder dir el nom del nou esser creat mitjançant el métode dirNom() d'aquest nou esser.
            Esser nova =(Esser) essers.get(essers.size()-1);
            System.out.printf("REPRODUCCIÓ**** %s m'he reproduït y he creat a %s. Ara pese %d%n", this.dirNom(), nova.dirNom(), this.dirPes());
        }else {
            throw new Exception (String.format("REPRODUCCIO**** %s amb un pes de %d no em puc reproduir", this.dirNom(), this.dirPes()));
        }
    }
}
