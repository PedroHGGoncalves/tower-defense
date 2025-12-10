package tdgame_2;

import java.awt.Graphics;
import java.awt.Color;

/*
 * Classe base para inimigos do jogo,
 * responsável por movimento, vida e renderização.
 */
public abstract class Enemy {

    // Estado e atributos principais
    protected double x, y;
    protected int vida;
    protected double velocidade;
    protected int recompensa = 1;

    // Controle do caminho
    protected int indexCaminho = 0;
    protected Path caminho;

    // Estado de vida
    protected boolean vivo = true;

    public Enemy(Path caminho) {
        // Define o caminho e posiciona no ponto inicial
        this.caminho = caminho;
        int[] start = caminho.getPontos().get(0);
        this.x = start[0] * Map.TILE_SIZE;
        this.y = start[1] * Map.TILE_SIZE;
    }

    // Move o inimigo suavemente entre os pontos do caminho
    public void mover() {

        if (!vivo) return;
        if (indexCaminho >= caminho.getPontos().size() - 1) return;

        int[] destino = caminho.getPontos().get(indexCaminho + 1);

        double dx = destino[0] * Map.TILE_SIZE - x;
        double dy = destino[1] * Map.TILE_SIZE - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        // Avança o índice ao alcançar o próximo tile
        if (dist < velocidade) {
            indexCaminho++;

            if (indexCaminho < caminho.getPontos().size()) {
                int[] p = caminho.getPontos().get(indexCaminho);
                x = p[0] * Map.TILE_SIZE;
                y = p[1] * Map.TILE_SIZE;
            }
        } else {
            // Movimento contínuo proporcional à velocidade
            x += (dx / dist) * velocidade;
            y += (dy / dist) * velocidade;
        }
    }

    // Indica se chegou ao final do caminho
    public boolean chegouNaBase() {
        return indexCaminho >= caminho.getPontos().size() - 1;
    }

    // Aplica dano e verifica morte
    public void receberDano(int dano) {
        if (!vivo) return;

        vida -= dano;
        if (vida <= 0) {
            morrer();
        }
    }

    // Atualiza o estado do inimigo para morto
    protected void morrer() {
        vivo = false;
    }

    public boolean isVivo() {
        return vivo;
    }

    public int getRecompensa() {
        return recompensa;
    }

    public int getVida() {
        return vida;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Renderiza o inimigo e sua vida atual
    public void desenhar(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, Map.TILE_SIZE, Map.TILE_SIZE);

        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(vida), (int) x, (int) y - 2);
    }
}

