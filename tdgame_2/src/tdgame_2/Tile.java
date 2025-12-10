package tdgame_2;

public class Tile {

    // Posição da célula no grid
    private int col;
    private int lin;

    // Controle de uso da célula
    private boolean caminho;       // inimigos passam por aqui
    private boolean construivel;   // torres podem ser colocadas

    public Tile(int col, int lin) {
        this.col = col;
        this.lin = lin;
        caminho = false;           // inicia como chão comum
        construivel = true;        // permite construção por padrão
    }

    public boolean isCaminho() {
        return caminho;            // consulta se faz parte do caminho
    }

    public void setCaminho(boolean c) {
        caminho = c;               // define como caminho
        if (c) construivel = false; // caminho nunca é construível
    }

    public boolean isConstruivel() {
        return construivel;        // consulta permissão de construção
    }

    public void setConstruivel(boolean flag) {
        construivel = flag;        // altera permissão manualmente
    }

    public int getCol() {
        return col;                // coluna no grid
    }

    public int getLin() {
        return lin;                // linha no grid
    }
}

