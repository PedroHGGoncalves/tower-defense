package tdgame_3;

// Reduz a velocidade do inimigo
public class SlowEffect extends StatusEffect {

    private double fator; 

    // Inicializa duração e intensidade do slow
    public SlowEffect(int duracaoTicks, double fator) {
        super(duracaoTicks);
        this.fator = fator;
    }

    @Override
    public void apply(Enemy e) {
        tick(); // decrementa a duração a cada aplicação
    }

    @Override
    public double modificarVelocidade() {
        return fator; // retorna quanto a velocidade deve ser reduzida
    }

    @Override
    public boolean ehImune(Enemy e) {
        return e instanceof TankEnemy; // inimigos tanque não sofrem slow
    }
}
