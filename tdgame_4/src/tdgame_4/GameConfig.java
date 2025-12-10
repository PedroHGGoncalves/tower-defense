package tdgame_4;

import java.util.List;

// Configurações iniciais do jogo
public class GameConfig {

    public final long seed;              // semente para geração aleatória
    public final int startingCoins;      // moedas iniciais do jogador
    public final int startingHealth;     // vida inicial da base
    public final List<Wave> waves;       // ondas do jogo

    public GameConfig(long seed, int startingCoins, int startingHealth, List<Wave> waves) {
        this.seed = seed;
        this.startingCoins = startingCoins;
        this.startingHealth = startingHealth;
        this.waves = waves;
    }
}


