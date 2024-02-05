/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;

/**
 * Interfície per a indicar les ferramentes necesaries per a controlar o manipular la població de cada classe que la implemente. 
 * @author pablo
 */
public interface Poblacio {
    //declaraciò de les constants de la interfície.
    public static final int pesReproduccio = 3; //constant multiplicadora per a condicionar la reproduccio dels essers.
    public static final int nombreMaxim = 20; //nombre maxim d'essers que l'usuari podrà triar en la creació del sistema.
    
    //declaraciò dels métodes que deuràn implementar les classes que implementen la interfície.
    void reduirPoblacio(); 
    void reproduir(ArrayList essers) throws Exception;
}
