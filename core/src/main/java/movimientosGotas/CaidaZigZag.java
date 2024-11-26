package movimientosGotas;

import com.badlogic.gdx.Gdx;
import gotas.Gota;
import io.github.some_example_name.GameScreen;

public class CaidaZigZag extends MovimientoGota {

    @Override
    public void actualizarX(Gota gota) {
        if (gota.getX() > (GameScreen.getAnchoCam()-64) ||
            gota.getX() < 0) {
            gota.setVelocidadX( gota.getVelocidadX() * -1 );
        }
        gota.setX(gota.getX() + (float) (gota.getVelocidadX() * Math.sin(Math.toRadians(gota.getY()))));
    }
}
