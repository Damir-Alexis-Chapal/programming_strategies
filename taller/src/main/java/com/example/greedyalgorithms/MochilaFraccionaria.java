package com.example.greedyalgorithms;
import java.util.*;

public class MochilaFraccionaria {

    static class Objeto {
        int cantidad;
        double peso;
        double valor;
        double valorPorPeso;

        Objeto(int cantidad, double peso, double valor) {
            this.cantidad = cantidad;
            this.peso = peso;
            this.valor = valor;
            this.valorPorPeso = valor / peso;
        }
    }

    public static void main(String[] args) {

        Objeto[] objetos = {
                new Objeto(3, 210, 15),
                new Objeto(2, 230, 50),
                new Objeto(4, 150, 20),
                new Objeto(5, 40, 55),
                new Objeto(3, 80, 92)
        };
        double pesoMaximo = 520;

        double valorTotal = 0;
        double pesoActual = 0;

        // Ordenar por valor por peso descendente
        Arrays.sort(objetos, (a, b) -> Double.compare(b.valorPorPeso, a.valorPorPeso));

        System.out.println("Orden de selección (por valor/peso):");
        for (Objeto o : objetos) {
            System.out.printf("Valor/Peso = %.2f (valor %.2f, peso %.2f)%n", o.valorPorPeso, o.valor, o.peso);
        }

        System.out.println("\nObjetos seleccionados:");
        for (Objeto o : objetos) {
            for (int i = 0; i < o.cantidad; i++) {
                if (pesoActual + o.peso <= pesoMaximo) {
                    pesoActual += o.peso;
                    valorTotal += o.valor;
                    System.out.printf("Tomado completo (valor %.2f, peso %.2f)%n", o.valor, o.peso);
                } else {
                    double espacioRestante = pesoMaximo - pesoActual;
                    if (espacioRestante <= 0) break;

                    double fraccion = espacioRestante / o.peso;
                    valorTotal += o.valor * fraccion;
                    pesoActual += espacioRestante;
                    System.out.printf("Tomado %.2f%% del objeto (valor %.2f, peso %.2f)%n",
                            fraccion * 100, o.valor * fraccion, espacioRestante);
                    break;
                }
            }
            if (pesoActual >= pesoMaximo) break;
        }

        System.out.println("\nPeso total cargado: " + pesoActual);
        System.out.println("Valor total obtenido: " + valorTotal);
    }
}

