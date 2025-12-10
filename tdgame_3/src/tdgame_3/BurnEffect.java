package tdgame_3;

// Efeito que causa dano contínuo ao longo do tempo
public class BurnEffect extends StatusEffect {

    private int danoPorTick; 

    // Define duração do efeito e dano periódico
    public BurnEffect(int duracaoTicks, int danoPorTick) {
        super(duracaoTicks);
        this.danoPorTick = danoPorTick;
    }

    // Aplica dano enquanto o efeito estiver ativo
    @Override
    public void apply(Enemy e) {
        e.receberDano(danoPorTick);
        tick();      // avança a duração do efeito
    }

    // Burn não altera a velocidade do inimigo
    @Override
    public double modificarVelocidade() {
        return 1.0;
    }

    // Inimigos rápidos são imunes a esse efeito
    @Override
    public boolean ehImune(Enemy e) {
        return e instanceof FastEnemy;
    }
}


