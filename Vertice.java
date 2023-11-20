public class Vertice{
    private int nombre;
    private boolean visitado;

    public Vertice(int nombre){
        this.nombre = nombre;
        this.visitado = false;
    }

    public int getNombre(){
        return this.nombre;
    }

    public boolean getVisitado(){
        return this.visitado;
    }

    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }

    public String toString(){
        return "Vertice: " + this.nombre;
    }

    public boolean equals(Vertice v){
        return this.nombre == v.getNombre();
    }
}