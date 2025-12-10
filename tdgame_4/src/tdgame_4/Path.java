package tdgame_4;

import java.util.ArrayList;

public class Path {
    private ArrayList<int[]> pontos; // Lista de coordenadas do caminho

    public Path() { 
        pontos = new ArrayList<>(); // Inicializa a lista
    }

    public void add(int col, int lin) { 
        pontos.add(new int[]{col, lin}); // Adiciona um ponto (coluna, linha)
    }

    public ArrayList<int[]> getPontos() { 
        return pontos; // Retorna todos os pontos do caminho
    }
}

