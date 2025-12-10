package tdgame_4;

public class Player {
    private int vidaBase; 
    private int moedas;   

    public Player(int vidaInicial, int moedasIniciais) {
        this.vidaBase = vidaInicial; 
        this.moedas = moedasIniciais; 
    }

    public void reduzirVida(int dano) { 
        vidaBase -= dano; // Reduz vida da base
    }

    public int getVidaBase() { 
        return vidaBase; // Retorna vida atual
    }

    public int getMoedas() { 
        return moedas; // Retorna moedas atuais
    }

    public boolean gastarMoedas(int valor) {
        if (valor <= moedas) {
            moedas -= valor; // Deduz moedas se possível
            return true;     // Compra permitida
        }
        return false;        // Moedas insuficientes
    }

    public void ganharMoedas(int valor) { 
        moedas += valor; // Adiciona moedas
    }

    public void adicionarMoedas(int valor) { 
        ganharMoedas(valor); // Alias para ganharMoedas
    }
}
