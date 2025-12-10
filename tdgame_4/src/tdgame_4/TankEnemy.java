package tdgame_4;

//Inimigo com mais vida, mais lento, da mais dano e é imune a slow
public class TankEnemy extends Enemy {
    public TankEnemy(Path caminho) {
        super(caminho);
        this.vida = 100;
        this.velocidade = 3.8;
        this.recompensa = 8;
        this.cor = java.awt.Color.GREEN;
        this.dano = 5;
    }

    @Override
    protected int getMaxVida() { return 100; }
}

