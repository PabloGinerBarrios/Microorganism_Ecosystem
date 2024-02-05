/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microorganismes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Classe principal del programa.
 * @author pablo
 */
public class Main {
    
    /**
     * Mètode que inicia tot el programa.
     * @param args 
     */
    public static void main(String[] args) {
        //Creació de la llista
        ArrayList<Esser> essers = new ArrayList<Esser>();
        
        //Petició de les quantitata inicials d'essers de cada tipus a l'usuari.
        System.out.println("Introduix el nombre d'Amebes con el que vols iniciar el sistema(entre 1 y " + Poblacio.nombreMaxim + "):");
        int nombreAmebes = llegirNumero(1, Poblacio.nombreMaxim);
        
        System.out.println("Introduix el mínim de Bacteris que vols en el sistema(entre 1 y " + Poblacio.nombreMaxim + "):");
        int nombreBacteris = llegirNumero(1, Poblacio.nombreMaxim);
        
        System.out.println("Introdueix el mínim d'Algues que vols en el sistema(entre 1 y " + Poblacio.nombreMaxim + "):");
        int nombreAlgues = llegirNumero(1, Poblacio.nombreMaxim);
        
        //Plenem la llista amb les quantitats introduides per l'usuari e iniciem el menú.
        crearEssers(nombreAmebes, nombreBacteris, nombreAlgues, essers);
        processaMenu(essers); 
    }
    
    /**
     * Mètode verificador per a controlar que el nombres quer l'usuari introdueix per teclat enten dins del que pide el programa.
     * @param min Nombre mínim válid que l'usuari pot introduir per teclat.
     * @param max Nombre máxim válid que l'usuari pot introduir per teclat.
     * @return Retorna el nombre validat.
     */
    public static int llegirNumero(int min, int max) {
        Scanner entrada = new Scanner(System.in);
        int nombre = 0;
        boolean valid = false;
        do {
            try {
                nombre = entrada.nextInt();
                if (nombre < min || nombre > max) {
                    throw new Exception ("ERROR: Ha d'introduir un número entre " + min + " i " + max + ".");
                } else {
                    valid = true;
                }    
            } catch (InputMismatchException m) {
                System.err.println("ERROR: No s'ha intrduït un valor numéric.");
                System.out.println("Vuelve a intentarlo.");
                entrada.nextLine();
            } catch (Exception e) {
                System.err.println(e);
                System.out.println("Vuelve a intentarlo.");
                entrada.nextLine();
            }
        } while (valid == false);    
        return nombre;
    }
    
    /**
     * Mètode que crea els essers per a plenat la llista.
     * @param nombreAmebes Nombre incial d'amebes introduit per l'usuari.
     * @param nombreBacteris Nombre incial de bacteris introduit per l'usuari.
     * @param nombreAlgues Nombre incial d'algues introduit per l'usuari.
     * @param essers Llista d'essers que plena este mètode. 
     */
    public static void crearEssers(int nombreAmebes, int nombreBacteris, int nombreAlgues, ArrayList essers) {
        int num;
        /*El bucle donará voltes mentres que les variables sigan majores que 0. Es crea un nombre aleatori que decideix qué tipus d'esser es creará y, aún queden essers d'aquest tipus
        per crear, es creará.*/
        while (nombreAmebes>0 || nombreBacteris>0 || nombreAlgues>0) {
            num = Esser.generaAleatori(0, 3);
            switch (num) {
                case 0:
                    if (nombreAmebes>0) {
                        essers.add(new Ameba());
                        nombreAmebes--;
                    }
                    break;
                case 1:
                    if (nombreBacteris>0) {
                        essers.add(new Bacteri());
                        nombreBacteris--;
                    }
                    break;
                case 2:
                    if (nombreAlgues>0) {
                        essers.add(new Alga());
                        nombreAlgues--;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    /**
     * Mètode que mostra el menú y permiteix portar a cap la opciò escollida per l'usuari.
     * @param essers Llista dels esser que n'hi ha en el sistema.
     */
    public static void processaMenu(ArrayList essers) {
        int opcio = -1;
        //Bucle que permiteix tornar al menú sempre que l'usuari no polse la opció 0.
        do {
            try {
                System.out.print("«OPCIONS==> 1.-Una Interacció, 2.-Deu Interaccions, 3.-Llistat, 4-Detall 0.-Eixir: «");
                //Validació per al nombre introduir per l'usuari.
                opcio = llegirNumero(0, 4);
                switch (opcio) {
                    case 1:
                        produeixInteraccio(essers);
                        break;
                    case 2:
                        //Bucle de 10 interaccions per a la opció 2 del menú.
                        for (int i = 0; i < 10; i++) {
                            produeixInteraccio(essers);
                            if (essers.size() == 1) {
                                break;
                            }
                        }
                        break;
                    case 3:
                        mostraLlistaEssers(essers);
                        break;
                    case 4:
                        mostraEsser(essers);
                        break;
                    case 0:
                        mostraLlistaEssers(essers);
                        System.out.println("Adeu.");
                        break;
                    default:
                        throw new AssertionError();
                }
                if (essers.size() == 1) {
                    Esser ganador = (Esser)essers.get(0);
                    System.out.printf("Solo queda un esser.%nEl ganador es %s%n", ganador.dirNom());
                    opcio = 0;
                }
            } catch (Exception e) {
                System.err.println(e);
            }     
        } while (opcio != 0);     
    }
    
    /**
     * Mètode que recorreix la llista dels essers mostrant l'estat de cadascun d'ells. Quan no hi ha mes essers que mostrar s'imprimeix una frase amb la quantitat de cada tipus d'esser. 
     * @param essers Llista amb tots els essers que n'hi ha en el sistema. 
     */
    public static void mostraLlistaEssers(ArrayList essers) {
        Iterator iter = essers.iterator();
        while (iter.hasNext()) {
            Esser sergio =(Esser) iter.next();
            //Es crida al mètode mostrarEstat() de cada esser al recorrer la llista.
            System.out.printf(sergio.mostrarEstat());
        }
        //Finalmente s'imprimeix una frase informativa amb les quantitats de cada tipus d'esser dins del sistema. 
        System.out.printf("POBLACIÓ: TOTAL ESSERS=>%d, AMEBES=>%d, BACTERIS=>%d, ALGUES=>%d%n", Esser.dirPoblacio(), Ameba.dirPoblacio(), Bacteri.dirPoblacio(), Alga.dirPoblacio());
    }
    
    /**
     * Mètode que mostra informació detallada d'un esser concret escollit per l'usuari.
     * @param essers Llista amb tots el esser que n'hi ha en el sistema.
     */
    public static void mostraEsser(ArrayList essers) {
        Scanner entrada = new Scanner(System.in);
        try {
            //Se pide el nom de l'esser al usuari.
            System.out.println("Introdueix el nom de l'esser que busca: ");
            String nom = entrada.nextLine().toUpperCase();
            //Iniciem un contador per a comprobar si l'esser existeix.
            int cont = 0;
            //Recorrem la llista buscant l'esser pel seu nom.
            Iterator iter = essers.iterator();
            while (iter.hasNext()) {
                Esser alberto = (Esser)iter.next();
                if (alberto.dirNom().equals(nom)) {
                    System.out.println(alberto.mostrarDetall());
                    cont++;
                }
            }
            //Si quan s'ix del bucle el contador es 0 s'imprimeix una frase informant de que l'esser especificat per l'usuari no existeix.
            if (cont == 0) {
                throw new Exception(String.format("ERROR: el microorganisme %s no apareix en la llista.»", nom));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    /**
     * Mètode que es escull un esser de manera aleatoria y el fa intentar menjar o reproduir també de manera aleatoria. 
     * @param essers ArrayList con los objetos de tipo Esser creados.
     */
    public static void produeixInteraccio(ArrayList essers) {
        try {
            //genero un número aleatorio entre 0 y el tamaño de la lista essers
            int num1 = Esser.generaAleatori(0, essers.size());
            //creo un objeto de tipo Esser llamado alfredo para llamarlo después a comer o reproducirse
            Esser alfredo = (Esser)essers.get(num1); 
            //modifico el valor de num1 generando otro valor aleatorio, esta vez entre 0 y 1 para decidir si alfredo come o intenta reproducirse
            num1 = Esser.generaAleatori(0, 2);
            if (num1 == 0) {
                alfredo.menjar(essers);
            }else {
                alfredo.reproduir(essers);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
            
    }
}
