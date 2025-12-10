package tdgame_2;

public class Wave {     // define uma onda de inimigos

    private int quantidade;    // total de inimigos da onda
    private int intervalo;    // tempo entre spawns (ticks)

    public Wave(int quantidade, int intervalo) {
        this.quantidade = quantidade;   // salva quantidade
        this.intervalo = intervalo;     // salva intervalo
    }

    public int getQuantidade() { // acesso à quantidade
        return quantidade;
    }

    public int getIntervalo() {  // acesso ao intervalo
        return intervalo;
    }
}



