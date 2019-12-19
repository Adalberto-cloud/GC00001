package Grafo_Peso;

public class Nodo {     //Nodo que usa la class Lista
    public String ciudad;
    public double distancia;
    public String camino;
    public Nodo Link;

    public Nodo(){
        this("", 0, "" );
    }
    
    public Nodo(String ciudad, double distancia, String camino) {
        this.ciudad = ciudad;
        this.distancia = distancia;
        this.camino=camino;
        this.Link = null;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia  = distancia;
    }
    
     public String getCamino() {
        return camino;
    }
     
     public void setCamino(String camino) {
        this.camino = camino;
    }
   
    public Nodo getLink() {
        return Link;
    }

    public void setLink(Nodo Link) {
        this.Link = Link;
    }

    double getPeso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
