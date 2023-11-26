import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Ingrese un número entero positivo:");
        int userInputNumber = inputScanner.nextInt();

        System.out.println(calcularFibonacciPersonalizado(userInputNumber));

        System.out.println(calcularUltimoDigitoFibonacciPersonalizado(userInputNumber));

        long customW = 100_000;
        int customM = 10_000;
        System.out.println(calcularFibonacciModuloM(customW, customM));

        int customS = 100;
        System.out.println(calcularSumaFibonacciPersonalizado(customS));

        int customK = 10;
        int customL = 10;
        System.out.println(calcularSumaParcialFibonacciPersonalizado(customK, customL));

        int customF = 1234567890;
        System.out.println(calcularSumaParcialCuadradosFibonacciPersonalizado(customF));

        inputScanner.close();
    }

    public static long calcularFibonacciPersonalizado(int n) {
        if (n == 0) {
            return 0;
        }
        long actual = 1;
        long anterior = 0;
        long auxiliar;

        for (int i = 1; i < n; i++) {
            auxiliar = actual;
            actual += anterior;
            anterior = auxiliar;
        }

        return actual;
    }

    public static int calcularUltimoDigitoFibonacciPersonalizado(int n) {
        int actual = 1;
        int anterior = 0;
        int auxiliar;

        for (int i = 1; i < n; i++) {
            auxiliar = actual % 10;
            actual = (actual + anterior) % 10;
            anterior = auxiliar;
        }

        return actual;
    }

    public static int calcularFibonacciModuloM(long n, int m) {
        int actual = 1;
        int anterior = 0;
        int auxiliar;

        for (long i = 1; i < n; i++) {
            auxiliar = actual;
            actual = (actual + anterior) % m;
            anterior = auxiliar;
        }

        return actual;
    }

    public static int calcularSumaFibonacciPersonalizado(int n) {
        return calcularSumaParcialFibonacciPersonalizado(0, n);
    }

    public static int calcularSumaParcialFibonacciPersonalizado(int m, int n) {
        int[] residuos = new int[obtenerPeriodoPersonalizado()];
        residuos[0] = 0;
        residuos[1] = 1;
        for (int i = 2; i < residuos.length; i++) {
            residuos[i] = residuos[i - 2] + residuos[i - 1];
        }

        int suma = 0;
        for (int i = m; i <= n; i++) {
            suma = (suma + residuos[i % residuos.length]) % 10;
        }

        return suma;
    }

    public static int calcularSumaParcialCuadradosFibonacciPersonalizado(int n) {
        int[] residuos = new int[obtenerPeriodoPersonalizado()];
        residuos[0] = 0;
        residuos[1] = 1;
        int[] potencias = new int[residuos.length];
        potencias[0] = 0;
        potencias[1] = 1;
        for (int i = 2; i < residuos.length; i++) {
            residuos[i] = (residuos[i - 2] + residuos[i - 1]) % 10;
            potencias[i] = (int) Math.pow(residuos[i], 2) % 10;
        }

        int suma = 0;
        for (int i = 0; i <= n; i++) {
            suma = (suma + potencias[i % potencias.length]) % 10;
        }

        return suma;
    }

    private static int obtenerPeriodoPersonalizado() {
        int mod = 10;
        int periodos = 1;
        int actual = 1;
        int anterior = 0;
        int auxiliar;

        while (true) {
            auxiliar = actual;
            actual = (actual + anterior) % mod;
            anterior = auxiliar;
            if (actual == 1 && anterior == 0) {
                break;
            }
            periodos += 1;
        }

        return periodos;
    }
}
