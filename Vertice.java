/**
 * Clase que representa un vertice de una grafica
 */
public class Vertice{
    private int nombre;
    private boolean visitado;

    /**
     * Constructor de un vértice dado su nombre
     * @param nombre El nombre del vertice
     */
    public Vertice(int nombre){
        this.nombre = nombre;
        this.visitado = false;
    }

    /**
     * Metodo para obtener el nombre del vertice
     * @return int El nombre del vertice
     */
    public int getNombre(){
        return this.nombre;
    }

    /**
     * Metodo para saber si el vertice ha sido visitado
     * @return boolean True si el vertice ha sido visitado, false en otro caso.
     */
    public boolean getVisitado(){
        return this.visitado;
    }

    /**
     * Metodo para asignar un valor a visitado
     * @param visitado Boolean True si el vertice ya se visito, false en otro caso
     */
    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }

    /**
     * Metodo para mostrar un vertice
     */
    public String toString(){
        return ""+this.nombre;
    }

    /**
     * Metodo para comparar vertices pos su nombre
     * @param v El vertice a comparar
     * @return Boolean True si los vertices tienen el mismo nombre, false en otro caso
     */
    public boolean equals(Vertice v){
        return this.nombre == v.getNombre();
    }
}