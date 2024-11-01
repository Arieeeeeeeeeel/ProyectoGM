package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import gotas.Gota;
import gotas.GotaBuena;
import gotas.GotaMala;

public class Lluvia {
    private Array<Gota> gotas; // Usamos Array<Gota> para almacenar las gotas
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
    }

    public void crear() {
        gotas = new Array<>();
        crearGotaDeLluvia();

        // Iniciar la música de fondo en bucle
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia() {
        float x = MathUtils.random(0, 800 - 64);
        float y = 480;
        float velocidad = 300 * Gdx.graphics.getDeltaTime();

        // Determinamos el tipo de gota aleatoriamente
        Gota nuevaGota;
        if (MathUtils.random(1, 10) < 5) { // 50% de probabilidad
            nuevaGota = new GotaMala(gotaMala, x, y, velocidad);
        } else {
            nuevaGota = new GotaBuena(gotaBuena, x, y, velocidad);
        }
        gotas.add(nuevaGota);
        lastDropTime = TimeUtils.nanoTime();
    }

    public boolean actualizarMovimiento(Tarro tarro) {
        // Generar nuevas gotas
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            crearGotaDeLluvia();
        }

        // Revisar el movimiento de cada gota y si colisiona con el tarro
        for (int i = 0; i < gotas.size; i++) {
            Gota gota = gotas.get(i);
            gota.caer();

            if (gota.y < 0) { // La gota cae al suelo
                gotas.removeIndex(i);
            } else if (gota.getArea().overlaps(tarro.getArea())) { // La gota choca con el tarro
                if (gota instanceof Recolectable) { // Verificar si es Recolectable
                    ((Recolectable) gota).efecto(tarro); // Aplicar efecto en el tarro
                }
                dropSound.play();
                gotas.removeIndex(i);

                // Verificamos si el tarro se queda sin vidas
                if (tarro.getVidas() <= 0) {
                    return false; // Si no tiene vidas, game over
                }
            }
        }
        return true;
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (Gota gota : gotas) {
            gota.dibujar(batch);
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    }
}
