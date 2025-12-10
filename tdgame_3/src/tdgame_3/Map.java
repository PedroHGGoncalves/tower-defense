package tdgame_3;

import java.awt.Graphics;
import java.awt.Color;

// Mapa do jogo (grid + caminho fixo)
public class Map {

    public static final int TILE_SIZE = 48;   // tamanho de cada célula
    public static final int COLS = 20;         // número de colunas
    public static final int ROWS = 12;         // número de linhas

    private Tile[][] grid;    // grade de células do mapa
    private Path caminho;     

    // Inicializa o grid e define o caminho
    public Map() {
        grid = new Tile[ROWS][COLS];
        caminho = new Path();
        gerarGrid();          // cria as células do mapa
        gerarCaminho();       // define o caminho fixo
    }

    // Cria todas as células do grid
    private void gerarGrid() {
        for (int lin = 0; lin < ROWS; lin++) {
            for (int col = 0; col < COLS; col++) {
                grid[lin][col] = new Tile(col, lin);
            }
        }
    }

    // Gera um caminho horizontal no meio do mapa
    // Células do caminho não permitem construção
    private void gerarCaminho() {
        for (int col = 0; col < COLS; col++) {
            caminho.add(col, ROWS / 2);
            grid[ROWS / 2][col].setCaminho(true);
            grid[ROWS / 2][col].setConstruivel(false);
        }
    }

    // Retorna o caminho utilizado pelos inimigos
    public Path getCaminho() {
        return caminho;
    }

    // Desenha o mapa e seus tipos de célula
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
    }

    // Verifica se uma célula permite construção de torre
    public boolean podeConstruir(int col, int lin) {
        if (col < 0 || col >= COLS || lin < 0 || lin >= ROWS) return false;
        return grid[lin][col].isConstruivel();
    }
}


