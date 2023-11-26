import java.util.Arrays;

public class FractionalKnapsack {

    // Clase interna para almacenar información sobre cada elemento
    static class Pair {
        double weight;
        double valuePerPound;

        Pair(double weight, double value, double valuePerPound) {
            this.weight = weight;
            this.valuePerPound = valuePerPound;
        }
    }

    // Método para resolver el problema de la mochila fraccionaria
    static double getOptimalValue(double capacity, double[] costs, double[] weights) {
        int n = costs.length;

        // Crear un array de objetos Pair para almacenar información sobre cada elemento
        Pair[] data = new Pair[n];
        for (int i = 0; i < n; i++) {
            double valuePerPound = costs[i] / weights[i];
            data[i] = new Pair(weights[i], costs[i], valuePerPound);
        }

        // Ordenar el array en orden descendente según el valor por libra
        Arrays.sort(data, (a, b) -> Double.compare(b.valuePerPound, a.valuePerPound));

        double totalValue = 0;

        // Iterar sobre los elementos y seleccionar la mayor cantidad posible
        for (int i = 0; i < n; i++) {
            if (capacity == 0) {
                break;
            }

            double minWeight = Math.min(data[i].weight, capacity);
            totalValue += minWeight * data[i].valuePerPound;
            capacity -= minWeight;
        }

        // Devolver el valor total óptimo
        return totalValue;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        double capacity = 50; // Capacidad de la mochila
        double[] costs = {60, 100, 120}; // Valores de los elementos
        double[] weights = {10, 20, 30}; // Pesos de los elementos

        double result = getOptimalValue(capacity, costs, weights);

        System.out.println("El valor total óptimo es: " + result);
    }
}
