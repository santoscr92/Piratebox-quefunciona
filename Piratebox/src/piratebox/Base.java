package piratebox;

/**
 * Clase Animal 
 *
 * @author Antonio Mejorado
 * @version 1.00 2008/6/13
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Base {
	
	private int posX;    //posicion en x.       
	private int posY;	//posicion en y.
        private int flechitas;
	private ImageIcon icono;    //icono.
        boolean move; 
        
        protected Animacion anim;
	
	/**
	 * Metodo constructor usado para crear el objeto
	 * @param posX es la <code>posicion en x</code> del objeto.
	 * @param posY es la <code>posicion en y</code> del objeto.
         * @param flechitas es la <code>caso flechas</code>del objeto.
	 * @param image es la <code>imagen</code> del objeto.
	 */
	public Base(int posX, int posY, int flechitas) {
		this.posX=posX;
		this.posY=posY;
                this.flechitas=flechitas;
		//icono = new ImageIcon(image);
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en x del objeto 
	 * @param posX es la <code>posicion en x</code> del objeto.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	/**
	 * Metodo de acceso que regresa la posicion en x del objeto 
	 * @return posX es la <code>posicion en x</code> del objeto.
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Metodo modificador usado para cambiar la posicion en y del objeto 
	 * @param posY es la <code>posicion en y</code> del objeto.
	 */
         
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	/**
	 * Metodo de acceso que regresa la posicion en y del objeto 
	 * @return posY es la <code>posicion en y</code> del objeto.
	 */
	public int getPosY() {
		return posY;
	}
        
        /**
         * Metodo modificador usadp para cambiar la posicon 
         * @param flechitas es la <code>caso flechas</code> del objeto.
         */
        public void setFlechitas(int flechitas){
            this.flechitas=flechitas;
        }
        
        /**
         * Metodo de acceso que regresa la posicion en y del objeto
         * @return flechitas es la <code>flechitas</code> del objeto.
         */
        public int getFlechitas(){
            return flechitas;
        }

	/**
	 * Metodo modificador usado para cambiar el icono del objeto 
	 * @param icono es el <code>icono</code> del objeto.
	 */
	//id setImageIcon(ImageIcon icono) {
	//	this.anim = icono;
	//}
	
	/**
	 * Metodo de acceso que regresa el icono del objeto 
	 * @return icono es el <code>icono</code> del objeto.
	 */
	//public ImageIcon getImageIcon() {
	//	return icono;
	//}
	
	/**
	 * Metodo de acceso que regresa el ancho del icono 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el ancho del icono.
	 */
	public int getAncho() {
		return ((new ImageIcon (anim.getImagen()).getIconWidth()));
                // return (new ImageIcon(animacion.getImagen()).getIconWidth();
	}
	
	/**
	 * Metodo de acceso que regresa el alto del icono 
	 * @return un objeto de la clase <code>ImageIcon</code> que es el alto del icono.
	 */
	public int getAlto() {
		return ((new ImageIcon (anim.getImagen()).getIconHeight()));
	}
	
	/**
	 * Metodo de acceso que regresa la imagen del icono 
	 * @return un objeto de la clase <code>Image</code> que es la imagen del icono.
	 */
	public Image getImagenI() {
		return anim.getImagen();
	}
	
	/**
	 * Metodo de acceso que regresa un nuevo rectangulo
	 * @return un objeto de la clase <code>Rectangle</code> que es el perimetro 
	 * del rectangulo
	 */
	public Rectangle getPerimetro(){
		return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
	
	/**
	 * Checa si el objeto <code>Base</code> intersecta a otro <code>Base</code>
	 *
     * @param obj
	 * @return un valor boleano <code>true</code> si lo intersecta <code>false</code>
	 * en caso contrario
	 */
	public boolean intersecta(Base obj){
		return getPerimetro().intersects(obj.getPerimetro());
	}

    /**
     *
     * @param obj
     * @return
     */
        
        public void actualizaAnimacion(long tiempo){
            anim.actualiza(tiempo);
        }
        
    public boolean tiene (Base obj){
            return getPerimetro().contains(posX, posY);
        }
}