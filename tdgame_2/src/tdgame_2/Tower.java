package tdgame_2;

public abstract class Tower {

    // Posição da torre no grid e em pixels
    protected int gridCol, gridLin;
    protected int x, y;

    // Atributos de combate
    protected int alcance;
    protected int dano;

    // Economia e cadência
    protected int custo;
    protected int cooldownMax;
    protected int cooldown = 0;

    // Acesso aos sistemas do jogo
    protected TowerManager manager;

    public Tower(int col, int lin, TowerManager manager) {
        gridCol = col;
        gridLin = lin;
        x = col * Map.TILE_SIZE;     // converte grid → pixels
        y = lin * Map.TILE_SIZE;
        this.manager = manager;
    }

    public void update() {

        // Atualiza recarga
        if (cooldown > 0) cooldown--;

        // Torre pronta para agir
        if (cooldown == 0) {
            Enemy alvo = encontrarAlvo();
            if (alvo != null) {
                atirar(alvo);
                cooldown = cooldownMax;
            }
        }
    }

    protected Enemy encontrarAlvo() {

        // Busca inimigo vivo dentro do alcance
        for (Enemy e : manager.getWaveManager().getInimigos()) {

            if (!e.isVivo()) continue;

            double dx = e.getX() - x;
            double dy = e.getY() - y;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist <= alcance) {
                return e; // primeiro alvo válido
            }
        }
        return null;
    }

    // Cada torre define seu tipo de disparo
    protected abstract void atirar(Enemy alvo);

    public void desenhar(java.awt.Graphics g) {
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);

        // Exibe custo (debug / feedback visual)
        g.setColor(java.awt.Color.WHITE);
        g.drawString(String.valueOf(custo), x + 4, y + 14);
    }

    public int getGridCol() {
        return gridCol;
    }

    public int getGridLin() {
        return gridLin;
    }

    public int getCusto() {
        return custo;
    }
}


