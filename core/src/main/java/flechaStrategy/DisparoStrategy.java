package flechaStrategy;

import io.github.some_example_name.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface DisparoStrategy {
    void disparar(Tarro tarro);
    void destruir();
    void actualizarMovimiento(Lluvia lluvia);
    void actualizarDibujo(SpriteBatch batch);
}
