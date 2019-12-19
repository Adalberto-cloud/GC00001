/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo_Peso;

/**
 *
 * @author adalberto
 */
public class Main {

    public static void main(String[] args) {
        Grafo G = new Grafo();
        G.addVertice("Santa Cruz");
        G.addVertice("Cochabamba");
        G.addVertice("La Paz");
        G.addVertice("Potosi");
        
        
        
 G.addArista("Santa Cruz", 100, "SZ-CBB", "Cochabamba");   
 G.addArista("Santa Cruz", 200, "SZ-CBB-VIEJA", "Cochabamba");
          
        G.addArista("Santa Cruz", 300, "SZ-LP", "La Paz");
//       G.addArista("La Paz", 200, "LP-CBB", "Cochabamba");
//       G.addArista("La Paz", 600, "LP-CBB2", "Cochabamba");
       G.addArista("Cochabamba", 50, "CBB-LP", "La Paz");
//       G.addArista("Santa Cruz", 900, "SZ-PSI", "Potosi");
//       G.addArista("La Paz", 500, "LP-PSI", "Potosi");
//       

        G.printListas();
//        G.bloquearCamino("Santa Cruz", "SZ-CBB");
//System.out.println(G.caminoMasCorto("Santa Cruz","La Paz"));
        System.out.println(G.menordistancia("Santa Cruz","La Paz"));
    }
}
