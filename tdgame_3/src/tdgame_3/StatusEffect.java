package tdgame_3;

// Classe base para efeitos de status
public abstract class StatusEffect {

    protected int duracaoTicks; 

    // Inicializa duração do efeito
    public StatusEffect(int duracaoTicks) {
        this.duracaoTicks = duracaoTicks;
    }

    // Aplica o efeito a cada tick
    public abstract void apply(Enemy e);

    
    public abstract double modificarVelocidade();

    
    public abstract boolean ehImune(Enemy e);

    // Retorna se o efeito já expirou
    public boolean isExpirado() {
        return duracaoTicks <= 0;
    }

    // Decrementa duração do efeito
    public void tick() {
        duracaoTicks--;
    }
}


