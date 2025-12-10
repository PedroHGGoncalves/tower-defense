package tdgame;

//Estado do jogador
public class Player {

    // Vida atual da base
    private int vidaBase;

    public Player(int vidaInicial) {
        this.vidaBase = vidaInicial;
    }

    // Aplica dano à base
    public void reduzirVida(int dano) {
        vidaBase -= dano;
    }

    // Consulta a vida da base
    public int getVidaBase() {
        return vidaBase;
    }
}


