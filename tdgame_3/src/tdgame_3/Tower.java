package tdgame_3;

// Classe base para torres, gerencia ataque, cooldown e upgrades
public abstract class Tower {

    protected int gridCol, gridLin, x, y;
    protected int alcance, dano, custo;
    protected int cooldownMax, cooldown = 0;
    protected int nivel = 1; // níveis de upgrade
    protected TowerManager manager;

    public Tower(int col, int lin, TowerManager manager) {
        this.gridCol = col;
        this.gridLin = lin;
        this.x = col * Map.TILE_SIZE;
        this.y = lin * Map.TILE_SIZE;
        this.manager = manager;
    }

    // Atualiza cooldown e dispara projétil se possível
    public void update() {
        if (cooldown > 0) cooldown--;
        if (cooldown == 0) {
            Enemy alvo = encontrarAlvo();
            if (alvo != null) {
                atirar(alvo);
                cooldown = cooldownMax;
            }
        }
    }

    // Encontra o primeiro inimigo dentro do alcance
    protected Enemy encontrarAlvo() {
        for (Enemy e : manager.getWaveManager().getInimigos()) {
            if (!e.isVivo()) continue;
            double dx = e.getX() - x;
            double dy = e.getY() - y;
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist <= alcance) return e;
        }
        return null;
    }

    protected abstract void atirar(Enemy alvo); // dispara projétil

    // Realiza upgrade da torre (máx. nível 3)
    public void upgrade(Player player) {
        if (nivel >= 3) return;
        if (!player.gastarMoedas(custo)) return;

        nivel++;
        if (nivel == 2) { dano += 5; cooldownMax = Math.max(1, cooldownMax - 2); }
        if (nivel == 3) { dano += 5; cooldownMax = Math.max(1, cooldownMax - 1); adicionarEfeito(); }
    }

    protected abstract void adicionarEfeito(); // efeito de status nível 3

    // Desenha torre no mapa
    public void desenhar(java.awt.Graphics g) {
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);
        g.setColor(java.awt.Color.WHITE);
        g.drawString(custo + " L" + nivel, x + 2, y + 14);
    }

    public int getGridCol() { return gridCol; }
    public int getGridLin() { return gridLin; }
    public int getCusto() { return custo; }
}



