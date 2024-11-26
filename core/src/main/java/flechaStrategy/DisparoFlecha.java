package flechaStrategy;

import io.github.some_example_name.Tarro;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import io.github.some_example_name.GameConfigurationSingleton;
import io.github.some_example_name.Lluvia;

public class DisparoFlecha implements DisparoStrategy {
    private Texture texturaFlecha;
	private Array<Flecha> flechas = new Array<Flecha>();
    private GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();

    public DisparoFlecha() {
        this.texturaFlecha = new Texture("Flecha.png");
    }

    public void agregarFlecha(Flecha flecha) {
        flechas.add(flecha);
    }

    @Override
    public void destruir() {
        texturaFlecha.dispose();
    }

    @Override
    public void actualizarDibujo(SpriteBatch batch) {
        for (Flecha flecha : flechas) {
            flecha.dibujar(batch);
        }
    }

    @Override
    public void actualizarMovimiento(Lluvia lluvia) {
        for (Flecha flecha : flechas) {
            flecha.subir();
            if (flecha.y >= gameConfig.height) {
                flechas.removeValue(flecha, true);
            } else if (lluvia.colisionaConFlecha(flecha)) {
                flechas.removeValue(flecha, true);
            }
        }
    }

    @Override
    public void disparar(Tarro tarro) {
        Flecha flecha = new Flecha(texturaFlecha, tarro.getPosicion().x, tarro.getPosicion().y);
        agregarFlecha(flecha);
    }
}