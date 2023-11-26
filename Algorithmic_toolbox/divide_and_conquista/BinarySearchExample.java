public class BinarySearchExample {

    public static void main(String[] args) {
        // Arreglo ordenado
        int[] nums = {1, 5, 8, 12, 13};

        // Elementos a buscar
        int[] search = {8, 1, 23, 1, 11};

        // Realizar la búsqueda binaria para cada elemento en el arreglo 'search'
        for (int target : search) {
            int result = binarySearch(nums, target);

            if (result != -1) {
                System.out.println("Elemento " + target + " encontrado en la posición " + result);
            } else {
                System.out.println("Elemento " + target + " no encontrado en el arreglo");
            }
        }
    }

    // Implementación del algoritmo de búsqueda binaria
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Elemento encontrado
            } else if (arr[mid] < target) {
                left = mid + 1; // Buscar en la mitad derecha
            } else {
                right = mid - 1; // Buscar en la mitad izquierda
            }
        }

        return -1; // Elemento no encontrado
    }
}
/*
     O(log2 n)
 */