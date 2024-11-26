package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import flechaStrategy.*;

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Tarro tarro;
    private Lluvia lluvia;
    private Texture fondo;
    private static float anchoCam;
    private DisparoStrategy disparoStrategy;
    private GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        anchoCam = game.getCam().viewportWidth;

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("background.png"));

        // Cargar el resto de las texturas y sonidos
        Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        tarro = new Tarro(new Texture(Gdx.files.internal("bucket.png")), hurtSound);

        Texture gota = new Texture(Gdx.files.internal("drop.png"));
        Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));

        Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameConfig.width, gameConfig.height);
        batch = new SpriteBatch();

        // Creación del tarro y la lluvia
        tarro.crear();
        lluvia.crear();
        disparoStrategy = new DisparoFlecha();
    }

    public static float getAnchoCam() {
        return anchoCam;
    }

    @Override
    public void render(float delta) {
        // Detecta si el jugador presiona la tecla ESC para pausar el juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause();
            return;
        }

        // Limpiar la pantalla con un color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Comenzar a dibujar
        batch.begin();
        batch.draw(fondo, 0, 0, gameConfig.width, gameConfig.height);

        // Dibujar textos
        font.draw(batch, "Diamantes totales: " + tarro.getPuntos(), 5, gameConfig.height - 5);
        font.draw(batch, "Vidas : " + tarro.getVidas(), camera.viewportWidth / 2, gameConfig.height - 5);
        font.draw(batch, "HighScore : " + game.getHigherScore(), gameConfig.width - 200, gameConfig.height - 5);

        if (!tarro.estaHerido()) {
            // Movimiento del tarro desde teclado
            tarro.actualizarMovimiento(disparoStrategy);
            disparoStrategy.actualizarMovimiento(lluvia);

            // Caída de la lluvia
            if (!lluvia.actualizarMovimiento(tarro)) {
                if (game.getHigherScore() < tarro.getPuntos())
                    game.setHigherScore(tarro.getPuntos());
                game.setScreen(new GameOverScreen(game));
                dispose();
            }

        }

        // Dibujar el tarro, la lluvia y las flechas
        disparoStrategy.actualizarDibujo(batch);
        tarro.dibujar(batch);
        lluvia.actualizarDibujoLluvia(batch);

        // Finalizar dibujo
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // Continuar con sonido de lluvia
        lluvia.continuar();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        lluvia.pausar();
        game.setScreen(new PausaScreen(game, this));
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        // Liberar recursos de las texturas y sonidos
        fondo.dispose();
        tarro.destruir();
        lluvia.destruir();
        disparoStrategy.destruir();
    }
}
