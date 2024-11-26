package flechaStrategy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import io.github.some_example_name.Item;

public class Flecha implements Item {
    protected Texture textura;
    protected float x;
    public float y;
    protected float velocidad = 5;

    public Flecha(Texture textura, float x, float y) {
        this.textura = textura;
        this.x = x;
        this.y = y;
    }

    // Método para actualizar la posición de la flecha
    public void subir() {
        y += velocidad;
    }

    // Método para obtener el área de la gota como un rectángulo
    public Rectangle getArea() {
        return new Rectangle(x, y, textura.getWidth(), textura.getHeight());
    }

    // Método para dibujar la flecha
    public void dibujar(SpriteBatch batch) {
        batch.draw(textura, x+25, y);
    }

    // Liberar recursos
    public void destruir() {
        textura.dispose();
    }
}
