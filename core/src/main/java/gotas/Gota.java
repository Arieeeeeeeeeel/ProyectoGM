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
    protected float y;
    protected float velocidadY;
    protected float velocidadX;
    protected MovimientoGota movimiento;

    public Gota(Texture textura, float x, float y, float velocidad, MovimientoGota movimiento) {
        this.textura = textura;
        this.x = x;
        this.y = y;
        this.velocidadX = velocidad;
        this.velocidadY = velocidad;
        this.movimiento = movimiento;
    }

    // Template Method
    public final int cicloDeVida(Tarro tarro) {
        caer();
        if (y < 0) { // La gota cae al suelo
            return 0;
        } else if (colisionaCon(tarro)) {
            efecto(tarro);
            return 1;
        }
        return 2;
    }

    // Método abstracto que será implementado por las subclases
    public abstract void efecto(Tarro tarro);

    // Método para actualizar la posición de la gota
    public void caer() {
        y -= velocidadY;
        movimiento.actualizarMov(this);
    }

    protected boolean colisionaCon(Tarro tarro) {
        return getArea().overlaps(tarro.getArea());
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

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(float velocidadY) {
        this.velocidadY = velocidadY;
    }

    public float getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(float velocidadX) {
        this.velocidadX = velocidadX;
    }

    public MovimientoGota getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoGota movimiento) {
        this.movimiento = movimiento;
    }

    

}
