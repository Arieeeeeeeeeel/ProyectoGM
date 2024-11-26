package movimientosGotas;

import com.badlogic.gdx.math.Rectangle;

public abstract class MovimientoGota {
    public void actualizarMov(int velX, Rectangle gota) {
        actualizarX(velX, gota);
    }

    public abstract void actualizarX(int velX, Rectangle gota);
}
