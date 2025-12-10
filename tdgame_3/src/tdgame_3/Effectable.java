package tdgame_3;

// Contrato para entidades que podem receber efeitos de status
public interface Effectable {

    // Aplica um efeito com tipo, duração e intensidade
    void aplicarEfeito(EffectType efeito, int duracao, double valor);
}

