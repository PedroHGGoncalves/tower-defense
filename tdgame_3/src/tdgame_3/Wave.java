package tdgame_3;

// Representa uma onda de inimigos: quantidade e intervalo de spawn
public class Wave {

    private int quantidade; // número de inimigos na onda
    private int intervalo;  // ticks entre spawns

    public Wave(int quantidade, int intervalo) {
        this.quantidade = quantidade;
        this.intervalo = intervalo;
    }

    public int getQuantidade() { return quantidade; }
    public int getIntervalo() { return intervalo; }
}


