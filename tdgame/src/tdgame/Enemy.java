package tdgame;

import java.awt.Graphics;
import java.awt.Color;

/*
 * Classe base abstrata para todos os inimigos do jogo.
 * Centraliza a lógica comum de movimento e atributos
 */
public abstract class Enemy {

    // Posição contínua em pixels (permite movimento suave)
    protected double x, y;

    // Atributos básicos do inimigo
    protected int vida;
    protected double velocidade;

    // Controle de progressão ao longo do caminho
    protected int indexCaminho = 0;
    protected Path caminho;

    public Enemy(Path caminho) {
        this.caminho = caminho;

        // Inicia o inimigo no primeiro ponto do caminho
        int[] start = caminho.getPontos().get(0);
        this.x = start[0] * Map.TILE_SIZE;
        this.y = start[1] * Map.TILE_SIZE;
    }

     // Move o inimigo seguindo os pontos do caminho,
    public void mover() {

        // Já alcançou o último ponto do caminho
        if (indexCaminho >= caminho.getPontos().size() - 1)
            return;

        int[] destino = caminho.getPontos().get(indexCaminho + 1);

        double dx = destino[0] * Map.TILE_SIZE - x;
        double dy = destino[1] * Map.TILE_SIZE - y;

        double dist = Math.sqrt(dx * dx + dy * dy);

        // Avança para o próximo ponto ao chegar suficientemente perto
        if (dist < velocidade) {
            indexCaminho++;
        } else {
            // Normaliza o vetor direção e aplica a velocidade
            x += (dx / dist) * velocidade;
            y += (dy / dist) * velocidade;
        }
    }

    
     //Indica se o inimigo chegou ao final do caminho    
    public boolean chegouNaBase() {
        return indexCaminho >= caminho.getPontos().size() - 1;
    }

    public void desenhar(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, Map.TILE_SIZE, Map.TILE_SIZE);
    }
}

