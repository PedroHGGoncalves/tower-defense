package tdgame;

import java.awt.Graphics;
import java.awt.Color;

/*
 * Representa o mapa do jogo em formato de grid.
 * Define a área jogável, os tiles e um caminho fixo
 */
public class Map {

    // Configurações do grid
    public static final int TILE_SIZE = 48;
    public static final int COLS = 20;
    public static final int ROWS = 12;

    // Estrutura do mapa e caminho dos inimigos
    private Tile[][] grid;
    private Path caminho;

    public Map() {
        grid = new Tile[ROWS][COLS];
        caminho = new Path();

        // Construção do mapa
        gerarGrid();
        gerarCaminho();
    }

    //Inicializa todas as células do grid.
    private void gerarGrid() {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {
                grid[lin][col] = new Tile(col, lin);
            }
        }
    }

    private void gerarCaminho() {
        int linhaCentral = ROWS / 2;

        for (int col = 0; col < COLS; col++) {
            caminho.add(col, linhaCentral);
            grid[linhaCentral][col].setCaminho(true);
        }
    }

    // Exposto para que WaveManager possa gerar inimigos
    public Path getCaminho() {
        return caminho;
    }

    // Renderização do mapa em tiles.
    public void desenhar(Graphics g) {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {

                Tile t = grid[lin][col];

                //Define as cores para diferenciar caminho de construível
                g.setColor(t.isCaminho() ? Color.YELLOW : Color.DARK_GRAY);
                g.fillRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(col * TILE_SIZE, lin * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}
