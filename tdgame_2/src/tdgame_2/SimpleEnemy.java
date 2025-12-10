package tdgame_2;

public class SimpleEnemy extends Enemy {

    public SimpleEnemy(Path caminho) {
        super(caminho);      // Inicializa posição pelo caminho

        vida = 10;           // Vida base do inimigo
        velocidade = 4.0;    // Movimento rápido
        recompensa = 3;      // Moedas ao ser derrotado
    }
}
