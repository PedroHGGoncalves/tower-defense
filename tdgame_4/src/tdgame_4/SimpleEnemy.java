package tdgame_4;

//Inimigo de atributos medianos
public class SimpleEnemy extends Enemy {
    public SimpleEnemy(Path caminho) {
        super(caminho);
        this.vida = 50;
        this.velocidade = 4.3;
        this.dano = 2;
        this.recompensa = 5;
        this.cor = java.awt.Color.RED;
    }

    @Override
    protected int getMaxVida() { return 50; }
}

