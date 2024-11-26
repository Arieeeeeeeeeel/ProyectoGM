package gotas;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Tarro;

public class GotaBuena extends Gota {
    public GotaBuena(Texture textura, float x, float y, float velocidad) {
        super(textura, x, y, velocidad);
    }

    @Override
    public void efecto(Tarro tarro) {
        tarro.sumarPuntos(1);
    }
}
