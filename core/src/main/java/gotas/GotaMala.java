package gotas;

import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Tarro;
import movimientosGotas.MovimientoGota;

public class GotaMala extends Gota {
    public GotaMala(Texture textura, float x, float y, float velocidad, MovimientoGota movimiento) {
        super(textura, x, y, velocidad, movimiento);
    }

    @Override
    public void efecto(Tarro tarro) {
        tarro.dañar(1);
    }
}
