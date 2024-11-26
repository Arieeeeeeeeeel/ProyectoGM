package factorias;

import com.badlogic.gdx.graphics.Texture;
import gotas.Gota;
import gotas.GotaBuena;
import gotas.GotaMala;
import movimientosGotas.CaidaRecta;
import movimientosGotas.MovimientoGota;

public class DificultadFacil implements GotaFactory {
    private MovimientoGota movimiento;

    public DificultadFacil() {
        this.movimiento = new CaidaRecta();
    }

    @Override
    public Gota crearBuena(Texture textura, float x, float y, float velocidad) {
        return new GotaBuena(textura, x, y, velocidad, movimiento);
    }

    @Override
    public Gota crearMala(Texture textura, float x, float y, float velocidad) {
        return new GotaMala(textura, x, y, velocidad, movimiento);
    }
}
