package com.example.divideandconquer;

public class MajorityElement {

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int resultado = encontrarMayoritario(arr);
        System.out.println("Elemento mayoritario: " + resultado);
    }

    public static int encontrarMayoritario(int[] nums) {
        return mayoritarioRec(nums, 0, nums.length - 1);
    }

    private static int mayoritarioRec(int[] nums, int inicio, int fin) {

        if (inicio == fin) {
            return nums[inicio];
        }

        int mitad = (inicio + fin) / 2;
        int izq = mayoritarioRec(nums, inicio, mitad);
        int der = mayoritarioRec(nums, mitad + 1, fin);

        // Si ambos lados coinciden, ese es el mayoritario
        if (izq == der) {
            return izq;
        }

        // Si no coinciden, contar frecuencias de ambos
        int conteoIzq = contar(nums, izq, inicio, fin);
        int conteoDer = contar(nums, der, inicio, fin);

        // Retornar el que más aparece
        int longitud = fin - inicio + 1;
        if (conteoIzq > longitud / 2) return izq;
        if (conteoDer > longitud / 2) return der;
        return -1;
    }

    private static int contar(int[] nums, int num, int inicio, int fin) {
        int conteo = 0;
        for (int i = inicio; i <= fin; i++) {
            if (nums[i] == num) conteo++;
        }
        return conteo;
    }

}
