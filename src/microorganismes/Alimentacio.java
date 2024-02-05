/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;

/**
 * Interfície per a indicar les ferramentes necesaries per a la alimentació dels essers del sistema.
 * @author pablo
 */
public interface Alimentacio {
    //Declaració de les constants de la interfície. Public, static y final per defecte. Cadascuna indica el pes inicial d'un tipus d'esser i, la última, el pes dun tipus d'aliment. 
    public static final int pesAmeba = 20;
    public static final int pesBacteri = 10;
    public static final int pesAlga = 3;
    public static final int pesNutrients = 5;
    
    //métode per a que els essers pugan menjar. S'implementará dins de cada classe que implemente la interfície.
    void menjar(ArrayList listaEssers) throws Exception;
}
