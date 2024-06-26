import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Clase que representa a una grafica
 */
public class Grafica{
    private ArrayList <Arista> aristas;
    private ArrayList <Vertice> vertices;
    private int numVertices=0;
    private int numAristas=0;
    private int[][] incidencias;//Matriz de adyacencias al realizar el proceso de generar matriz
    private PriorityQueue<Arista> cola = new PriorityQueue<>();
    private ArrayList<Grafica> arboles = new ArrayList<>(); //Bosque generador al aplicar el algoritmo Bosque generador
    private boolean constructor2=false; //Indicador de si la grafica se creo con el constructor 2.

    //Determinar el bosque generador de peso minimo de una grafica disconexa usando BFS

    /**
     * Constructor, inicializa el arreglo de aritas, de vertices y la matriz de adyacencias.
     * @param numVertices
     * @param numAristas
     */
    public Grafica(int numVertices, int numAristas){
        this.aristas = new ArrayList <Arista>();
        this.vertices = new ArrayList <Vertice>();
        this.numVertices = numVertices;
        this.numAristas = numAristas;
        incidencias = new int[numVertices][numVertices]; 
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                incidencias[i][j]=0;
            }
        }
    }

    /**
     * Metodo para generar una grafica sin saber cuantas aristas y vertices tendra. 
     * Con este metodo se debe generar la matriz de adyacencias despues de haber agregado los vertices y aristas
     */
    public Grafica(){
        aristas = new ArrayList <Arista>();
        vertices = new ArrayList <Vertice>();
        constructor2 = true;
    }

    /**
     * Metodo para agregar un vertice en la grafica dado el nombre del vertice.
     * @param vert
     */
    public void agregarVertice(int vert){
        Vertice v = new Vertice(vert);
        vertices.add(v);
        if (constructor2) {
            numVertices+=1;
        }
    }

    /**
     * Metodo para agregar un vertice a la grafica dado el vertice
     * @param v
     */
    public void agregarVertice(Vertice v){
        vertices.add(v);
        if (constructor2) {
            numVertices+=1;
        }
    }


    /**
     * Metodo para agregar una arista en la grafica dados el nombre de los vertices que conecta y su pedo.
     * Los vertices que conecta la arista ya deben estar en la grafica.
     * @param vert1
     * @param vert2
     * @param peso
     */
    public void agregarArista(int vert1, int vert2, int peso){
        Vertice v1 = new Vertice(vert1);
        Vertice v2 = new Vertice(vert2);
        ciclo:
        for (int i = 0; i < numVertices; i++) {
            Vertice vi = vertices.get(i);
            if(vi.equals(v1)){ 
                for (int j = 0; j < numVertices; j++) {
                    Vertice vj = vertices.get(j);
                    if(vj.equals(v2)){
                        agregarArista(vi,vj,peso);
                        if (constructor2) {
                            numAristas+=1;
                        }
                        break ciclo;
                    }
                }
            }
        }
    }

    /**
     * Metodo para agregar una arista a la grafica dados dos vertices y el peso de la arista.
     * @param v1
     * @param v2
     * @param peso
     */
    public void agregarArista(Vertice v1, Vertice v2, int peso){
        Arista a = new Arista(v1, v2, peso);
        aristas.add(a);
        if (constructor2) {
            numAristas+=1;
        }
    }

    /**
     * Metodo para agregar una arista a la grafica dada una arista.
     * @param arista
     */
    public void agregarArista(Arista arista){
        aristas.add(arista);
        if (constructor2) {
            numAristas+=1;
        }
    }

    
    /**
     * Metodo para generar la matriz de adyacencias, donde pondremos los pesos de las aristas
     */
    public void generarMatrizAdyacencias(){
        for (int i = 0; i < numAristas; i++) {
            Arista arista = aristas.get(i);
            int v1Nombre = arista.getV1().getNombre();
            int v2Nombre = arista.getV2().getNombre();
            int peso = arista.getPeso();
            for(int j=0;j<numVertices;j++){
                if(vertices.get(j).getNombre()==v1Nombre){
                    for(int k=0;k<numVertices;k++){
                        if(vertices.get(k).getNombre()==v2Nombre){
                            incidencias[j][k]=peso;
                            incidencias[k][j]=peso;
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo para obtener el arbol generador de una grafica conexa
     * @param indiceInicial El indice del vertice del que comenzaremos el algoritmo
     */
    public void ArbolGenerador(int indiceInicial){
        int indiceNuevo = indiceInicial;
        Vertice nuevo = vertices.get(indiceNuevo);
        Grafica g = arboles.get(arboles.size()-1);

        nuevo.setVisitado(true);
        g.agregarVertice(nuevo);
        //Metemos a la cola las aristas del primer vértice
        for (int i = 0; i < numVertices; i++) {
            int num =incidencias[indiceNuevo][i]; //Vertices en la posicion i que tienen arista con el vertice nuevo
            if(num!= 0){// Si hay arista con nuevo
                Arista a = new Arista(nuevo, vertices.get(i), num);
                cola.add(a);
            }
        }

        while(!cola.isEmpty()){

            //Sacar el vertice de la cola
            Arista arista = cola.poll();
            Vertice v1 = arista.getV1();
            Vertice v2 = arista.getV2();
            if(!v1.getVisitado() || !v2.getVisitado()){
                g.agregarArista(arista);
            }else{
                continue;
            }
            
            if(!v1.getVisitado()){
                v1.setVisitado(true);
                g.agregarVertice(v1);
                nuevo = v1;
                for (int i = 0; i < numVertices; i++) {
                    if(vertices.get(i).equals(v1)){
                        indiceNuevo = i;
                    }
                }
            }else{
                v2.setVisitado(true);
                g.agregarVertice(v2);
                nuevo = v2;
                for (int i = 0; i < numVertices; i++) {
                    if(vertices.get(i).equals(v2)){
                        indiceNuevo = i;
                    }
                }
            }

            //Agregar a los vecinos de "nuevo" a la cola
            for (int i = 0; i < numVertices; i++) {
                int num =incidencias[indiceNuevo][i]; //Vertices en la posicion i que tienen arista con el vertice nuevo
                if(num!= 0){// Si hay arista con nuevo
                    Vertice vi = vertices.get(i);
                    if(!vi.getVisitado()){// y no han sido visitados
                        Arista a = new Arista(nuevo, vi, num);
                        cola.add(a);
                    }
                }
            }
        }
    }

    /**
     * Metodo para obtener el bosque generador de una grafica
     */
    public void bosqueGenerador(){
        for (int i = 0; i < numVertices; i++) {
            if(!vertices.get(i).getVisitado()){
                arboles.add(new Grafica());
                ArbolGenerador(i);
            }
        }
    }

    /**
    * Metodo para imprimir las aristas de la grafica
    */
    public void imprimirAristas(){
        System.out.print("\nAristas:\n");
        for(int i = 0; i < aristas.size(); i++){
            System.out.println(aristas.get(i));
        }
    }

    /**
     * Metodo para imprimir los vertices de la grafica
     */
    public void imprimirVertices(){
        System.out.print("\nVertices: ");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print(vertices.get(i)+" ");
        }
        System.out.println();
    }

    /**
     * Metodo para imprimir las aristas y los vertices de la grafica
     */
    public void imprimirGrafica(){
        this.imprimirVertices();
        this.imprimirAristas();
    }

    /**
     * Metodo para imprimir la matriz de adyacencias de la grafica
     */
    public void imprimirMatriz(){
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(incidencias[i][j] + " ");
            }
            System.out.print("\n");
        }

    }

    /**
     * Metodo para imprimir los arboles generadores
     */
    public void imprimirArboles(){
        for (int i = 0; i < arboles.size(); i++) {
            Grafica g = arboles.get(i);
            System.out.println("\nArbol " + (i+1));
            g.imprimirGrafica();
        }
    }
   
}