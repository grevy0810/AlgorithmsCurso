public class MinCoins {

    // Método para encontrar el número mínimo de monedas necesarias
    static int getChange(int change) {
        // Definir los valores de las monedas disponibles
        int[] coinValues = {10, 5, 1};

        // Inicializar el contador de monedas
        int numCoins = 0;

        // Iterar a través de los valores de las monedas
        for (int coin : coinValues) {
            // Calcular la cantidad de monedas de este valor
            numCoins += change / coin;

            // Actualizar la cantidad de cambio para la siguiente iteración
            change %= coin;
        }

        // Devolver el número mínimo de monedas
        return numCoins;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        int change = 10007; // 100.07 centavos

        int result = getChange(change);

        System.out.println("Número mínimo de monedas necesarias: " + result);
    }
}
