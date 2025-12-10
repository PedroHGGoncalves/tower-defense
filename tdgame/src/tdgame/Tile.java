package tdgame;

/*
 * Representa uma célula individual do grid do mapa,
 * armazena posição e verifica se faz parte do caminho.
 */
public class Tile {

    // Coordenadas lógicas da célula no grid
    private int col;
    private int lin;

    // Indica se esta célula pertence ao caminho dos inimigos
    private boolean caminho;

    public Tile(int col, int lin) {
        // Define a posição da célula no grid
        this.col = col;
        this.lin = lin;

        // Inicia como área comum (não caminho)
        this.caminho = false;
    }

    // Informa se a célula faz parte do caminho
    public boolean isCaminho() {
        return caminho;
    }

    // Marca ou desmarca a célula como caminho
    public void setCaminho(boolean c) {
        caminho = c;
    }
}


