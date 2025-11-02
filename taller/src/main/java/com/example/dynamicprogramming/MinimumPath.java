package com.example.dynamicprogramming;

public class MinimumPath {

    public static void main(String[] args) {
        int[][] matriz = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println("Costo mínimo: " + minCamino(matriz));
    }

    public static int minCamino(int[][] matriz) {

        int m = matriz.length;
        int n = matriz[0].length;
        int[] dp = new int[n];

        // Inicializamos la primera celda
        dp[0] = matriz[0][0];

        // Inicializamos la primera fila (solo podemos movernos hacia la derecha)
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + matriz[0][j];
        }

        // Recorremos el resto de filas
        for (int i = 1; i < m; i++) {
            // Actualizamos la primera columna (solo se puede venir desde arriba)
            dp[0] += matriz[i][0];

            // Para el resto de columnas:
            for (int j = 1; j < n; j++) {
                dp[j] = matriz[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }

        // El último elemento contiene el costo mínimo total
        return dp[n - 1];
    }

}
