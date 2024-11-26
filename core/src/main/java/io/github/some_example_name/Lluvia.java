package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import factorias.*;
import flechaStrategy.Flecha;
import gotas.Gota;
import gotas.GotaMala;

public class Lluvia {
    private Array<Gota> gotas; // Usamos Array<Gota> para almacenar las gotas
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Sound dropSound;
    private Music rainMusic;
    private GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();
    private GotaFactory factory;

    public Lluvia(Texture gotaBuena, Texture gotaMala, Sound dropSound, Music rainMusic) {
        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
    }

    public void crear() {
        gotas = new Array<>();
        crearGotaDeLluvia(1);


        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearGotaDeLluvia(int dificultad) {
        float x = MathUtils.random(0, gameConfig.getWidth() - 64);
        float y = gameConfig.getHeight();
        float velocidad = 300 * Gdx.graphics.getDeltaTime();

        setFactory(dificultad);

        Gota nuevaGota;
        if (MathUtils.random(1, 10) < 5) {
            nuevaGota = factory.crearBuena(gotaBuena, x, y, velocidad);
        } else {
            nuevaGota = factory.crearMala(gotaMala, x, y, velocidad);
        }
        gotas.add(nuevaGota);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void setFactory(int dificultad) {
        if (dificultad == 1) {
            factory = new DificultadFacil();
        } else if (dificultad == 2) {
            factory = new DificultadMedia();
        } else if (dificultad == 3) {
            factory = new DificultadDificil();
        } else {
            factory = new DificultadExtremo();
        }
    }

    public boolean actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            crearGotaDeLluvia(tarro.dificultad());
        }

        for (int i = 0; i < gotas.size; i++) {
            Gota gota = gotas.get(i);
            int option = gota.cicloDeVida(tarro);

            if (option == 0) { // La gota cae al suelo
                gotas.removeIndex(i);
            } else if (option == 1){ // La gota choca con el tarro
                dropSound.play();
                gotas.removeIndex(i);

                if (tarro.getVidas() <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean colisionaConFlecha(Flecha flecha) {
        for (int i = 0; i < gotas.size; i++) {
            Gota gota = gotas.get(i);
            if (gota.getArea().overlaps(flecha.getArea())) {
                if (gota instanceof GotaMala) {
                    gotas.removeIndex(i);
                    return true;
                }
            }
        }
        return false;
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
