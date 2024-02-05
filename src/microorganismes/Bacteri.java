/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase per a la creació y control de les Amebes dins del sistema. Subclase d'Esser. 
 * @author pablo
 */
public final class Bacteri extends Esser {
    private static int totalBacteris; //Atribut de classe. Nombre total de bacteris dins del sistema.
    private final Aliment aliment; //Atribut d'instancia. Aliment del que s'alimenta un baceri concret.
    private final boolean reproduccio; //Atribut de clase per a indicar si un bacteri es capaz de reproduirse.
    
    /**
     * Constructor per a la creacio d'un nou Bacteri. No recibeix res con a argument. La creació d'un nou Bacteri es fa de forma automàtica.
     */
    public Bacteri() {
        super("BACTERI", pesBacteri); //Invocaciò al mètode constructor de la superclase Esser. S'envia el valor del nom y del pes que, en aquest cas, son iguals per a tots els bacteris.
        this.aliment = Aliment.Alga; //Els bacteris sols menjen algues.
        this.reproduccio = true; //Els bacteris es reprodueixen.
        totalBacteris++; //Es suma 1 al total de bacteris amb la creació de cada nou Bacteri.
    }
    
    /**
     * Constructor necessari per a poder crear un bacteri amb un pes diferent del pes base de Bacteri. 
     * Es necessari perque, quan es reprodueix un Bacteri genera un nou Bacteri amb la meitat del pes del que es reprodueix y no amb el pes base de Bacteri. 
     * @param pes 
     */
    public Bacteri(int pes) {
        super("BACTERI", pes); //Invocaciò al mètode constructor de la superclase Esser. S'envia el mateix nom que en l'altre constructor però el pes que es manda des de la funció reproduir.
        this.aliment = Aliment.Alga; //Els bacteris sols menjen algues.
        this.reproduccio = true; //Els bacteris es reprodueixen.
        totalBacteris++; //Es suma 1 al total de bacteris amb la creació de cada nou Bacteri.
    }
    
    /**
     * Mètode Getter per a l'atribut totalBacteris.
     * @return Retorna el valor de l'atribut totalBacteris.
     */
    public static int dirPoblacio() {
        return totalBacteris;
    }
    
    /**
     * Mètode que redueix en 1 el valor de totalBacteris y crida al mètode setTotalEssers per a disminuir el atribut totalEssers en 1.
     */
    @Override
    public void reduirPoblacio() {
        totalBacteris--;
        setTotalEssers(Esser.dirPoblacio()-1);
    }
    
    /**
     * Mètode que retorna una String per a informar sobre el nom y el pes d'un esser. Es crida als mètodes dirNom() y dirPes() perque ambdos son atributs privats de la classe Esser. 
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarEstat() {
        return String.format("/%s => PES: %d%n",this.dirNom(), this.dirPes()); 
    }
    
    /**
     * Mètode que retorna una String per a informar el nom, el pes, el aliment y la reproduccio d'un esser concret. 
     * @return Retorna la String amb la frase informativa.
     */
    @Override
    public String mostrarDetall() {
        return "/" + this.dirNom()+ " => " + "PES: " + this.dirPes() + " - " + "ALIMENTACIÓ: " + this.aliment + " - " + "REPRODUCCIÓ: SI";
    }
    
    /**
     * Mètode que controla com menjen els bacteris.
     * Si no queden algues genera una excepcio personalitzada informant.Si queden algues genera un aleatori entre 0 y el nombre d'algues menys 1. Alshores genera un iterator per a la 
     * llista d'essers y recorreix la llista generant, en cada iteraciò, un esser anomenat rubi, comproba si rubi es un alga y, si ho es, comprova si el contador es igual al aleatori 
     * calculat avans. Si ho es, el bacteri es menja l'alga. Si no, suma 1 al contador y dona un altra volta al bucle. Quan el contador siga igual a l'aleatori el bacteri menja l'alga 
     * que correspon amb el aleatori.
     * @param essers Recibeix com a parametre la lista que conté tots els essers del sistema. 
     * @throws Exception Genera una excepciò si no queden algues porque els Bacteris sols menjen algues. 
     */
    @Override
    public void menjar(ArrayList essers) throws Exception {
        //Cree un contador.
        int cont = 0;
        if (Alga.dirPoblacio() > 0) {
            //Si queden algues genera un aleatori entre 0 y el nombre d'algues menys 1.
            int aleatori = Esser.generaAleatori(0, Alga.dirPoblacio());
            Iterator<Esser> iter = essers.iterator();
            while (iter.hasNext()) {
                //genera un iterator per a la llista d'essers y recorreix la llista generant, en cada iteraciò, un esser anomenat rubi.
                Esser rubi = iter.next();
                if (rubi.getClass().getSimpleName().equals("Alga")){
                    /*comproba si rubi es un alga y, si ho es, comprova si el contador es igual al aleatori calculat avans. Si ho es, el bacteri es menja l'alga. 
                    Si no, suma 1 al contador y dona un altra volta al bucle. Quan el contador siga igual a l'aleatori el bacteri menja l'alga que correspon amb el aleatori.
                    Se incrementa el peso del Bacteri que menja amb el pes de l'alga menjada.
                    S'informa amb la frase correspondent.
                    Es redueix la poblaciò d'algues.
                    Finalmente s'elimina l'alga menjada de la llista.*/
                    if (cont == aleatori) {
                        this.canviaPes(rubi.dirPes());
                        System.out.printf("ALIMNENTACIÓ**** %s: m'he menjat a %s. Ara pese %d%n", this.dirNom(), rubi.dirNom(), this.dirPes());
                        rubi.reduirPoblacio();
                        iter.remove();
                    }
                    cont++;  
                }
            }
        //Si no queden algues genera una excepcio personalitzada informant.
        }else {
            throw new Exception ("No queden algues.");
        }
    }
    
    /**
     * Mètode que controla com es reprodueixen els bacteris. 
     * Si compleix la condiciò marcada es crea un nou bacteri amb la meitat de l'esser que es reprodueix, se li rest el mateix pes al Bacteri que es reprodueix y s'informa 
     * amb la correspondent frase. 
     * Si no compleix la confició es genera una excepcio informant de la imposibilitat de reproducciò.
     * @param essers Recibeix com a parametre la lista d'essers. 
     * @throws Exception Genera una excepciò si el Bacteri no compleix amb les condicions necessaries per a reproduirse. 
     */
    @Override
    public void reproduir(ArrayList essers) throws Exception{
        if (this.dirPes() >= pesReproduccio*(2*pesBacteri)) {
            //Si compleix amb la condicio es crea un nou Bacteri dins de la llista amb la meitat del pes del Bacteri que es reprodueix. 
            essers.add(new Bacteri((int)this.dirPes()/2));
            //Es dinminueix a la meitat el pes del Bacteri que es reprodueix.
            this.canviaPes((int)-this.dirPes()/2);
            //Li dina un identificador el nou Bacteri
            Esser nou = (Esser)essers.get(essers.size()-1);
            //S'informa amb la corespondent frase. 
            System.out.printf("REPRODUCCIÓ**** %s m'he reproduit y he creat a %s. Ara pese %d%n", this.dirNom(), nou.dirNom(), this.dirPes());
        //Si no compleix amb la condicio es genera una exceptio per a informar de que l'esser no es pot reproduir. 
        }else {
            throw new Exception (String.format("REPRODUCCIÓ**** %s amb un pes de %d no em puc reproduir", this.dirNom(), this.dirPes()));
        }
    }
}
