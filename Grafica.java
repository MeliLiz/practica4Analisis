import java.util.PriorityQueue;
import java.util.ArrayList;

public class Grafica{
    private Arista[] aristas;
    private Vertice[] vertices;
    private int numVertices;
    private int numAristas;
    private int contadorVertice=0;
    private int contadorAristas =0;
    private int[][] incidencias;
    private PriorityQueue<Arista> cola = new PriorityQueue<Arista>();

    //Determinar el bosque generador de peso minimo de una grafica disconexa usando BFS

    /**
     * Constructor, inicializa el arreglo de aritas, de vertices y la matriz de adyacencias
     * @param numVertices
     * @param numAristas
     */
    public Grafica(int numVertices, int numAristas){
        this.aristas = new Arista[numAristas];
        this.vertices = new Vertice[numVertices];
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
     * Metodo para agregar una arista en la grafica. Es una arista que ya se contemplaba en numAristas al crear la grafica.
     * @param vert1
     * @param vert2
     * @param peso
     */
    public void agregarArista(int vert1, int vert2, int peso){
        Vertice v1 = new Vertice(vert1);
        Vertice v2 = new Vertice(vert2);
        ciclo:
        for (int i = 0; i < numVertices; i++) {
            if(vertices[i].equals(v1)){
                for (int j = 0; j < numVertices; j++) {
                    if(vertices[j].equals(v2)){
                        Arista a = new Arista(vertices[i], vertices[j], peso);
                        this.aristas[contadorAristas] = a;
                        contadorAristas+=1;
                        break ciclo;
                    }
                }
            }
        }
    }

    /**
     * Metodo para agregar un vertice en la grafica. Es un vertice que ya se contemplaba en numVertices al crear la grafica.
     * @param vert
     */
    public void agregarVertice(int vert){
        Vertice v = new Vertice(vert);
        this.vertices[contadorVertice] = v;
        contadorVertice +=1;
    }

    /**
     * Metodo para generar la matriz de adyacencias, donde pondremos los pesos de las aristas
     */
    public void generarMatrizAdyacencias(){
        for (int i = 0; i < numAristas; i++) {
            Arista arista = aristas[i];
            int v1Nombre = arista.getV1().getNombre();
            int v2Nombre = arista.getV2().getNombre();
            int peso = arista.getPeso();
            for(int j=0;j<numVertices;j++){
                if(vertices[j].getNombre()==v1Nombre){
                    for(int k=0;k<numVertices;k++){
                        if(vertices[k].getNombre()==v2Nombre){
                            incidencias[j][k]=peso;
                            incidencias[k][j]=peso;
                        }
                    }
                }
            }
        }
    }

   /**
    * Metodo para imprimir las aristas de la grafica
    */
    public void imprimirAristas(){
        for(int i = 0; i < this.numAristas; i++){
            System.out.println(this.aristas[i]);
        }
    }

    /**
     * Metodo para imprimir los vertices de la grafica
     */
    public void imprimirVertices(){
        for(int i = 0; i < this.numVertices; i++){
            System.out.println(this.vertices[i]);
        }
    }

    /**
     * Metodo para imprimir las aristas y los vertices de la grafica
     */
    public void imprimirGrafica(){
        this.imprimirAristas();
        this.imprimirVertices();
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

    public void ArbolGenerador(int indiceInicial){
        int indiceNuevo = indiceInicial;
        Vertice nuevo = vertices[indiceNuevo];

        nuevo.setVisitado(true);
        for (int i = 0; i < numVertices; i++) {
            int num =incidencias[indiceNuevo][i]; //Vertices en la posicion i que tienen arista con el vertice nuevo
            if(num!= 0){// Si hay arista con nuevo
                Arista a = new Arista(nuevo, vertices[i], num);
                cola.add(a);
            }
        }
        while(!cola.isEmpty()){

            //Sacar el vertice de la cola
            Arista arista = cola.poll();
            Vertice v1 = arista.getV1();
            Vertice v2 = arista.getV2();

            if(!v1.getVisitado()){
                v1.setVisitado(true);
                nuevo = v1;
                for (int i = 0; i < numVertices; i++) {
                    if(vertices[i].equals(v1)){
                        indiceNuevo = i;
                    }
                }
            }else{
                v2.setVisitado(true);
                nuevo = v2;
                for (int i = 0; i < numVertices; i++) {
                    if(vertices[i].equals(v2)){
                        indiceNuevo = i;
                    }
                }
            }

            //Agregar a los vecinos de "nuevo" a la cola
            for (int i = 0; i < numVertices; i++) {
                int num =incidencias[indiceNuevo][i]; //Vertices en la posicion i que tienen arista con el vertice nuevo
                if(num!= 0){// Si hay arista con nuevo
                    if(!vertices[i].getVisitado()){// y no han sido visitados
                        Arista a = new Arista(nuevo, vertices[i], num);
                        cola.add(a);
                    }
                }
            }
        }
    }

    public void bosqueGenerador(){
        for (int i = 0; i < numVertices; i++) {
            if(!vertices[i].getVisitado()){
                ArbolGenerador(i);
            }
        }
    }



    /*public void bfs(){
        for(int i = 0; i < this.numVertices; i++){
            this.vertices[i].setVisitado(false);
        }
        for(int i = 0; i < this.numVertices; i++){
            if(!this.vertices[i].getVisitado()){
                this.bfs(this.vertices[i]);
            }
        }
    }
    public void bfs(Vertice v){
        Cola<Vertice> cola = new Cola<Vertice>();
        cola.encolar(v);
        v.setVisitado(true);
        while(!cola.esVacia()){
            Vertice aux = cola.desencolar();
            System.out.println(aux);
            for(int i = 0; i < this.numAristas; i++){
                if(this.aristas[i].getV1().equals(aux) && !this.aristas[i].getV2().getVisitado()){
                    cola.encolar(this.aristas[i].getV2());
                    this.aristas[i].getV2().setVisitado(true);
                }
                if(this.aristas[i].getV2().equals(aux) && !this.aristas[i].getV1().getVisitado()){
                    cola.encolar(this.aristas[i].getV1());
                    this.aristas[i].getV1().setVisitado(true);
                }
            }
        }
    }*/

   
}