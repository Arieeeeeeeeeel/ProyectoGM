package factorias;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import gotas.Gota;
import gotas.GotaBuena;
import gotas.GotaMala;
import movimientosGotas.CaidaDiagonal;
import movimientosGotas.CaidaRecta;
import movimientosGotas.CaidaZigZag;
import movimientosGotas.MovimientoGota;
import io.github.some_example_name.Tarro;
import java.util.ArrayList;

public class DificultadExtremo implements GotaFactory {

    private ArrayList<MovimientoGota> movimientos;
    public DificultadExtremo() {
        movimientos = new ArrayList<>();
        movimientos.add(new CaidaRecta());
        movimientos.add(new CaidaDiagonal());
        movimientos.add(new CaidaZigZag());
    }

    @Override
    public Gota crearBuena(Texture textura, float x, float y, float velocidad) {
        MovimientoGota movimiento = seleccionarMovimientoAleatorio();
        return new GotaBuena(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.sumarPuntos(5);
            }
        };
    }

    @Override
    public Gota crearMala(Texture textura, float x, float y, float velocidad) {
        MovimientoGota movimiento = seleccionarMovimientoAleatorio();
        return new GotaMala(textura, x, y, velocidad, movimiento) {
            @Override
            public void efecto(Tarro tarro) {
                tarro.dañar(3);
            }
        };
    }

    private MovimientoGota seleccionarMovimientoAleatorio() {
        int index = MathUtils.random(0, movimientos.size() - 1);
        return movimientos.get(index);
    }
}
