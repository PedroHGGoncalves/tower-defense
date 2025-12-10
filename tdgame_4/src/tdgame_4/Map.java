package tdgame_4;

import java.awt.*;

//Representa o grid do jogo: caminho inimigo e áreas construíveis.
public class Map {

    public static final int TILE_SIZE = 48;
    public static final int COLS = 20;
    public static final int ROWS = 12;

    private Tile[][] grid;
    private Path caminho;

    public Map() {
        grid = new Tile[ROWS][COLS];
        caminho = new Path();
        gerarGrid();
        gerarCaminho();
    }

    // Inicializa todas as células do grid como construíveis e sem caminho
    private void gerarGrid() {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {
                grid[lin][col] = new Tile(col, lin);
            }
        }
    }

    // Cria caminho horizontal no meio e bloqueia construção
    private void gerarCaminho() {
        for (int col = 0; col < COLS; col++) {
            caminho.add(col, ROWS / 2);
            grid[ROWS / 2][col].setCaminho(true);
            grid[ROWS / 2][col].setConstruivel(false);
        }
    }

    public Path getCaminho() {
        return caminho;
    }

    // Renderiza todas as células e colore a base da direita
    public void desenhar(Graphics g) {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {
                Tile t = grid[lin][col];
                if (t.isCaminho()) g.setColor(Color.YELLOW);
                else if (!t.isConstruivel()) g.setColor(Color.GRAY);
                else g.setColor(Color.DARK_GRAY);

                g.fillRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // base tile (rightmost tile of the path) colored orange
        int baseCol = COLS - 1;
        int baseLin = ROWS / 2;
        g.setColor(new Color(255, 140, 0)); // laranja
        g.fillRect(baseCol * TILE_SIZE, baseLin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(baseCol * TILE_SIZE, baseLin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    // Retorna se é permitido construir na célula especificada
    public boolean podeConstruir(int col, int lin) {
        if (col < 0 || col >= COLS || lin < 0 || lin >= ROWS) return false;
        return grid[lin][col].isConstruivel();
    }
}
