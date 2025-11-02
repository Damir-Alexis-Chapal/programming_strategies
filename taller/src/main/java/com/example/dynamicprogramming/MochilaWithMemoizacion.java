package com.example.dynamicprogramming;
import java.util.Arrays;

public class MochilaWithMemoizacion {

    public static void main(String[] args) {
        int[] valores = {2, 5, 10, 14, 15};
        int[] pesos = {1, 3, 4, 5, 7};
        int capacidad = 8;
        int n = valores.length;

        memo = new int[n + 1][capacidad + 1];
        for (int[] fila : memo) {
            Arrays.fill(fila, -1);
        }

        System.out.println("Valor máximo (Memoización): " + knapsackMemo(valores, pesos, capacidad, n));
    }

    static int[][] memo;

    public static int knapsackMemo(int[] valores, int[] pesos, int capacidad, int n) {
        if (n == 0 || capacidad == 0) {
            return 0;
        }

        if (memo[n][capacidad] != -1) {
            return memo[n][capacidad];
        }

        if (pesos[n - 1] > capacidad) {
            memo[n][capacidad] = knapsackMemo(valores, pesos, capacidad, n - 1);
        } else {
            int incluir = valores[n - 1] + knapsackMemo(valores, pesos, capacidad - pesos[n - 1], n - 1);
            int excluir = knapsackMemo(valores, pesos, capacidad, n - 1);
            memo[n][capacidad] = Math.max(incluir, excluir);
        }

        return memo[n][capacidad];
    }
}
