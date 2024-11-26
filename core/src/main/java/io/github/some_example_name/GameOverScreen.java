package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameOverScreen implements Screen {
    private final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo; // Fondo de la pantalla de Game Over
    private Sound gameOverSound; // Efecto de sonido para Game Over
    private GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();

    public GameOverScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("gameover_background.png")); //

        // Cargar el sonido de Game Over
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("gameover_sound.wav"));

        // Configuración de la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameConfig.width, gameConfig.height);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibujar el fondo
        batch.draw(fondo, 0, 0, gameConfig.width, gameConfig.height); // Ajusta el tamaño según sea necesario

        // Dibujar textos
        font.draw(batch, "PERDISTE", 100, 200);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 100);

        batch.end();

        // Reiniciar el juego si se toca la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {
        // Reproducir el sonido de Game Over
        gameOverSound.play();
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
        // Liberar la textura del fondo y el sonido de Game Over
        fondo.dispose();
        gameOverSound.dispose();
    }
}
