package Grafo_Peso;

public class Lista {    //ADT Lista (Ordenada por Data y sin duplicados).

    private Nodo L;
    private int n;

    public Lista() {
        L = null;
        n = 0;
    }

    public void add(String ciudad, double distancia, String camino) { //Inserta data a la Lista.
        Nodo Ant = null;
        Nodo p = L;

        while (p != null) {
            Ant = p;
            p = p.getLink();
        }

        Nodo nuevo;
        if (Ant == null) {   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(ciudad, distancia, camino);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        } else {    //x no está en la lista.  Insertarlo entre Ant y p
            nuevo = new Nodo(ciudad, distancia, camino);
            Ant.setLink(nuevo);
            nuevo.setLink(p);
            n++;
        }
    }

    public void del(String ciudad) {     //Elimina el nodo con Data=data, si existe.
        Nodo Ant = null;
        Nodo p = L;

        while (p != null) {
            Ant = p;
            p = p.getLink();

            if (p != null && p.getCiudad().equals(ciudad)) {  //data existe en la Lista 
                if (Ant == null) {
                    L = L.getLink();    //data era el primero de la Lista
                } else {
                    Ant.setLink(p.getLink());
                }

                p.setLink(null);
                n--;
            }
        }
    }

    public void bloquearOdesbloquear(String camino, boolean estado) {     //Elimina el nodo con Data=data, si existe.
        Nodo p = L;

        while (p != null) {

            if (p.getCamino().equals(camino)) {  //data existe en la Lista 

                p.setEstado(estado);
            }
            p = p.getLink();
        }
    }

    public String get(int k) {  //Devuelve el k-ésimo elemento de la lista k=0, 1, ..., length()-1 
        Nodo p = L;
        int i = 0;
        while (p != null) {
            if (i == k) {
                if(p.estado==true){
                    return p.getCiudad();
                }
            }

            p = p.getLink();
            i++;
        }

        System.err.println("Lista.get: Fuera de rango");
        return "No hay Ciudad";
    }

    public double getDistancia(String a) {
        Nodo p = L;
        while (p != null && !p.getCiudad().equals(a)) {
            p = p.getLink();
        }
        if (p != null && p.getCiudad().equals(a)) {
            return p.getDistancia();
        }
        return -1;
    }
    public double getDistancia(String a,String camino) {
        Nodo p = L;
        while (p != null && !p.getCiudad().equals(a)) {
            p = p.getLink();
        }
        if (p != null && p.getCiudad().equals(a) && p.getCamino().equals(camino)) {
            return p.getDistancia();
        }
        return -1;
    }
    public String getCamino(String a) {
        Nodo p = L;
        while (p != null && !p.getCiudad().equals(a)) {
            p = p.getLink();
        }
        if (p != null && p.getCiudad().equals(a)) {
            return p.getCamino();
        }
        return "No hay Ciudad";
    }

    public boolean existe(String ciudad) {  //Devuelve true sii el data especificado está en la lista.
        return (exist(ciudad) != null);
    }
    public int length() {
        return n;
    }

    @Override
    public String toString() {
        String S = "[";
        String coma = "";

        Nodo p = L;
        while (p != null) {
            S += coma + p.getCiudad() + "/" + doubleToStr(p.getDistancia()) + "/" + p.getCamino() + " estado: " + p.getEstado();
            coma = ", ";
            p = p.getLink();
        }

        return S + "]";
    }


    private String doubleToStr(double d) { //Devuelve d sin el pto decimal innecesario.
        String s = "" + d;
        int posPto = s.indexOf('.');
        for (int i = posPto + 1; i < s.length(); i++) {  //Ver si después del '.' todos son ceros.
            if (s.charAt(i) != '0') {
                return s;
            }
        }

        return s.substring(0, posPto);
    }

    public Nodo exist(String ciudad) {  //Devuelve el puntero al Nodo donde se encuentra data. 
        Nodo p = L;

        while (p != null && !p.getCiudad().equals(ciudad)) {
            p = p.getLink();
        }

        if (p != null && p.getCiudad().equals(ciudad)) {
            return p;
        }

        return null;    //Devolver null, si data no existe en la lista.
    }

    String getCarretera2(int i) {
                if (indexValido(i, "getVertice")) {
            return get2(i);
        }

        return "";
        }
    
    
        private boolean indexValido(int i, String Metodo) {
        boolean b = (0 <= i && i <= n);
        if (!b) {
            String s = (n == -1 ? "La Lista está vacía" : "Los índices van de 0 a " + n);
            System.err.println("Lista." + Metodo + ": " + i + " está fuera de rango. " + s + ".");
        }

        return b;
    }
        
        
        
    public String get2(int k) {  //Devuelve el k-ésimo elemento de la lista k=0, 1, ..., length()-1 
      Nodo p = L;
        int i = 0;
        while (p != null) {
            if (i == k) {
                return p.getCamino();
            }

            p = p.getLink();
            i++;
        }

        System.err.println("Lista.get: Fuera de rango");
        return "";
    }

}
