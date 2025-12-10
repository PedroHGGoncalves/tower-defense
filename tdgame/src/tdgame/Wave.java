package tdgame;

//Define os parâmetros de uma onda de 
public class Wave {

    // Número de inimigos da onda
    private int quantidade;

    // Tempo entre cada geração, em ticks
    private int intervalo;

    public Wave(int quantidade, int intervalo) {
        // Armazena os parâmetros da onda
        this.quantidade = quantidade;
        this.intervalo = intervalo;
    }

    // Informa quantos inimigos a onda possui
    public int getQuantidade() {
        return quantidade;
    }

    // Informa o intervalo entre spawns
    public int getIntervalo() {
        return intervalo;
    }
}

