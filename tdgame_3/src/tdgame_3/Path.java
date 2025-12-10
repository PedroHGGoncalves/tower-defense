package tdgame_3;

import java.util.ArrayList;

// Armazena a sequência de células que formam o caminho dos inimigos
public class Path {

    private ArrayList<int[]> pontos; // lista ordenada de pontos (col, lin)

    // Cria um caminho vazio
    public Path() {
        pontos = new ArrayList<>();
    }

    // Adiciona um novo ponto ao caminho
    public void add(int col, int lin) {
        pontos.add(new int[]{col, lin});
    }

    // Retorna a sequência completa do caminho
    public ArrayList<int[]> getPontos() {
        return pontos;
    }
}


