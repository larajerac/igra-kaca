import javax.swing.*;
import java.awt.*;

public class Izgled {
   
    private JPanel platno;
    private final Mreza mreza;
    public final int velikostVozla = 15;

    public Izgled(Mreza mreza) {
        this.mreza = mreza;
    }

    public void init() {
        platno = new JPanel() {
            @Override
            public void paintComponent(Graphics graphics) {
                narisiMrezaOzadje(graphics);
                narisiKaca(graphics, mreza.pridobiKaca());
                narisiHrana(graphics, mreza.pridobiHrana());
            }
        };
    }

    public void narisi() {
        platno.repaint();
    }

    public JPanel pridobiPlatno() {
        return platno;
    }

    public void narisiKaca(Graphics graphics, Kaca kaca) {
        for (Vozel vozel : kaca.pridobiTelo()) {
            narisiKvadrat(graphics, vozel, Color.GREEN);
        }
    }
    public void narisiHrana(Graphics graphics, Vozel squareArea) {
        narisiKrog(graphics, squareArea, Color.RED);
    }
    public void narisiMrezaOzadje(Graphics graphics) {
        for (int i = 0; i < mreza.pridobiSirina(); i++) {
            for (int j = 0; j < mreza.pridobiVisina(); j++) {
                narisiKvadrat(graphics, new Vozel(i, j), new Color(127, 127, 127, 255));
            }
        }
    }

    private void narisiKvadrat(Graphics graphics, Vozel squareArea, Color barva) {
        graphics.setColor(barva);
        int velikost = velikostVozla;                                 
        graphics.fillRect(squareArea.pridobiX() * velikost, squareArea.pridobiY() * velikost, velikost - 1, velikost - 1);
    }
    private void narisiKrog(Graphics graphics, Vozel squareArea, Color barva) {
        graphics.setColor(barva);
        int velikost = velikostVozla;
        graphics.fillOval(squareArea.pridobiX() * velikost-1, squareArea.pridobiY() * velikost-1, velikost, velikost);
    }

    public void sporociloKonec() {
        JOptionPane.showMessageDialog(null, "Konec igre!", "Konec igre", JOptionPane.INFORMATION_MESSAGE);
    }

}