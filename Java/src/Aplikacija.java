import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Aplikacija implements Runnable {

    private final int mrezaVelikost = 30;
    private final int mrezaVelikost1 = 30;
    private Izgled izgled;
    private Igra igra;
    
   
   
    public void run() {

        JFrame window = new JFrame("Kaca");               //ustvarimo igralno okno

        Container contentPane = window.getContentPane();
        
        Mreza mreza = new Mreza(mrezaVelikost, mrezaVelikost1);
        izgled = new Izgled(mreza);
        izgled.init();

        
        izgled.pridobiPlatno().setPreferredSize(new Dimension(mrezaVelikost * izgled.velikostVozla, 
            mrezaVelikost1 * izgled.velikostVozla));
        
        contentPane.add(izgled.pridobiPlatno(), BorderLayout.CENTER);

        window.pack();
        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        igra = new Igra(mreza, izgled);
        window.addKeyListener(igra);
        
       
        new Thread(igra).start();
        	

    }
    
    public static void main(String[] args) {
    	Aplikacija aplikacija = new Aplikacija();
        aplikacija.run();
    
      
    }
}
