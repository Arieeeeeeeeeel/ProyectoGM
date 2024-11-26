package factorias;

import com.badlogic.gdx.graphics.Texture;
import gotas.Gota;
import gotas.GotaBuena;
import gotas.GotaMala;
import movimientosGotas.CaidaDiagonal;
import movimientosGotas.MovimientoGota;
import io.github.some_example_name.Tarro;

public class DificultadMedia implements GotaFactory {
    private MovimientoGota movimiento;

    public DificultadMedia() {
        this.movimiento = new CaidaDiagonal();
    }

    @Override
    public Gota crearBuena(Texture textura, float x, float y, float velocidad) {
        return new GotaBuena(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.sumarPuntos(7);
            }
        };
    }

    @Override
    public Gota crearMala(Texture textura, float x, float y, float velocidad) {
        return new GotaMala(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.dañar(2);
            }
        };
    }
}
