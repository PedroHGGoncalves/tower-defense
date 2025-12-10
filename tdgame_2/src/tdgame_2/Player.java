package tdgame_2;

public class Player {

    private int vidaBase;

    // Moedas para construir torres
    private int moedas;

    public Player(int vidaInicial) {
        vidaBase = vidaInicial; // Define vida inicial da base
        moedas = 50;            // Moedas iniciais do jogador
    }

    public void reduzirVida(int dano) {
        vidaBase -= dano; // Aplica dano à base
    }

    public int getVidaBase() {
        return vidaBase; // Consulta vida atual
    }

    public int getMoedas() {
        return moedas; // Consulta saldo
    }

    public boolean gastarMoedas(int valor) {
        if (valor <= moedas) { // Verifica saldo
            moedas -= valor;   // Debita valor
            return true;       // Compra permitida
        }
        return false;          // Saldo insuficiente
    }

    public void ganharMoedas(int valor) {
        moedas += valor; // Credita recompensa
    }

    public void adicionarMoedas(int valor) {
        // Mantido como alias por clareza sem duplicar lógica
        ganharMoedas(valor);
    }
}



