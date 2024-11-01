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

public class GameScreen implements Screen {
    final GameLluviaMenu game;
    private OrthographicCamera camera;
    private SpriteBatch batch;	   
    private BitmapFont font;
    private Tarro tarro;
    private Lluvia lluvia;
    private Texture fondo; // Añadimos la textura para el fondo
	   
    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        // Cargar la textura del fondo
        fondo = new Texture(Gdx.files.internal("background.png")); // Asegúrate de que "background.png" esté en la carpeta assets
		
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
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        // Creación del tarro y la lluvia
        tarro.crear();
        lluvia.crear();
    }

    @Override
    public void render(float delta) {
        // Detecta si el jugador presiona la tecla ESC para pausar el juego
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pause();
            return; // Evitar que continúe el renderizado cuando se pausa
        }

        // Limpiar la pantalla con un color azul oscuro
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualizar la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Comenzar a dibujar
        batch.begin();
		
        // Dibujar el fondo (esto debe ir primero para que el fondo quede atrás de los demás elementos)
        batch.draw(fondo, 0, 0, 800, 480); // Ajusta el tamaño si tu ventana tiene otras dimensiones

        // Dibujar textos
        font.draw(batch, "Diamantes totales: " + tarro.getPuntos(), 5, 475);
        font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
        font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth / 2 - 50, 475);

        if (!tarro.estaHerido()) {
            // Movimiento del tarro desde teclado
            tarro.actualizarMovimiento();
	        
            // Caída de la lluvia
            if (!lluvia.actualizarMovimiento(tarro)) {
                if (game.getHigherScore() < tarro.getPuntos())
                    game.setHigherScore(tarro.getPuntos());
                game.setScreen(new GameOverScreen(game));
                dispose();
            }
        }
		
        // Dibujar el tarro y la lluvia
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
        fondo.dispose(); // Liberar la textura del fondo
        tarro.destruir();
        lluvia.destruir();
    }
}