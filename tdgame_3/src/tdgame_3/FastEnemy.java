package tdgame_3;

// Inimigo rápido e com pouca vida
public class FastEnemy extends Enemy {

    // Inicializa atributos
    public FastEnemy(Path caminho) {
        super(caminho);    // posiciona no início do caminho
        this.vida = 30;               
        this.velocidade = 6.0;        
        this.recompensa = 6;          
        this.cor = java.awt.Color.BLUE; 
        this.dano = 1;                
    }
}




