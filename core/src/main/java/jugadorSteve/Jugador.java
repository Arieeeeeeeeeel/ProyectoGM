package jugadorSteve;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public final class Jugador {
    private static Jugador instance;
    private int vidas;
    private int ptj;
    private Steve pj;


    private Jugador(Texture tex, Sound ss) {
        pj = Steve.getSteve(); 
        vidas = 3;
        ptj = 0;
    }

    public static Jugador getJugador() {
        if (instance == null) {
            instance = new Jugador(new Texture(Gdx.files.internal("bucket.png")), Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")));
        }
        return instance;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPtj() {
        return ptj;
    }

    public void sumarPuntos(int puntos) {
        ptj += puntos;
    }
}
