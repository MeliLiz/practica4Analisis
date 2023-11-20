public class Arista {
    
    private Vertice v1;
    private Vertice v2;
    private int peso;
    
    public Arista(Vertice v1, Vertice v2, int peso){
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }
    
    public Vertice getV1(){
        return this.v1;
    }
    
    public Vertice getV2(){
        return this.v2;
    }
    
    public int getPeso(){
        return this.peso;
    }
    
    public String toString(){
        return "v1: " + this.v1 + " v2: " + this.v2 + " Peso: " + this.peso;
    }
}
