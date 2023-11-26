public class GasStation {

    public static int getRefills(int distance, int refill, int[] stops) {
        int numRefills = 0;
        int currentRefill = 0; // Índice de la última parada de repostaje

        // Caso base: si la distancia es menor que la capacidad del tanque, no es necesario repostar.
        if (distance <= refill) {
            return 0;
        }

        // Caso base: si la primera parada está fuera del alcance, no es posible llegar a la primera estación.
        if (stops[0] > refill) {
            return -1;
        }

        // Algoritmo greedy
        while (currentRefill < stops.length - 1) {
            int nextRefill = currentRefill;

            // Buscar la parada más lejana posible que aún sea alcanzable
            while (nextRefill < stops.length - 1 && stops[nextRefill + 1] - stops[currentRefill] <= refill) {
                nextRefill++;
            }

            // Si no hay paradas alcanzables, devuelve -1
            if (nextRefill == currentRefill) {
                return -1;
            }

            // Incrementar el número de paradas y actualizar la posición del automóvil
            numRefills++;
            currentRefill = nextRefill;
        }

        // Si la última parada de repostaje no está al final del recorrido, considera una parada final.
        if (stops[currentRefill] + refill < distance) {
            numRefills++;
        }

        return numRefills;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        int distance = 500; // Distancia total
        int refill = 200;   // Capacidad del tanque
        int[] stops = {100, 200, 300, 400}; // Ubicaciones de las estaciones de servicio

        int result = getRefills(distance, refill, stops);

        if (result == -1) {
            System.out.println("No es posible realizar el viaje sin quedarse sin combustible.");
        } else {
            System.out.println("Número mínimo de paradas: " + result);
        }
    }
}
