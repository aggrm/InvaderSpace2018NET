/*
 * La clase que sirve para dibuajar los disparos de la nave en la pantalla
 */
package codigo;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author xp
 */
public class Disparo {
    public Image imagen = null;
    private int x = 0;
    private int y = 0;
    private int altoPantalla;
    private boolean disparo = false;
    
    
    public Disparo (int _altoPantalla)
    {
        try
        {
            imagen = ImageIO.read(getClass().getResource("/imagenes/disparo.png"));
        }
        catch (IOException e) {}
        altoPantalla = _altoPantalla; 
    }

    public void mueve()
    {
        if(y > 0)
        {
            y-= 10;
        }
    }
    
    public void posicionaDisparo(Nave _nave)
    {
        x = _nave.x + _nave.imagen.getWidth(null)/2 - imagen.getWidth(null)/2;
        y = _nave.y;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isDisparo() {
        return disparo;
    }

    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }
}
