package gotas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import movimientosGotas.MovimientoGota;
import io.github.some_example_name.Item;
import io.github.some_example_name.Tarro;

public abstract class Gota implements Item {
    protected Texture textura;
    protected float x;
    public float y;
    protected float velocidad;
    protected MovimientoGota movimiento;

    public Gota(Texture textura, float x, float y, float velocidad, MovimientoGota movimiento) {
        this.textura = textura;
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.movimiento = movimiento;
    }

    // Método abstracto que será implementado por las subclases
    public abstract void efecto(Tarro tarro);

    // Método para actualizar la posición de la gota
    public void caer() {
        y -= velocidad;
    }

    // Método para obtener el área de la gota como un rectángulo
    public Rectangle getArea() {
        return new Rectangle(x, y, textura.getWidth(), textura.getHeight());
    }

    // Método para dibujar la gota
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, x, y);
    }

    // Liberar recursos
    public void destruir() {
        textura.dispose();
    }
}
