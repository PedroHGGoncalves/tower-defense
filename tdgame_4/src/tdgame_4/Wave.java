package tdgame_4;

//Representa uma onda de inimigos: quantidade e intervalo entre spawn.
public class Wave {
    private int quantidade; // Número de inimigos na onda
    private int intervalo;  // Intervalo entre spawn de inimigos

    public Wave(int quantidade, int intervalo) {
        this.quantidade = quantidade; 
        this.intervalo = intervalo;   
    }

    public int getQuantidade() { return quantidade; } 

    public int getIntervalo() { return intervalo; }   
}


