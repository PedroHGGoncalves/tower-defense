package tdgame_3;

// Representa o estado do jogador (base e economia)
public class Player {

    private int vidaBase; 
    private int moedas;   

    // Inicializa a vida da base e moedas iniciais
    public Player(int vidaInicial) {
        this.vidaBase = vidaInicial;
        this.moedas = 50;
    }

    // Reduz a vida da base ao inimigo chegar ao final
    public void reduzirVida(int dano) {
        vidaBase -= dano;
    }

    public int getVidaBase() { return vidaBase; }
    public int getMoedas() { return moedas; }

    // Tenta gastar moedas; retorna sucesso ou falha
    public boolean gastarMoedas(int valor) {
        if (valor <= moedas) {
            moedas -= valor;
            return true;
        }
        return false;
    }

    // Adiciona moedas ao jogador
    public void ganharMoedas(int valor) {
        moedas += valor;
    }

    // Alias para ganho de moedas
    public void adicionarMoedas(int valor) {
        ganharMoedas(valor);
    }
}
