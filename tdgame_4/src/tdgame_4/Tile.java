package tdgame_4;

//Representa uma célula do mapa, podendo ser caminho ou construível.
public class Tile {
    private int col;          // Coluna da célula
    private int lin;          // Linha da célula
    private boolean caminho;  // Indica se faz parte do caminho
    private boolean construivel; // Indica se torre pode ser construída

    public Tile(int col, int lin) {
        this.col = col;
        this.lin = lin;
        this.caminho = false;     // Inicialmente não é caminho
        this.construivel = true;  // Inicialmente construível
    }

    public boolean isCaminho() { return caminho; } // Retorna se é caminho

    public void setCaminho(boolean c) {
        caminho = c;              // Define como caminho
        if (c) construivel = false; // Caminho não é construível
    }

    public boolean isConstruivel() { return construivel; } // Retorna se é construível

    public void setConstruivel(boolean flag) { construivel = flag; } // Define construível

    public int getCol() { return col; } // Retorna coluna

    public int getLin() { return lin; } // Retorna linha
}


