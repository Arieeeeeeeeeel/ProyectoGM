package movimientosGotas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.some_example_name.GameScreen;

public class CaidaDiagonal extends MovimientoGota {
    @Override
    public void actualizarX(int velX, Rectangle gota) {
        if (gota.x > (GameScreen.getAnchoCam()-64) ||
            gota.x < 0) {
            velX *= -1;
        }
        gota.x += velX * Gdx.graphics.getDeltaTime();
    }
}
