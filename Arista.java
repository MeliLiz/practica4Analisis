/**
 * Clase que representa la arista de una gráfica
 */
public class Arista implements Comparable<Arista>{
    
    private Vertice v1;// Vertices que conecta la arista
    private Vertice v2;
    private int peso;//Peso de la arista
    
    /**
     * Constructor de una arista dados dos vertices y el peso de la arista
     * @param v1 //Primer vertice que conecta la arista
     * @param v2 // segundo vertice que conecta la arista
     * @param peso //EL peso de la arista
     */
    public Arista(Vertice v1, Vertice v2, int peso){
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }
    
    /**
     * Metodo para obtener el primer vertice que conecta la arista
     * @return Vertice
     */
    public Vertice getV1(){
        return this.v1;
    }
    
    /**
     * Metodo para obtener el segundo vertice que conecta la arista
     * @return Vertice
     */
    public Vertice getV2(){
        return this.v2;
    }
    
    /**
     * Metodo para obtener el peso de la arista
     * @return int El peso de la arista
     */
    public int getPeso(){
        return this.peso;
    }
    
    /**
     * Metodo para representar en cadena a la arista
     */
    public String toString(){
        return "" + this.v1 + ", " + this.v2 + " Peso: " + this.peso;
    }

    /**
     * Metodo para comparar aristas por su peso
     */
    @Override
    public int compareTo(Arista o) {
        return Integer.compare(this.peso, o.peso);
    }
}
