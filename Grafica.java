import java.util.PriorityQueue;

public class Grafica{
    private Arista[] aristas;
    private Vertice[] vertices;
    private int numAristas;
    private int numVertices;
    private int contadorVertice=0;
    private int contadorAristas =0;

    //Determinar el bosque generador de peso minimo de una grafica disconexa usando BFS

    public Grafica(int numVertices, int numAristas){
        this.numVertices = numVertices;
        this.numAristas = numAristas;
        this.aristas = new Arista[numAristas];
        this.vertices = new Vertice[numVertices];
    }
    public void agregarArista(int vert1, int vert2, int peso){
        Vertice v1 = new Vertice(vert1);
        Vertice v2 = new Vertice(vert2);
        Arista a = new Arista(v1, v2, peso);
        this.aristas[contadorAristas] = a;
        contadorAristas+=1;
    }
    public void agregarVertice(int vert){
        Vertice v = new Vertice(vert);
        this.vertices[contadorVertice] = v;
        contadorVertice +=1;
    }
    public void imprimirAristas(){
        for(int i = 0; i < this.aristas.length; i++){
            System.out.println(this.aristas[i]);
        }
    }
    public void imprimirVertices(){
        for(int i = 0; i < this.vertices.length; i++){
            System.out.println(this.vertices[i]);
        }
    }
    public void imprimirGrafica(){
        this.imprimirAristas();
        this.imprimirVertices();
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