  import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Okno extends JPanel implements ActionListener {

private int tocke = 0;
// višina in širina okna
private final static int SIRINA = 600;
private final static int VISINA = 600;

// predstavlja pixel velikost clenov kace
private final static int velikostPix = 25;


private final static int vsipix = (SIRINA * VISINA)
        / (velikostPix * velikostPix);

// prevri èe igra teèe
private boolean vIgri = true;

private Timer timer;

// nastavimo hitrost igre
private static int hitrost = 70;


private Kaca kaca = new Kaca();
private Hrana hrana = new Hrana();

public Okno() {
	
    addKeyListener(new Keys());
    setBackground(Color.BLACK);
    setFocusable(true);

    setPreferredSize(new Dimension(SIRINA, VISINA));

    zazeniIgro();
}

// narišemo komponente
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    narisi(g);
}

// narisemo kaco in hrano
void narisi(Graphics g) {
    // riši samo èe je kaèa živa
    if (vIgri == true) {
        g.setColor(Color.green);
        g.fillRect(hrana.dobihranaX(), hrana.dobihranaY(), velikostPix, velikostPix); // hrana

        // narisi kaco.
        for (int i = 0; i < kaca.dobicleni(); i++) {
            // glava
            if (i == 0) {
                g.setColor(Color.RED);
                g.fillRect(kaca.KacaX(i), kaca.KacaY(i),
                        velikostPix, velikostPix);
                //telo
            } else {
                g.fillRect(kaca.KacaX(i), kaca.KacaY(i),
                        velikostPix, velikostPix);
            }
        }

        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();
    } else {
        //èe umremo se zaène nova igra
        koncajIgra(g);
    }
}

void zazeniIgro() {
    kaca.nastcleni(3); //zaè velikost
    tocke = 0;
    // telo
    for (int i = 0; i < kaca.dobicleni(); i++) {
        kaca.nastKacaX(SIRINA / 2);
        kaca.nastKacaY(VISINA / 2);
    }
    // na zaè se premika desno
    kaca.nastpremikDesno(true);

    // naša prva hrana
    hrana.ustvariHrana();


    timer = new Timer(hitrost, this);
    timer.start();
}

//æe je kaèa blizu hrane
void preveristikHrana() {

    if ((blizina(kaca.KacaX(0), hrana.dobihranaX(), 20))
            && (blizina(kaca.KacaY(0), hrana.dobihranaY(), 20))) {

        System.out.println("intersection");
        // Add a 'joint' to our kaca
        kaca.nastcleni(kaca.dobicleni() + 1);
        //ustvari novo hrano
        hrana.ustvariHrana();
        //prištej toèko
        System.out.println(++tocke);
        
        
    }
}

//preveri trke kaèe
void preveriTrk() {

    // sama vase
    for (int i = kaca.dobicleni(); i > 0; i--) {

        //nemore èe ni daljša od 5
        if ((i > 5)
                && (kaca.KacaX(0) == kaca.KacaX(i) && (kaca
                        .KacaY(0) == kaca.KacaY(i)))) {
            vIgri = false; // konec
        }
    }

    // èe se kaèa zaleti v steno
    if (kaca.KacaY(0) >= VISINA) {
        vIgri = false;
    }

    if (kaca.KacaY(0) < 0) {
        vIgri = false;
    }

    if (kaca.KacaX(0) >= SIRINA) {
        vIgri = false;
    }

    if (kaca.KacaX(0) < 0) {
        vIgri = false;
    }

    // If the Igra has ended, then we can stop our timer
    if (!vIgri) {
        timer.stop();
    }
}

void koncajIgra(Graphics g) {

    //ustvari sporoèilo ob koncu igre
  
	String message = "Konec igre. Za ponovno igro pritsni presledek. Toèke:" + Tocke();

    // nov font
    Font font = new Font("Times New Roman", Font.BOLD, 14);
    FontMetrics metrics = getFontMetrics(font);

    // nastavi barvo in font
    g.setColor(Color.red);
    g.setFont(font);

    // narisi sporoèilo
    g.drawString(message, (SIRINA - metrics.stringWidth(message)) / 2,
            VISINA / 2);

    System.out.println("konec igre");

}

//dokler smo v igri
@Override
public void actionPerformed(ActionEvent e) {
    if (vIgri == true) {

        preveristikHrana();
        preveriTrk();
        kaca.premik();

        System.out.println(kaca.KacaX(0) + " " + kaca.KacaY(0)
                + " " + hrana.dobihranaX() + ", " + hrana.dobihranaY());
    }
    // ponovno nariši oknon
    repaint();
}

private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!kaca.jepremikDesno())) {
            kaca.nastpremikLevo(true);
            kaca.nastpremikGor(false);
            kaca.nastpremikDol(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!kaca.jepremikLevo())) {
            kaca.nastpremikDesno(true);
            kaca.nastpremikGor(false);
            kaca.nastpremikDol(false);
        }

        if ((key == KeyEvent.VK_UP) && (!kaca.jepremikDol())) {
            kaca.nastpremikGor(true);
            kaca.nastpremikDesno(false);
            kaca.nastpremikLevo(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!kaca.jepremikGor())) {
            kaca.nastpremikDol(true);
            kaca.nastpremikDesno(false);
            kaca.nastpremikLevo(false);
        }

        if ((key == KeyEvent.VK_SPACE) && (vIgri == false)) {

            vIgri = true;
            kaca.nastpremikDol(false);
            kaca.nastpremikDesno(false);
            kaca.nastpremikLevo(false);
            kaca.nastpremikGor(false);

            zazeniIgro();
        }
    }
}

private boolean blizina(int a, int b, int blizu) {
    return Math.abs((long) a - b) <= blizu;
}

public static int dobiVsepix() {
    return vsipix;
}

public static int pridVelikostClena() {
    return velikostPix;
}
public int Tocke() { return tocke; }
}