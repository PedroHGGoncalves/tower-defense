package tdgame_3;

import java.awt.Graphics;
import java.util.ArrayList;

// Gerencia todos os projéteis ativos no jogo
public class ProjectileManager {

    private ArrayList<Projectile> projeteis; // projéteis atualmente em voo
    private WaveManager waveManager;          // referência ao controle de inimigos

    // Inicializa o gerente de projéteis
    public ProjectileManager(WaveManager waveManager) {
        this.projeteis = new ArrayList<>();
        this.waveManager = waveManager;
    }

    // Adiciona um novo projétil ao jogo
    public void spawn(Projectile p) {
        projeteis.add(p);
    }

    // Atualiza todos os projéteis e remove os que já colidiram
    public void update(Player player) {
        ArrayList<Projectile> remover = new ArrayList<>();

        for (Projectile p : projeteis) {
            p.update();
            if (p.isRemover()) remover.add(p);
        }

        projeteis.removeAll(remover);
    }

    // Desenha todos os projéteis ativos
    public void desenhar(Graphics g) {
        for (Projectile p : projeteis) {
            p.desenhar(g);
        }
    }
}

