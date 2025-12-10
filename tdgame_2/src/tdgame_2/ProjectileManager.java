package tdgame_2;

import java.util.ArrayList;
import java.awt.Graphics;

public class ProjectileManager {

    // Lista de projéteis ativos
    private ArrayList<Projectile> projeteis;

    // Acesso aos inimigos ativos
    private WaveManager waveManager;

    public ProjectileManager(WaveManager waveManager) {
        projeteis = new ArrayList<>();
        this.waveManager = waveManager;
    }

    public void spawn(Projectile p) {
        // Registra projétil recém-criado
        projeteis.add(p);
    }

    public void update(Player player) {

        // Armazena projéteis que devem sair do jogo
        ArrayList<Projectile> remover = new ArrayList<>();

        for (Projectile p : projeteis) {

            // Atualiza movimento e estado
            p.update();

            // Projétil inválido
            if (p.isRemover()) {
                remover.add(p);
                continue;
            }

            // Testa colisão com inimigos ativos
            for (Enemy e : waveManager.getInimigos()) {

                if (!e.isVivo()) continue; // ignora inimigos mortos

                // Distância projétil → inimigo
                double dx = e.getX() - p.getX();
                double dy = e.getY() - p.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);

                // Colisão aproximada
                if (dist < Map.TILE_SIZE * 0.6) {

                    // Aplica dano
                    e.receberDano(p.getDano());

                    // Projétil cumpre seu papel
                    p.setRemover(true);

                    break; // evita múltiplos acertos
                }
            }
        }

        // Remove projéteis descartados
        projeteis.removeAll(remover);
    }

    public void desenhar(Graphics g) {
        // Renderiza todos os projéteis ativos
        for (Projectile p : projeteis) {
            p.desenhar(g);
        }
    }
}

