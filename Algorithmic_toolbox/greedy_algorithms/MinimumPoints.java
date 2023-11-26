import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class MinimumPoints {

    // Clase interna para almacenar el resultado
    static class Points {
        int numPoints;
        ArrayList<Integer> points;

        Points(int numPoints, ArrayList<Integer> points) {
            this.numPoints = numPoints;
            this.points = points;
        }
    }

    // Método para encontrar el mínimo número de puntos
    static Points getPoints(int[][] lines) {
        int numLines = lines.length;

        // Caso especial: si hay solo una línea
        if (numLines == 1) {
            int singlePoint = lines[0][1];
            ArrayList<Integer> singlePointList = new ArrayList<>();
            singlePointList.add(singlePoint);
            return new Points(1, singlePointList);
        }

        // Ordenar las líneas según sus coordenadas izquierdas (puntos de inicio)
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));

        int farthest = lines[0][1]; // Punto más lejano

        ArrayList<Integer> resultPoints = new ArrayList<>();
        resultPoints.add(farthest); // Agregar el primer punto
        int numPoints = 1;

        // Iterar sobre cada línea ordenada
        for (int i = 1; i < numLines; i++) {
            int currentEnd = lines[i][1];

            // Comparar el punto final de la línea actual con el punto más lejano
            if (currentEnd < farthest) {
                farthest = currentEnd;
            } else {
                // Agregar el punto más lejano a la lista de puntos
                resultPoints.add(farthest);
                numPoints++;

                // Actualizar el punto más lejano al punto final de la línea actual
                farthest = currentEnd;
            }
        }

        // Añadir el último punto
        resultPoints.add(farthest);
        numPoints++;

        // Devolver el objeto Points con el resultado
        return new Points(numPoints, resultPoints);
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        int[][] lines = {{1, 3}, {2, 5}, {5, 6}, {7, 9}};

        Points result = getPoints(lines);

        System.out.println("Número mínimo de puntos: " + result.numPoints);
        System.out.println("Lista de puntos: " + result.points);
    }
}
