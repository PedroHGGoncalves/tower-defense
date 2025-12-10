package tdgame_4;

import java.awt.*;

//Classe base para torres, gerencia posição, alcance, dano, cooldown e upgrades.
public abstract class Tower {

    protected int gridCol, gridLin, x, y;       // Posição no grid e coordenadas em pixels
    protected int alcance, dano, custo;         
    protected int cooldownMax, cooldown = 0;    // Tempo entre ataques
    protected int nivel = 1;                    
    protected TowerManager manager;             

    public Tower(int col, int lin, TowerManager manager) {
        this.gridCol = col;
        this.gridLin = lin;
        this.x = col * Map.TILE_SIZE;          // Posição em pixels X
        this.y = lin * Map.TILE_SIZE;          // Posição em pixels Y
        this.manager = manager;
    }

    public void update() {
        if (cooldown > 0) cooldown--;          // Decrementa cooldown
        if (cooldown == 0) {
            Enemy alvo = encontrarAlvo();      // Procura inimigo no alcance
            if (alvo != null) {
                atirar(alvo);                  // Atira no alvo
                cooldown = cooldownMax;        // Reseta cooldown
            }
        }
    }

    protected Enemy encontrarAlvo() {
        for (Enemy e : manager.getWaveManager().getInimigos()) {
            if (!e.isVivo()) continue;
            double dx = e.getX() - x;
            double dy = e.getY() - y;
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist <= alcance) return e;     // Retorna o primeiro inimigo no alcance
        }
        return null;
    }

    protected abstract void atirar(Enemy alvo);    
    protected abstract void adicionarEfeito();     

    public void upgrade(Player player) {
        if (nivel >= 3) return;                     // Limite de nível
        if (!player.gastarMoedas(custo)) return;    // Checa custo
        nivel++;
        if (nivel == 2) { dano += 5; cooldownMax = Math.max(1, cooldownMax - 2); }
        if (nivel == 3) { dano += 5; cooldownMax = Math.max(1, cooldownMax - 1); adicionarEfeito(); }
    }

    public void desenhar(Graphics g) {
        g.setColor(Color.MAGENTA);                  
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);
        g.setColor(Color.WHITE);
        g.drawString(custo + " L" + nivel, x + 2, y + 14); // Mostra custo e nível
    }

    public int getGridCol() { return gridCol; }
    public int getGridLin() { return gridLin; }
    public int getCusto() { return custo; }
}

