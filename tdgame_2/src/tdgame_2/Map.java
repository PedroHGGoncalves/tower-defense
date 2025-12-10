package tdgame_2;

import java.awt.Graphics;
import java.awt.Color;

public class Map {

    // Dimensões do grid
    public static final int TILE_SIZE = 48;
    public static final int COLS = 20;
    public static final int ROWS = 12;

    private Tile[][] grid;   // Matriz de tiles
    private Path caminho;    // Caminho seguido pelos inimigos

    public Map() {
        grid = new Tile[ROWS][COLS];
        caminho = new Path();

        gerarGrid();    // Inicializa todos os tiles
        gerarCaminho(); // Define o caminho fixo
    }

    private void gerarGrid() {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {
                grid[lin][col] = new Tile(col, lin); // Tile padrão
            }
        }
    }

    private void gerarCaminho() {
        int meio = ROWS / 2; // Linha central do mapa

        for (int col = 0; col < COLS; col++) {
            caminho.add(col, meio);               // Adiciona ponto ao Path
            grid[meio][col].setCaminho(true);     // Marca visualmente como caminho
            grid[meio][col].setConstruivel(false);// Impede construção
        }
    }

    public Path getCaminho() {
        return caminho;
    }

    public void desenhar(Graphics g) {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {

                Tile t = grid[lin][col];

                // Define cor conforme o tipo do tile
                if (t.isCaminho()) g.setColor(Color.YELLOW);
                else if (!t.isConstruivel()) g.setColor(Color.GRAY);
                else g.setColor(Color.DARK_GRAY);

                g.fillRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // Borda do tile
                g.setColor(Color.BLACK);
                g.drawRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public boolean podeConstruir(int col, int lin) {
        // Valida limites do mapa
        if (col < 0 || col >= COLS || lin < 0 || lin >= ROWS) return false;

        // Só permite se o tile aceitar construção
        return grid[lin][col].isConstruivel();
    }
}

