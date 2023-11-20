import java.util.PriorityQueue;

public class Grafica{
    private Arista[] aristas;
    private Vertice[] vertices;
    private int contadorVertice=0;
    private int contadorAristas =0;
    int[][] incidencias;
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
        for (int i = 0; i < vertices.length; i++) {
            if(vertices[i].equals(v1)){
                for (int j = 0; j < vertices.length; j++) {
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
        for (int i = 0; i < aristas.length; i++) {
            Arista arista = aristas[i];
            int v1Nombre = arista.getV1().getNombre();
            int v2Nombre = arista.getV2().getNombre();
            int peso = arista.getPeso();
            for(int j=0;j<vertices.length;j++){
                if(vertices[j].getNombre()==v1Nombre){
                    for(int k=0;k<vertices.length;k++){
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
        for(int i = 0; i < this.aristas.length; i++){
            System.out.println(this.aristas[i]);
        }
    }

    /**
     * Metodo para imprimir los vertices de la grafica
     */
    public void imprimirVertices(){
        for(int i = 0; i < this.vertices.length; i++){
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
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                System.out.print(incidencias[i][j] + " ");
            }
            System.out.print("\n");
        }

    }

    /*public void bfs(){
        for(int i = 0; i < this.vertices.length; i++){
            this.vertices[i].setVisitado(false);
        }
        for(int i = 0; i < this.vertices.length; i++){
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
            for(int i = 0; i < this.aristas.length; i++){
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