package tdgame_3;

// Inimigo base
public class SimpleEnemy extends Enemy {

    // Inicializa atributos padrão
    public SimpleEnemy(Path caminho) {
        super(caminho);           
        this.vida = 50;           
        this.velocidade = 4.0;    
        this.dano = 2;           
        this.recompensa = 5;     
        this.cor = java.awt.Color.RED; 
    }
}



