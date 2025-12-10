package tdgame;

import java.util.ArrayList;

//Caminho dos inimigos
public class Path {

    // Lista de pontos que formam o caminho
    private ArrayList<int[]> pontos;

    public Path() {
        pontos = new ArrayList<>();
    }

    //Adiciona um ponto no final do caminho
    public void add(int col, int lin) {
        pontos.add(new int[]{col, lin});
    }

    
    public ArrayList<int[]> getPontos() {
        return pontos;
    }
}


