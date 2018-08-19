import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Igra implements Runnable, KeyListener{

    private final Mreza mreza;
    private final Izgled izgled;

    private boolean running;

    public Igra(Mreza mreza, Izgled izgled) {
        this.mreza = mreza;
        this.izgled = izgled;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(Math.max(50, 200 - mreza.pridobiTocke() / 5 * 30));                     //DEFAULT_MOVE_INTERVAL
            } catch (InterruptedException e) {
                break;
            }
            mreza.seSmerSpremeni = false;
            if (mreza.naslRunda() == true) {
                izgled.narisi();
            } else {
                System.out.print(" tocke: " + mreza.pridobiTocke());
                izgled.sporociloKonec();
                running = false;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (mreza.seSmerSpremeni == false) {
            switch (keyCode) {
                case KeyEvent.VK_UP :
                    mreza.spremeniSmer(Smer.gor);
                    break;
                case KeyEvent.VK_RIGHT :
                    mreza.spremeniSmer(Smer.desno);
                    break;
                case KeyEvent.VK_DOWN :
                    mreza.spremeniSmer(Smer.dol);
                    break;
                case KeyEvent.VK_LEFT :
                    mreza.spremeniSmer(Smer.levo);
                    break;
                case KeyEvent.VK_SPACE :
                    break;
            }
        }
        // na novo  platno
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

}