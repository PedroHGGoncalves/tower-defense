package tdgame_3;

// Inimigo lento e com muita vida
public class TankEnemy extends Enemy {
	
	//incializa atributos
    public TankEnemy(Path caminho) {
        super(caminho);    // posiciona no início do caminho
        this.vida = 100;      
        this.velocidade = 2.0; 
        this.recompensa = 8;   
        this.cor = java.awt.Color.GREEN; 
        this.dano = 5;
    }
}



