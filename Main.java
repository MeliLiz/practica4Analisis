import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        String filePath = "Archivo.txt";
        ArrayList<int[]> cadenas = new ArrayList<int[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Leer línea por línea hasta que alcance el final del archivo
            while ((line = br.readLine()) != null) {
                //Arreglos de las partes de la linea, primero String y luego int
                String[] partesS = line.split("[,:]");
                int[] partes = new int[partesS.length];
                for(int i = 0; i < partesS.length; i++){
                    partes[i] = Integer.parseInt(partesS[i]);
                }
                cadenas.add(partes);
            }

            int[] cadena1 = cadenas.get(0); //Obtenemos el arreglo que tiene los vértices que tenemos en la gráfica, de la linea 1
            Grafica g = new Grafica(cadena1.length, cadenas.size()-1);

            //Agregamos los vértices a la gráfica
            for (int i = 0; i < cadena1.length; i++) {
                g.agregarVertice(cadena1[i]);
            }

            //Agregamos las aristas a las gráficas
            for (int i=1; i< cadenas.size(); i++){
                int[] cadena2 = cadenas.get(i);
                g.agregarArista(cadena2[0], cadena2[1], cadena2[2]);
            }

            //g.imprimirGrafica();
            g.generarMatrizAdyacencias();
            g.imprimirMatriz();
            g.bosqueGenerador();
            g.imprimirArboles();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
