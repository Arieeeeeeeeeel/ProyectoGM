package movimientosGotas;

import com.badlogic.gdx.Gdx;
import gotas.Gota;
import io.github.some_example_name.GameConfigurationSingleton;

public class CaidaDiagonal extends MovimientoGota {
    GameConfigurationSingleton gameConfig = GameConfigurationSingleton.getInstance();

    @Override
    public void actualizarX(Gota gota) {
        if (gota.getX() > (gameConfig.getWidth()-64) ||
            gota.getX() < 0) {
            gota.setVelocidadX( gota.getVelocidadX() * -1 );
        }
        gota.setX( gota.getX() + gota.getVelocidadX() );
    }
}
