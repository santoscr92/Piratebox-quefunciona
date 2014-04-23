    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    package piratebox;

    /**
     *
     * @author santoscr92 
     */
    
    //tonyStark
    //thechosenjuan

    import javax.swing.JFrame;
    import java.applet.AudioClip;
    import java.awt.Graphics;
    import java.awt.Image;
    import java.awt.Color;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;
    import java.awt.event.MouseMotionListener;
    import java.net.URL;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import javax.swing.ImageIcon;
    import java.util.LinkedList;
    import java.awt.Toolkit;

    public class examen extends JFrame implements Runnable, KeyListener,MouseListener,MouseMotionListener{
         private static final long serialVersionUID = 1L;
        private final int MIN = -6;    //Minimo al generar un numero al azar.
        private final int MAX = 7;    //Maximo al generar un numero al azar.
        private Image dbImage;      // Imagen a proyectar  
        private Image gameover;
        private Image fondo;
        private Image pausee;
        private Image howto;
        private Image settings;
        private Image menu;
        private Graphics dbg;       // Objeto grafico
        private SoundClip sonido;    // Objeto AudioClip
        private SoundClip rat;    // Objeto AudioClip
        private SoundClip bomb;    //Objeto AudioClip
        private SoundClip teleport;
        private Bueno jack;    // Objeto de la clase Bueno
        private Malo davidj;    //Objeto de la clase Malo
        private int ancho;  //Ancho del jackimage
        private int alto;   //Alto del jackimage
        private ImageIcon jackimage; // Imagen del jackimage.
        private boolean clic = false;
        private int random;
        private int incX,incY;
        private long tiempoActual;
        private boolean move; 
        
        
        //bala
        private Bala bala; //objeto bala
        private int jackcurrentstate; // saber en que direccion se quedo jack
        private int balasactivas;
        private boolean bal,balaviva,movbala;
        private int velocidadbalax,velocidadbalay;

        //mouse
        private int x1; // posicion del mouse en x
        private int y1; // posicion del mouse en y
        private int vidas = 1000;
        private LinkedList lista;
        private int score = 0;
        private boolean pausa = false;
        private boolean bsettings = false;
        private boolean bhowto = false;
        private boolean bmenu = false;
        private boolean chocoabajo = false;
        private boolean chocoalado = false;
        private boolean up,down,left,right;
        private boolean activado = false;
        private boolean valp=true,valh=true,valm=true,vals=true;
        
        // variables para vida
        private int vida;
        private boolean espaciovida;




        public examen() {
            init();
            start();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

        public void init() {

            this.setSize(1200, 700);
            move = false; 
            int posX = getWidth() / 2;    // posicion en x es un cuarto del applet
            int posY = getHeight() / 2;    // posicion en y es un cuarto del applet
            URL eURL = this.getClass().getResource("Imagenes/palmera.png");
            jack = new Bueno(posX, posY,1);
            URL bURL = this.getClass().getResource("Imagenes/balab.png");
            //jack.setPosX((int) (getWidth()/2));
            //jack.setPosY(getHeight());
            int posrX = (int) (Math.random() * (getWidth() / 4)) + getWidth() / 2;    //posision x es tres cuartos del applet
            int posrY = 0;    //posision y es tres cuartos del applet
            URL rURL = this.getClass().getResource("Imagenes/muerto.png");
            davidj = new Malo(posrX, posrY,1);
            davidj.setPosX(davidj.getPosX() - davidj.getAncho());
            davidj.setPosY(davidj.getPosY() - davidj.getAlto());
            
            setBackground(Color.white);
            addKeyListener(this);
            
            //imagenes
            URL goURL = this.getClass().getResource("Imagenes/palmera.png");
            gameover = Toolkit.getDefaultToolkit().getImage(goURL);

            URL URL = this.getClass().getResource("Imagenes/muerto.png");
            gameover = Toolkit.getDefaultToolkit().getImage(goURL);

            URL fondoURL = this.getClass().getResource("Imagenes/fondo.jpg");
            fondo = Toolkit.getDefaultToolkit().createImage(fondoURL);

            URL pauseURL = this.getClass().getResource("Imagenes/pause.jpg");
            pausee = Toolkit.getDefaultToolkit().createImage(pauseURL);
            
             URL menuURL = this.getClass().getResource("Imagenes/menu.jpg");
            menu = Toolkit.getDefaultToolkit().createImage(menuURL);
            
            URL howtoURL = this.getClass().getResource("Imagenes/howto.jpg");
            howto = Toolkit.getDefaultToolkit().createImage(howtoURL);
            
            URL settingsURL = this.getClass().getResource("Imagenes/settings.jpg");
            settings = Toolkit.getDefaultToolkit().createImage(settingsURL);

            
            //Se cargan los sonidos.
            sonido = new SoundClip("Sonidos/mice.wav");
            bomb = new SoundClip("Sonidos/Explosion.wav");
            teleport = new SoundClip("Sonidos/teleport.wav");


            //jackimage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(eURL));
            //ancho = jackimage.getIconWidth();
            //alto = jackimage.getIconHeight();
            //ancho2 = davidj.getIconWidth();
            // alto2 = davidj.getIconHeight();
            addMouseListener(this);
            addMouseMotionListener(this);  

            lista = new LinkedList();
            for(int k = 0; k < 12; k++)
            {
                random = ((int) ((Math.random() * (900 - 20)) + 20));
                int posaY = 0;    //posision x es tres cuartos del applet
                //int posaY = (int) (Math.random() * (getHeight() / 4)) + getHeight() / 2;    //posision y es tres cuartos del applet
                int posaX = (int) (Math.random() * (getWidth() / 4)) + getWidth() / 2;
                URL aURL = this.getClass().getResource("Imagenes/muerto.png");
                davidj = new Malo(random, posrY,1);
                davidj.setPosX(davidj.getPosX() - davidj.getAncho());
                davidj.setPosY(davidj.getPosY() - davidj.getAlto());
                lista.addLast(davidj);
            }
            
            
            jackcurrentstate = 2;
            vida = 200;
            espaciovida = false;
            velocidadbalax = 15;
            velocidadbalay = 15;
        }

        public void start() {
            // Declaras un hilo
            Thread th = new Thread(this);
            // Empieza el hilo
            th.start();
        }

        public void run() {
            while (vidas > 0) {
                if(!pausa){
                actualiza();
                checaColision();
                }
                repaint(); 
                // Se actualiza el <code>Applet</code> repintando el contenido.
                try {
                    // El thread se duerme.
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    System.out.println("Error en " + ex.toString());
                }

            }
        }

        public void actualiza() {

             //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
            long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;

            //Guarda el tiempo actual
            tiempoActual += tiempoTranscurrido;
            
            //actualizacion de jack si esta en movimiento
            if(move == true){
            jack.actualizaAnimacion(tiempoActual);
            }
            
            //actualizacion de animaicion de malos
            for(int x=0; x<lista.size(); x++) {
                Malo davidj = (Malo) lista.get(x);
                davidj.actualizaAnimacion(tiempoActual);
            }

            // movimiento de jack         
            if(up)
            {
                jack.setPosY(jack.getPosY() - 10);
                up = false;
                move = false;
                jackcurrentstate = 2;
            }
            if(down)
            {
                jack.setPosY(jack.getPosY() + 10);
                down = false;
                move = false;
                jackcurrentstate = 4;
            }
            if(left)
            {
                jack.setPosX(jack.getPosX() - 10);
                left = false;
                move = false;
                jackcurrentstate = 3;
            }
            if(right)
            {
                jack.setPosX(jack.getPosX() + 10);
                right = false;
                move = false;
                jackcurrentstate = 1;
            }


            /*
            for (int k = 0; k < 12; k++) 
            {
                Malo davidj = (Malo) lista.get(k);   
                davidj.setPosY(davidj.getPosY() + 1);
            }
            */
            
            // actualiza posicion de los malos 
            for (int i = 0; i < 12; i++) {
                Malo davidj = (Malo) lista.get(i);

                if (jack.getPosX() > davidj.getPosX()) {
                    incX = 1;
                    davidj.setFlechitas(3);
                    davidj.setPosX(davidj.getPosX() + incX);
                } 

                else {

                    incX = -1;
                    davidj.setFlechitas(4);
                    davidj.setPosX(davidj.getPosX() + incX);
                }

                if (jack.getPosY() > davidj.getPosY()) {

                    incY = 1;
                    davidj.setFlechitas(1);
                    davidj.setPosY(davidj.getPosY() + incY);
                } 

                else {
                    incY = -1;
                    davidj.setFlechitas(2);
                    davidj.setPosY(davidj.getPosY() + incY);
                }
            }
            
            //crea una nueva bala
            if(bal && !balaviva)
            {
                if(jackcurrentstate == 1)
               {
                  bala = new Bala(jack.getPosX() + jack.getAncho(),jack.getPosY() +  25,jackcurrentstate);  
               }
               else if(jackcurrentstate == 2)
               {
                   bala = new Bala(jack.getPosX() + 10,jack.getPosY(),jackcurrentstate);
               }
               else if(jackcurrentstate == 3)
               {
                   bala = new Bala(jack.getPosX(),jack.getPosY() + 25,jackcurrentstate);
               }
               else if(jackcurrentstate == 4)
               {
                    bala = new Bala(jack.getPosX() + 10,jack.getPosY() + jack.getAlto(),jackcurrentstate);
               }
                
                bal = false;
                balaviva = true;
            }
            
            //acutaliza animacion de la bala si no es null
            if(bala != null)
            {
               bala.actualizaAnimacion(tiempoActual); 
               if(true){
                    if(jackcurrentstate == 1)
                    {
                        bala.setPosX(bala.getPosX() + velocidadbalax);
                    }
                    else if(jackcurrentstate == 2)
                    {
                        bala.setPosY(bala.getPosY() - velocidadbalay);
                    }
                    else if(jackcurrentstate == 3)
                    {
                        bala.setPosX(bala.getPosX() - velocidadbalax );
                    }
                    else if(jackcurrentstate == 4)
                    {
                        bala.setPosY(bala.getPosY() + velocidadbalay);
                    } 
                    movbala = true;
               }
               
            }
            
            
                
            

      }

        public void checaColision() {
            
            //jack no sale del frame
            if (jack.getPosX() + jack.getAncho() > getWidth()) {
                jack.setPosX(getWidth() - jack.getAncho());
            }
            if (jack.getPosX() < 0) {
                jack.setPosX(0);
            }
            if (jack.getPosY() + jack.getAlto() > getHeight()) {
                jack.setPosY(getHeight() - jack.getAlto());
            }
            if (jack.getPosY() < 0) {
                jack.setPosY(0);
            }
            
            // si la bala sale del frame balaviva = false
            if(bala != null){
                if (bala.getPosX() > getWidth()) {
                    balaviva = false;
                    //bala = null;
                }
                
                if (bala.getPosX() < 0) {
                    balaviva = false;
                    //bala = null;
                }
                
                if (bala.getPosY() > getHeight()) {
                    balaviva = false;
                    //bala = null;
                }
                
                if (bala.getPosY() < 0) {
                    balaviva = false;
                    //bala = null;
                }
                
            }
            



            
            for (int i = 0; i < lista.size(); i++) {
                Malo davidj = (Malo) lista.get(i);
                
                if (jack.intersecta(davidj)) 
                {
                    bomb.play();    //sonido al colisionar

                   /* // si le planeta pega abajo del asteroide se le suma 100 al score
                    if (jack.intersecta(davidj) && (davidj.getPosY() + davidj.getAlto() - 15) < jack.getPosY())
                    {
                        score += 100;
                        vida--;
                    }
                    // si el planeta intersecta el asteroide por un lado se le resta una vida y 
                    // aumenta la velocidad
                    else if(jack.intersecta(davidj) && davidj.getPosY() + davidj.getAlto() - 15 >= jack.getPosY())
                    {
                        //vidas--;

                    }
                    */
                    
                    
                    vida--;
                        
                    
                    //Los asteroides aparecen en un rando random
                    davidj.setPosX(((int) (Math.random() * (1000 - 100))) + 100);
                    davidj.setPosY(0);
                }
                if(bala != null){
                   if(bala.intersecta(davidj))
                    {
                    score +=5;
                    //Los asteroides aparecen en un rando random
                    davidj.setPosX(((int) (Math.random() * (1000 - 100))) + 100);
                    davidj.setPosY(0);
                    bala = null;
                    balaviva = false;
                    movbala = false;
                    } 
                }
                
            }       
    }

        public void paint(Graphics g) {
            // Inicializan el DoubleBuffer
            if (dbImage == null) 
            {
                dbImage = createImage(this.getSize().width, this.getSize().height);
                dbg = dbImage.getGraphics();
            }

            // Actualiza la imagen de fondo.
            dbg.setColor(getBackground());
            dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

            // Actualiza el Foreground.
            dbg.setColor(getForeground());
            paint1(dbg);

            // Dibuja la imagen actualizada
            g.drawImage(dbImage, 0, 0, this);
        }

        public void paint1(Graphics g) {
            if (vidas>0){
            if (jack != null && davidj != null) {
                //Dibuja la imagen en la posicion actualizada
                g.drawImage(fondo, 0, 0, null);
                g.drawImage(jack.getImagenI(), jack.getPosX(), jack.getPosY(), this);
                g.drawImage(davidj.getImagenI(), davidj.getPosX(), davidj.getPosY(), this);
                

            } else {
                //Da un mensaje mientras se carga el dibujo
                g.drawString("No se cargo la imagen..", 20, 20);
            }
            }
            else{
                g.drawImage(gameover, 400, 150, this);
            }
            
            if(bala != null)
            {
                g.drawImage(bala.getImagenI(),bala.getPosX(),bala.getPosY(),this);
            }
            

            for (int i = 0; i < lista.size(); i++) {
                Malo davidj = (Malo) lista.get(i);
                g.drawImage(davidj.getImagenI(), davidj.getPosX(), davidj.getPosY(), this);
            }
            if(pausa){
                    g.drawImage(pausee, getWidth()/3, getHeight()/3,null);
                }
            
            if(bmenu){
                    g.drawImage(menu, 0, 0,null);
                }
            
            if(bsettings){
                    g.drawImage(settings, 0, 0,null);
                }
            
            if(bhowto){
                    g.drawImage(howto, 0, 0,null);
                }
            
            

            g.setColor(Color.black);
            //g.drawString("Vidas: " + vidas, 10, 20);
            g.drawString("Score: " + score, 70, 50);
            g.drawString("Vida: " + vida, jack.getPosX(), jack.getPosY() - 10);

        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_P && valp) //Presiono flecha arriba
            {   
                pausa = !pausa;
                valm = !valm;
                valh = !valh;
                vals = !vals;
            }
            
            
            
            if (e.getKeyCode() == KeyEvent.VK_M && valm) //Presiono flecha arriba
            {   
                bmenu = !bmenu;
                valp = !valp;
                valh = !valh;
                vals = !vals;
            }
            
            if (e.getKeyCode() == KeyEvent.VK_S && vals) //Presiono flecha arriba
            {   
                bsettings = !bsettings;
                valm = !valm;
                valh = !valh;
                valp = !valp;
            }
            
            if (e.getKeyCode() == KeyEvent.VK_H && valh) //Presiono flecha arriba
            {   
                bhowto = !bhowto;
                valm = !valm;
                valp = !valp;
                vals = !vals;
            }

            int x =jack.getPosX();
            int y=jack.getPosY();
            
            if (e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                bal = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) //Presiono flecha arriba
            {   
                jack = new Bueno(x,y,1);
                up = true;
                move = true; 
            } 
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) //Presiono flecha abajo
                    {   jack = new Bueno(x,y,2);
                        down = true;
                        move = true;
                    } 
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) //Presiono flecha izquierda
                    {    
                        jack = new Bueno(x,y,3);
                        left = true;
                        move = true; 
                    } 
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) //Presiono flecha derecha
                    {   
                        jack = new Bueno(x,y,4);
                        right = true;
                        move = true; 
                    }


        }

        /**
         * Metodo <I>keyTyped</I> sobrescrito de la interface
         * <code>KeyListener</code>.<P>
         * En este metodo maneja el evento que se genera al presionar una tecla que
         * no es de accion.
         *
         * @param e es el <code>evento</code> que se genera en al presionar las
         * teclas.
         */
        @Override
        public void keyTyped(KeyEvent e) {

        }

        /**
         * Metodo <I>keyReleased</I> sobrescrito de la interface
         * <code>KeyListener</code>.<P>
         * En este metodo maneja el evento que se genera al soltar la tecla
         * presionada.
         *
         * @param e es el <code>evento</code> que se genera en al soltar las teclas.
         */
        @Override
        public void keyReleased(KeyEvent e) {

        }

         public void mouseClicked(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            if(jack.tiene(x1,y1))
                clic = true;

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }


        public void mouseDragged(MouseEvent e) {
            /*
            x1 = e.getX();
            y1 = e.getY();

            clic = true;
            */
            /*
                    if(jack.tiene(x1, y1)) {
                    clic = true;
            }*/
        }


    }