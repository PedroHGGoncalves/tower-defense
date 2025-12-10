package tdgame_2;

import java.util.ArrayList;

public class Path {

    // Sequência ordenada de pontos do caminho
    private ArrayList<int[]> pontos;

    public Path() {
        pontos = new ArrayList<>(); // Lista começa vazia
    }

    public void add(int col, int lin) {
        // Cada ponto é salvo como {coluna, linha}
        pontos.add(new int[]{col, lin});
    }

    public ArrayList<int[]> getPontos() {
        // Usado pelos inimigos para navegar no mapa
        return pontos;
    }
}

