public class InversionCount {

    public static void main(String[] args) {
        int[] nums = {1, 8, 9, 2, 3, 4};
        int inversionCount = mergeSort(nums, 0, nums.length - 1);

        System.out.println("El número de inversiones es: " + inversionCount);
    }

    public static int mergeSort(int[] arr, int left, int right) {
        int inversionCount = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            // Inversiones en la mitad izquierda
            inversionCount += mergeSort(arr, left, mid);

            // Inversiones en la mitad derecha
            inversionCount += mergeSort(arr, mid + 1, right);

            // Inversiones en la fusión
            inversionCount += merge(arr, left, mid, right);
        }

        return inversionCount;
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int inversionCount = 0;

        // Tamaños de los subarreglos
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Crear subarreglos temporales
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copiar datos a los subarreglos temporales
        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        // Índices iniciales de los subarreglos temporales
        int i = 0, j = 0;

        // Índice inicial del subarreglo fusionado
        int k = left;

        // Fusionar los subarreglos temporales
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                // Elemento en el subarreglo izquierdo es mayor, hay inversiones
                arr[k++] = rightArray[j++];
                inversionCount += n1 - i;
            }
        }

        // Copiar los elementos restantes de leftArray (si los hay)
        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        // Copiar los elementos restantes de rightArray (si los hay)
        while (j < n2) {
            arr[k++] = rightArray[j++];
        }

        return inversionCount;
    }
}
/*
    complejidad
        O(n log n). 
 */