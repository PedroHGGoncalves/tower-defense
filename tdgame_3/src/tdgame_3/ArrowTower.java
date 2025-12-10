package tdgame_3;

// Torre básica que dispara projéteis rápidos e baratos
public class ArrowTower extends Tower {

    // Define os atributos iniciais da torre
    public ArrowTower(int col, int lin, TowerManager manager) {
        super(col, lin, manager);
        this.alcance = 150;
        this.dano = 10;
        this.custo = 25;
        this.cooldownMax = 20;
    }

    // Cria e dispara um projétil contra o inimigo alvo
    @Override
    protected void atirar(Enemy alvo) {
        manager.getProjectileManager().spawn(
            new Projectile(
                x + Map.TILE_SIZE / 2, // origem no centro da torre
                y + Map.TILE_SIZE / 2,
                alvo,
                8,
                dano
            )
        );
    }

    // Aplica efeito especial (slow) em níveis mais altos
    @Override
    protected void adicionarEfeito() {
        Enemy alvo = encontrarAlvo();
        if (alvo != null) {
            alvo.aplicarEfeito(EffectType.SLOW, 120, 0.5);
        }
    }
}




