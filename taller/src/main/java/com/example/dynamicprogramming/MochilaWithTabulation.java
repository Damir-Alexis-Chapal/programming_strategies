package com.example.dynamicprogramming;

public class MochilaWithTabulation {

    public static void main(String[] args) {
        int[] valores = {2, 5, 10, 14, 15};
        int[] pesos = {1, 3, 4, 5, 7};
        int capacidad = 8;

        System.out.println("Valor máximo (Tabulación): " + knapsackTab(valores, pesos, capacidad));
    }

    public static int knapsackTab(int[] valores, int[] pesos, int capacidad) {
        int n = valores.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacidad; w++) {
                if (pesos[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            valores[i - 1] + dp[i - 1][w - pesos[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacidad];
    }
}
