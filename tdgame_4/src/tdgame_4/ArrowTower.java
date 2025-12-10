package tdgame_4;

// Torre barata, com grande alcance, pouco dano e pouco cooldown
//nível 3 aplica efeito Slow
public class ArrowTower extends Tower {

    public ArrowTower(int col, int lin, TowerManager manager) {
        super(col, lin, manager);
        this.alcance = 150;
        this.dano = 10;
        this.custo = 25;
        this.cooldownMax = 20;
    }

    // Dispara projétil em direção ao inimigo
    @Override
    protected void atirar(Enemy alvo) {
        manager.getProjectileManager().spawn(
            new Projectile(x + Map.TILE_SIZE/2, y + Map.TILE_SIZE/2, alvo, 8, dano)
        );
    }

    // Adiciona efeito Slow no alvo (nível 3)
    @Override
    protected void adicionarEfeito() {
        Enemy alvo = encontrarAlvo();
        if (alvo != null) {
            alvo.aplicarEfeito(EffectType.SLOW, 120, 0.25); // 25% de redução de velocidade
        }
    }

    // Desenha torre rosa com info de custo e nível
    @Override
    public void desenhar(java.awt.Graphics g) {
        g.setColor(new java.awt.Color(255, 105, 180));
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);
        g.setColor(java.awt.Color.WHITE);
        g.drawString(custo + " L" + nivel, x + 2, y + 14);
    }
}


