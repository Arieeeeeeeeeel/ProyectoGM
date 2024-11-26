package movimientosGotas;

import gotas.Gota;

public abstract class MovimientoGota {
    public void actualizarMov(Gota gota) {
        actualizarX(gota);
    }

    public abstract void actualizarX(Gota gota);
}
