package factorias;

import com.badlogic.gdx.graphics.Texture;
import gotas.Gota;

public interface GotaFactory {
    public Gota crearBuena(Texture textura, float x, float y, float velocidad);
    public Gota crearMala(Texture textura, float x, float y, float velocidad);
}
