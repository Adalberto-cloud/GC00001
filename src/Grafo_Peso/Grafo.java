package Grafo_Peso;

import java.util.LinkedList;

public class Grafo {    //Grafo dirigido y con peso 
    private static final int MAXVERTEX = 49;    //Máximo índice de V[]
  
    private Lista V[];
    private String Ciudades[];
    private int n;       //"Dimensión" de V[]
   double peso[] = new double[MAXVERTEX+1];
   double peson[] = new double[MAXVERTEX+1];
   public Grafo(){
       V = new Lista[MAXVERTEX+1];      //V[0..MAXVERTEX]
       Ciudades = new String[MAXVERTEX+1];
       n = -1;
       peso = new double[MAXVERTEX+1];
       marca = new boolean[MAXVERTEX+1];    //Iniciar la ED para el marcado de los vértices.
    }
    
    public void addVertice(String ciudad){
        if (n == MAXVERTEX){
            System.err.println("Grafo.addVertice: Demasiados vértices (solo se permiten "+(MAXVERTEX+1)+")");
            return;
        }    
        
        n++;
        V[n] = new Lista();     //Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia vacía)
        Ciudades[n]=ciudad;
    }
     private boolean isMarcado(int u){   //Devuelve true sii el vertice u está marcado.
        return marca[u]; 
    }
    public int cantVertices(){
        return n+1;
    }
    
    public boolean isVerticeValido(int v){
        return (0<=v && v<=n);
    }
    
    public void addArista(String a, double distancia, String camino, String b){  //Crea la arista u-->v
        if (distancia <= 0 || camino == ""){
            System.err.println("Grafo.addArista:  El peso debe ser mayor que cero");
            return;
        }
        
        /*String metodo="addArista";
        if (!isVerticeValido(a, metodo) || !isVerticeValido(b, metodo))
            return;     //No existe el vertice u o el vertice v.
        */
        int pos=-1;
        for(int i=0;i<=n;i++)
        {
            if (Ciudades[i].equals(a))
            {
               pos=i;
               break;
            }
        }
        if (pos!=-1)
        {
            V[pos].add(b, distancia, camino);      //Adicionar (data, peso) a la lista V[u]        
        }
        
        
    } 
     public void delArista(String a, String  b){  //Elimina la arista u-->v
        String metodo="delArista";
        int u = posicion(a);
        int v = posicion(b);
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return;     //No existe el vertice u o el vertice v.
        
        V[u].del(b);
    } 
    
/*    public void delArista(int u, int v){  //Elimina la arista u-->v
        String metodo="delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return;     //No existe el vertice u o el vertice v.
        
        V[u].del(v);
    } 
*/
    
/*    public double costo(int u, int v){  //Devuelve el peso de la arista u-->v.  Si esa arista no existe, devuelve 0
        if (!isVerticeValido(u) || !isVerticeValido(v))
            return 0;
        
        return V[u].getPeso(v);
    }
*/    
     public double distancia(String a,String b){
        int x = posicion(a);
        int y = posicion(b);
        if (!isVerticeValido(x) || !isVerticeValido(y))
            return 0;
        return V[x].getDistancia(b);
    }
     
     public String camino(String a,String b){
        int x = posicion(a);
        int y = posicion(b);
        if (!isVerticeValido(x) || !isVerticeValido(y))
            return "No hay Ciudad";
        return V[x].getCamino(b);
    }
     
    public void dfs(int v){  //Recorrido Depth-First Search (en profundidad).
        if (!isVerticeValido(v, "dfs"))
            return;  //Validación. v no existe en el Grafo.
        
        desmarcarTodos();
        //System.out.print("DFS:");
        dfs1(v);
        //System.out.println();
    }
    
    private void dfs1(int v){  //mask-function de void dfs(int)
        //System.out.print(" "+v);
        marcar(v);
        
        for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
            String s = V[v].get(i);
            int w = posicion(s);
                if (!isMarcado(w))
                    dfs1(w);
            
        }
    }
    
    private int posicion(String ciudad)
    {
        for(int i=0;i<=n;i++)
        {
            if (Ciudades[i].equals(ciudad))
                return i;
        }
        return -1;
    }
    
    
    public void bfs(int u){  //Recorrido Breadth-First Search (en anchura).
        if (!isVerticeValido(u, "bfs"))
            return;  //Validación. u no existe en el Grafo. 
       
        desmarcarTodos();
        LinkedList <Integer> cola = new LinkedList<>();  //"cola" = (vacía) = (empty)
        cola.add(u);     //Insertar u a la "cola" (u se inserta al final de la lista).
        marcar(u);
        
        System.out.print("BFS:");
        do{
            int v = cola.pop();         //Obtener el 1er elemento de la "cola".
            System.out.print(" "+v);
            
            for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
                String s = V[v].get(i);
                int w = posicion(s);
            
                if (!isMarcado(w)){
                    cola.add(w);
                    marcar(w);
                }    
            }
        }while (!cola.isEmpty());  
        
        System.out.println();
    }
    
    
    public void printListas(){  //Muestra las listas del Grafo.  Util para el programador de esta class
        if (cantVertices()==0)
            System.out.println("(Grafo Vacío)");
        else{
            for (int i = 0; i <= n; i++) {
                System.out.println("V["+Ciudades[i]+"]-->"+V[i]);  
            }  
        }
    }
    
/*    @Override
    public String toString(){   //Este método debe ser cambiado si el grafo es dirigido.
        if (cantVertices()==0)
            return "(Grafo Vacío)";
        
        final String SEPARADOR = ", ";
        
           //Mostrar las aristas u-->v del grafo como (u, peso, v)
        desmarcarTodos();
        String S="[";
        String coma="";
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= n; k++) {   
                double peso = costo(i, k);
                if (peso > 0){
                    String arista = "(" + i + "," + peso + "," + k + ")";
                    S += coma + arista;
                    coma = SEPARADOR;
                    marcar(i);
                }            
            }
            
            if (!isMarcado(i)){     //El vertice i no tiene aristas
                S += coma + i;
                coma = SEPARADOR;
            }           
        }
        
        return S+"]";
    } 
*/
    
    private boolean isVerticeValido(int v, String metodo){
        boolean b = isVerticeValido(v);
        if (!b)
            System.err.println("Grafo."+metodo+": " + v + " no es un vértice del Grafo " + getIndicacion());

        return b;
    }
    
    private String getIndicacion(){  //Corrutina de boolean isVerticeValido(int, String)
        switch (cantVertices()){
            case 0  :   return "(el grafo no tiene vértices). ";
            case 1  :   return "(el 0 es el único vértice). ";
            case 2  :   return "(0 y 1 son los únicos vértices). ";
            default :   return "(los vértices van de 0 a " + (cantVertices()-1) + "). "; 
        }
    }

//********* Para el marcado de los vértices
    private boolean marca[];
    
    private void desmarcarTodos(){
        for (int i = 0; i <= n; i++) {
            marca[i] = false;  
        }
    }
    
    private void marcar(int u){
        if (isVerticeValido(u))
           marca[u] = true; 
    }
    
    private void desmarcar(int u){
        if (isVerticeValido(u))
           marca[u] = false; 
    }
     public boolean existeCamino(String a,String b){
        int x = posicion(a);
        int y = posicion(b);
        dfs(x);
        return isMarcado(y);
    }
     
     public boolean marcados(int u){
        for(int i=0;i<V[u].length();i++){
            String s = V[u].get(i);
            int w = posicion(s);
            if(!isMarcado(w))
                return false;
        }
        return true;
    }
     
     /*public boolean hayCamino(int u, int v){
        dfs(u);
        if (isMarcado(v)){
            return true;
        } else {
            return false;
        }
    }*/
   //---------------------ojocon!isMarcado(w)--------------------------
    public int menDist(int u){
        double men=Integer.MAX_VALUE;
        int ver=Integer.MAX_VALUE;
        for(int i=0;i<V[u].length();i++){
            String s = V[u].get(i);
            int w=posicion(s);
            Nodo aux=V[u].exist(s);            
            if (aux!=null && aux.getDistancia()<men && !isMarcado(w))
            {
                men=aux.getDistancia();
                ver=w;
            }
        }
        return ver;

    }
    private double min(double a,double b){
        if(a<b)
            return a;
        else
            return b;
    }
     
     public int VerticeNoMarcado(){
        double men = Double.POSITIVE_INFINITY;
        int ver = -1;
        for(int i=0;i<=n;i++){
            if(!isMarcado(i) && peson[i]<men){
                men = peson[i];
                ver = i;
            }
        }
        return ver;
    }
    public double menordistancia(String a,String z){
        if (!existeCamino(a,z)){
            return 0;
        } else {
        desmarcarTodos();
        double peso[] = new double[n+1];
        for(int i=0;i<=n;i++){
            peso[i] = Double.POSITIVE_INFINITY; //Integer.MAX_VALUE;
        }
        int x = posicion(a);
        int y = posicion(z);
        peso[x] = 0;
        while(!isMarcado(y)){
            int u = VerticeNoMarcado();
            marcar(u);
            String s1 = Ciudades[u];
            for(int i=0;i<V[u].length();i++){
                String s = V[u].get(i);
                int w = posicion(s);
                if(!isMarcado(w)){
                    peso[w] = min(peso[w],peso[u]+distancia(s1, s));
                }
            }
        }
        
        return peso[y];
        }  
    }
     
}