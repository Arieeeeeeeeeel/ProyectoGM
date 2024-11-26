package gotas;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Tarro;

public class GotaMala extends Gota {
    public GotaMala(Texture textura, float x, float y, float velocidad) {
        super(textura, x, y, velocidad);
    }

    @Override
    public void efecto(Tarro tarro) {
        tarro.dañar();
    }
}
