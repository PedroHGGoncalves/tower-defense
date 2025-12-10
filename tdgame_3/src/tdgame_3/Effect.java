package tdgame_3;

// Representa um efeito temporário aplicado a um inimigo
public class Effect {

    private EffectType tipo;    // tipo do efeito 
    private int duracao;        // duração restante em ticks
    private double valor;       // intensidade do efeito

    // Cria um efeito com tipo, duração e intensidade definidos
    public Effect(EffectType tipo, int duracao, double valor) {
        this.tipo = tipo;
        this.duracao = duracao;
        this.valor = valor;
    }

    // Acessores do estado do efeito
    public EffectType getTipo() { return tipo; }
    public int getDuracao() { return duracao; }
    public double getValor() { return valor; }

    // Avança o tempo de vida do efeito
    public void tick() { duracao--; }

    // Indica se o efeito já expirou
    public boolean terminou() { return duracao <= 0; }
}

