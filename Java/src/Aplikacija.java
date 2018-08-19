import javax.swing.*;
import java.awt.*;

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

        //nastavi JPanel velikost
        izgled.pridobiPlatno().setPreferredSize(new Dimension(mrezaVelikost * izgled.velikostVozla, 
            mrezaVelikost1 * izgled.velikostVozla));
        // add JPanel to windows
        contentPane.add(izgled.pridobiPlatno(), BorderLayout.CENTER);

        // narisi mrezo in kaco
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        igra = new Igra(mreza, izgled);
        window.addKeyListener(igra);

       
        new Thread(igra).start();

    }

    public static void main(String[] args) {
        //KacaApp kacaApp = new KacaApp();
        //kacaApp.run();
        SwingUtilities.invokeLater(new Aplikacija());
    }
}
