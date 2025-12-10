package tdgame;

//Inimigo simples
public class SimpleEnemy extends Enemy {

    public SimpleEnemy(Path caminho) {
        // Inicializa o inimigo no caminho definido pela superclasse
        super(caminho);

        // Define atributos específicos desse inimigo
        this.vida = 5;
        this.velocidade = 2.0;
    }
}

