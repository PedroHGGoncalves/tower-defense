package tdgame_4;

// Torre cara, com curto alcance, alto dano e alto cooldown 
//nível 3 aplica efeito Burn
public class CannonTower extends Tower {

    public CannonTower(int col, int lin, TowerManager manager) {
        super(col, lin, manager);
        this.alcance = 120;
        this.dano = 20;
        this.custo = 45;
        this.cooldownMax = 30;
    }

    // Dispara projétil em direção ao inimigo
    @Override
    protected void atirar(Enemy alvo) {
        manager.getProjectileManager().spawn(
            new Projectile(x + Map.TILE_SIZE/2, y + Map.TILE_SIZE/2, alvo, 5, dano)
        );
    }

    // Aplica efeito Burn no alvo (nível 3)
    @Override
    protected void adicionarEfeito() {
        Enemy alvo = encontrarAlvo();
        if (alvo != null) {
            alvo.aplicarEfeito(EffectType.BURN, 100, 2.0); // 2 de dano por tick
        }
    }

    // Desenha torre marrom com info de custo e nível
    @Override
    public void desenhar(java.awt.Graphics g) {
        g.setColor(new java.awt.Color(139, 69, 19));
        g.fillRect(x, y, Map.TILE_SIZE, Map.TILE_SIZE);
        g.setColor(java.awt.Color.WHITE);
        g.drawString(custo + " L" + nivel, x + 2, y + 14);
    }
}

