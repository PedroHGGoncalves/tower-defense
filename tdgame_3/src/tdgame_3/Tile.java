package tdgame_3;

//Representa uma célula do mapa (grid)
public class Tile {

    private int col;
    private int lin;
    private boolean caminho;
    private boolean construivel;

    public Tile(int col, int lin) {
        this.col = col;
        this.lin = lin;
        this.caminho = false;
        this.construivel = true;
    }

    public boolean isCaminho() { return caminho; }

 // Define se é caminho; caminho não é construível
    public void setCaminho(boolean c) { 
        caminho = c; 
        if (c) construivel = false; 
    }

 // Retorna se é possível construir torre nesta célula
    public boolean isConstruivel() { return construivel; }

    public void setConstruivel(boolean flag) { construivel = flag; }

    public int getCol() { return col; }

    public int getLin() { return lin; }
}

