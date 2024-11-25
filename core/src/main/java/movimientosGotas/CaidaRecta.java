package movimientosGotas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CaidaRecta extends MovimientoGota {
    @Override
    public void actualizarY(int velY, Rectangle gota) {
        gota.y -=  velY * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void actualizarX(int velX, Rectangle gota) {

    }
}
