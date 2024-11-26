package factorias;

import com.badlogic.gdx.graphics.Texture;
import gotas.Gota;
import gotas.GotaBuena;
import gotas.GotaMala;
import movimientosGotas.CaidaZigZag;
import movimientosGotas.MovimientoGota;
import io.github.some_example_name.Tarro;

public class DificultadDificil implements GotaFactory {
    private MovimientoGota movimiento;

    public DificultadDificil() {
        this.movimiento = new CaidaZigZag();
    }

    @Override
    public Gota crearBuena(Texture textura, float x, float y, float velocidad) {
        return new GotaBuena(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.sumarPuntos(5);
            }
        };
    }

    @Override
    public Gota crearMala(Texture textura, float x, float y, float velocidad) {
        return new GotaMala(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.dañar(3);
            }
        };
    }
}
