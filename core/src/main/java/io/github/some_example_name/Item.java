package io.github.some_example_name;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Item {
    Rectangle getArea();
    void dibujar(SpriteBatch batch);
    void destruir();
}
