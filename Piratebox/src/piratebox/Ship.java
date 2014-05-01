/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratebox;

/**
 *
 * @author David
 */
import java.awt.Image;
import java.awt.Toolkit;

public class Ship extends Base {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Base</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto ship.
     * @param posY es el <code>posiscion en y</code> del objeto ship.
     * @param image es la <code>imagen</code> del objeto ship.
     */
    private boolean llega;

    public void setllega(boolean a) {
        llega = a;
    }

    public boolean getllega() {
        return llega;
    }

    public Ship(int posX, int posY, int casu) {
        super(posX, posY, casu);
        Image ship1;
        ship1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Imagenes/pirateship.png"));
        anim = new Animacion();
        anim.sumaCuadro(ship1, 100);

    }
}
