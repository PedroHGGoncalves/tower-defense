package tdgame_3;

// Torre cara, de alto dano e baixa cadência
public class CannonTower extends Tower {

    // Atributos iniciais da torre
    public CannonTower(int col, int lin, TowerManager manager) {
        super(col, lin, manager);
        this.alcance = 120;
        this.dano = 20;
        this.custo = 45;
        this.cooldownMax = 40;
    }

    // Dispara um projétil mais lento e mais forte
    @Override
    protected void atirar(Enemy alvo) {
        manager.getProjectileManager().spawn(
            new Projectile(
                x + Map.TILE_SIZE / 2,
                y + Map.TILE_SIZE / 2,
                alvo,
                5,
                dano
            )
        );
    }

    // Aplica efeito de queimadura em níveis avançados
    @Override
    protected void adicionarEfeito() {
        Enemy alvo = encontrarAlvo();
        if (alvo != null) {
            alvo.aplicarEfeito(EffectType.BURN, 100, 2.0);
        }
    }
}



