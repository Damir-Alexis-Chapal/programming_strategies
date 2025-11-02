package com.example.greedyalgorithms;
import java.util.*;
public class ArbolRecubrimientoMinimo {

    public static void main(String[] args) {

        int n = 6;
        List<Edge> edges = new ArrayList<>();
        // agregar aristas (i, j, peso)
        edges.add(new Edge(0,1,40000000));
        edges.add(new Edge(0,2,30000000));
        edges.add(new Edge(1,2,10000000));
        edges.add(new Edge(1,3,20000000));
        edges.add(new Edge(2,3,40000000));
        edges.add(new Edge(3,4,20000000));
        edges.add(new Edge(4,5,60000000));
        edges.add(new Edge(3,5,30000000));

        // 1) KRUSKAL
        Result rK = kruskal(n, edges);
        System.out.println("Kruskal MST:");
        for (Edge e : rK.mst) System.out.println("  " + e);
        System.out.printf("Costo total Kruskal: %.2f\n\n", rK.totalWeight);

        // 2) PRIM
        // Crear lista de adyacencia bidireccional
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.i).add(new Edge(e.i, e.j, e.w));
            adj.get(e.j).add(new Edge(e.j, e.i, e.w));
        }
        Result rP = prim(n, adj, 0);
        System.out.println("Prim MST (iniciando en nodo 0):");
        for (Edge e : rP.mst) System.out.println("  " + e);
        System.out.printf("Costo total Prim: %.2f\n", rP.totalWeight);
    }

    // Arista y peso (i--w--j)
    static class Edge {
        int i, j;
        double w;
        Edge(int i, int j, double w) { this.i = i; this.j = j; this.w = w; }
        public String toString() { return String.format("(%d - %d : %.2f)", i, j, w); }
    }

    // Union-Find para Kruskal
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[rb] < rank[ra]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
            return true;
        }
    }

    // KRUSKAL
    public static Result kruskal(int n, List<Edge> edges) {
        // n = número de nodos (0..n-1)
        // edges = lista de aristas (i, j, w)
        Collections.sort(edges, Comparator.comparingDouble(e -> e.w));
        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();
        double total = 0;
        for (Edge e : edges) {
            if (uf.union(e.i, e.j)) {
                mst.add(e);
                total += e.w;
                if (mst.size() == n - 1) break;
            }
        }
        return new Result(mst, total);
    }

    // PRIM (cola de prioridad, O(E log V))
    public static Result prim(int n, List<List<Edge>> adj, int start) {
        boolean[] inMST = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.w));
        List<Edge> mst = new ArrayList<>();
        double total = 0;

        // Añadir aristas desde el nodo inicial
        inMST[start] = true;
        for (Edge e : adj.get(start)) pq.add(new Edge(start, e.j, e.w));

        while (!pq.isEmpty() && mst.size() < n - 1) {
            Edge e = pq.poll();
            if (inMST[e.j]) continue; // si extremo ya en MST, ignorar
            // aceptar arista
            inMST[e.j] = true;
            mst.add(e);
            total += e.w;
            // agregar aristas desde el nodo recién añadido
            for (Edge next : adj.get(e.j)) {
                if (!inMST[next.j]) {
                    pq.add(new Edge(e.j, next.j, next.w));
                }
            }
        }

        return new Result(mst, total);
    }

    // Para resultados
    static class Result {
        List<Edge> mst;
        double totalWeight;
        Result(List<Edge> mst, double totalWeight) { this.mst = mst; this.totalWeight = totalWeight; }
    }

}
