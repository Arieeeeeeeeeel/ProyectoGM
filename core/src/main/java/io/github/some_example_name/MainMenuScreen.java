package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

    final GameLluviaMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture backgroundTexture; // Nueva textura para el fondo
    private GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, gameConfig.width, gameConfig.height);

        // Cargar la textura del fondo
        backgroundTexture = new Texture(Gdx.files.internal("background_menu.png")); // Asegúrate de que el archivo esté en los assets
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        // Dibujar el fondo
        batch.draw(backgroundTexture, 0, 0, gameConfig.width, gameConfig.height);

        // Dibujar textos
        font.getData().setScale(2, 2);
        font.draw(batch, "Bienvenido a MineFall", 100, camera.viewportHeight / 2 + 50);
        font.draw(batch, "Toca en cualquier lugar para comenzar.", 100, camera.viewportHeight / 2 - 50);

        batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {
        // Nada especial en el método show
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
        // Liberar recursos del fondo
        backgroundTexture.dispose();
    }
}
