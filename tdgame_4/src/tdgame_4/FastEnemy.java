package tdgame_4;

//Inimigo rápido com menos vida
public class FastEnemy extends Enemy {
    public FastEnemy(Path caminho) {
        super(caminho);
        this.vida = 30;
        this.velocidade = 6.0;
        this.recompensa = 6;
        this.cor = java.awt.Color.BLUE;
        this.dano = 1;
    }

    @Override
    protected int getMaxVida() {
    	return 30; 
    	}
}

