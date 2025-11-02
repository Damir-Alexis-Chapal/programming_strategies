package com.example.divideandconquer;
import java.util.Arrays;

public class SmallerElementsCount {

    public static void main(String[] args) {
        int[] arr = {5, 2, 1, 4, 3};
        int[] res = contarMenores(arr);
        System.out.println("Entrada:  " + Arrays.toString(arr));
        System.out.println("Salida:   " + Arrays.toString(res));
    }

    public static int[] contarMenores(int[] nums) {
        int n = nums.length;
        int[] resultado = new int[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        mergeSort(nums, indices, resultado, 0, n - 1);
        return resultado;
    }

    private static void mergeSort(int[] nums, int[] indices, int[] resultado, int inicio, int fin) {
        if (inicio >= fin) return;

        int mitad = (inicio + fin) / 2;
        mergeSort(nums, indices, resultado, inicio, mitad);
        mergeSort(nums, indices, resultado, mitad + 1, fin);
        merge(nums, indices, resultado, inicio, mitad, fin);
    }

    private static void merge(int[] nums, int[] indices, int[] resultado, int inicio, int mitad, int fin) {
        int[] temp = new int[fin - inicio + 1];
        int i = inicio;       // puntero mitad izquierda
        int j = mitad + 1;    // puntero mitad derecha
        int k = 0;            // índice para temp
        int menoresDerecha = 0;

        while (i <= mitad && j <= fin) {
            if (nums[indices[j]] < nums[indices[i]]) {
                menoresDerecha++;
                temp[k++] = indices[j++];
            } else {
                resultado[indices[i]] += menoresDerecha;
                temp[k++] = indices[i++];
            }
        }

        while (i <= mitad) {
            resultado[indices[i]] += menoresDerecha;
            temp[k++] = indices[i++];
        }

        while (j <= fin) {
            temp[k++] = indices[j++];
        }

        // Copiar los índices ordenados al arreglo original
        for (int t = 0; t < temp.length; t++) {
            indices[inicio + t] = temp[t];
        }
    }

}
