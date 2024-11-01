package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PausaScreen implements Screen {

    private final GameLluviaMenu game;
    private GameScreen juego;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo; // Fondo para la pantalla de pausa
    private Sound pauseSound; // Efecto de sonido para la pausa

    public PausaScreen(final GameLluviaMenu game, GameScreen juego) {
        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("brb.png")); // Asegúrate de que "pause_background.png" esté en assets

        // Cargar el sonido de pausa
        pauseSound = Gdx.audio.newSound(Gdx.files.internal("pause_sound.wav")); // Asegúrate de que "pause_sound.wav" esté en assets

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar el fondo
        batch.draw(fondo, 0, 0, 800, 480); // Ajusta el tamaño según sea necesario para que ocupe toda la pantalla

        // Dibujar textos
        font.draw(batch, "Juego en Pausa ", 100, 150);
        font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);

        batch.end();

        // Retomar el juego si se toca la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(juego);
            dispose();
        }
    }

    @Override
    public void show() {
        // Reproducir el sonido de pausa
        pauseSound.play();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        // Liberar la textura del fondo y el sonido de pausa
        fondo.dispose();
        pauseSound.dispose();
    }
}