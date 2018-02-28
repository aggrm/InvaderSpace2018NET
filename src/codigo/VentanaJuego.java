/*
 * Juego de los invasores del espacio

 Ejercicio para explicar los siguientes conceptos:
 - Hilos de ejecución
 - ArrayList
 */
package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 *
 * @author Alberto Goujon
 * 
 * TODO:
 * - Crear un sistema para almacenar todos los macianos
 *      Marciano[][]listaMarcianos
 * - Crear el sistema de choques disparos-marcianos
 */
public class VentanaJuego extends javax.swing.JFrame {

    static int ANCHOPANTALLA = 600;
    static int ALTOPANTALLA = 450;
    
    //Cuantos marcianos van a salir en la pantalla------------------------------
    int filasMarcianos = 5;
    int columnasMarcianos = 10;
    //--------------------------------------------------------------------------
    BufferedImage buffer = null;
    int contador = 0;
    Nave miNave = new Nave(ANCHOPANTALLA);
    Disparo miDisparo = new Disparo(ALTOPANTALLA);
    Marciano miMarciano = new Marciano(ANCHOPANTALLA);
    
    
    Marciano[][] listaMarcianos = new Marciano[filasMarcianos][columnasMarcianos];      //El array de dos dimensiones que guarda la lista de marcianos
    boolean direccionMarciano = false;                                                  //Direccion en la que se movera la array de marcianos
                                                                                        
                                                                                        //Bucle de animación del juego
                                                                                        //En este caso es un hilo de ejecucuión nuevo que se encarga
                                                                                        //de refrescar la pantalla
    Timer temporizador = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: codigo de la animación
            bucleDelJuego();
        }
    });

    /**
     * Creates new form VentanaJuego
     */
    public VentanaJuego() {
        initComponents();

        //Hay que quitar la opción "resizable" del jPanel para que se ajuste
        //correctamente Creditos: Junior
        setSize(ANCHOPANTALLA, ALTOPANTALLA);
        buffer = (BufferedImage) jPanel1.createImage(ANCHOPANTALLA, ALTOPANTALLA);
        buffer.createGraphics();
        //Inicio el temporizador
        temporizador.start();

        //Meto la nave en el juego
        miNave.x = ANCHOPANTALLA / 2 - miNave.imagen.getWidth(this) / 2;
        miNave.y = ALTOPANTALLA - miNave.imagen.getWidth(this) - 25;
        
        //Creo el array de los marcianos
        for(int i = 0; i <filasMarcianos; i++)
        {
            for(int j=0; j <columnasMarcianos; j++)
            {
                listaMarcianos[i][j] = new Marciano(ANCHOPANTALLA);
                listaMarcianos[i][j].x = j * (15 + listaMarcianos[i][j].imagen.getWidth(null));
                listaMarcianos[i][j].y = i * (10 + listaMarcianos[i][j].imagen.getHeight(null));
            }
        }
        //inicio el temporizador
        temporizador.start();
    }
    
    private void pintaMarcianos(Graphics2D g2)
    {
        for(int i = 0; i <filasMarcianos; i++)
        {
            for(int j=0; j <columnasMarcianos; j++)
            {
                listaMarcianos[i][j].mueve(direccionMarciano);
                if(contador < 50)
                {
                    g2.drawImage(listaMarcianos[i][j].imagen,listaMarcianos[i][j].x, listaMarcianos[i][j].y, null);
                }
                else if (contador < 100)
                {
                    g2.drawImage(listaMarcianos[i][j].imagen2,listaMarcianos[i][j].x, listaMarcianos[i][j].y, null);
                }
                else contador = 0;
                if ( listaMarcianos[i][j].x == ANCHOPANTALLA- listaMarcianos[i][j].imagen.getWidth(null) ||  listaMarcianos[i][j].x == 0)
                {
                     direccionMarciano = !direccionMarciano;
                     for (int k = 0; k < filasMarcianos; k++)
                     {
                         for (int m = 0; m < columnasMarcianos; m++)
                         {
                            listaMarcianos[k][m].y +=  listaMarcianos[k][m].imagen.getHeight(null);
                         }
                     }
                }
            }
        }
    }
    private void bucleDelJuego() {
        //el bucle de animación gobierna el redibujado de los objetos en el jPanel1
        //Primero borro todo la que haye en el bufer 
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, ANCHOPANTALLA, ALTOPANTALLA);

        ////////////////////////////////////////////////////////////////////////
        //Redibujamos cada elemento en su nueva posición  en el bufer
        contador++;
        //Pinto el disparo
        miDisparo.mueve();
        g2.drawImage(miDisparo.imagen, miDisparo.getX(), miDisparo.getY(), null);
        
        //Pinto nave
        miNave.mueve();
        g2.drawImage(miNave.imagen, miNave.x, miNave.y, null);
        
        //Pinto marcianos
        pintaMarcianos(g2);
        
//        miMarciano.mueve();
//        if(contador < 50){
//            g2.drawImage(miMarciano.imagen,miMarciano.x, miMarciano.y, null);
//        }
//        else if (contador < 100)
//        {
//            g2.drawImage(miMarciano.imagen2,miMarciano.x, miMarciano.y, null);
//        }
//        else contador = 0;
//         if (miMarciano.x == ANCHOPANTALLA-miMarciano.imagen.getWidth(null) || miMarciano.x == 0)
//         {
//             miMarciano.direccion = !miMarciano.direccion;
//             miMarciano.y += miMarciano.imagen.getHeight(null);
//         }
        
        ////////////////////////////////////////////////////////////////////////
        //Dibujo de golpe el bufer sobre el jPanel1
        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(768, 522));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_A:
                miNave.setPulsadoIzquierda(true);
                break;
            case KeyEvent.VK_D:
                miNave.setPulsadoDerecha(true);
                break;
            case KeyEvent.VK_SPACE:
                miDisparo.setDisparo(true); miDisparo.posicionaDisparo(miNave);
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_A:
                miNave.setPulsadoIzquierda(false);
                break;
            case KeyEvent.VK_D:
                miNave.setPulsadoDerecha(false);
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
