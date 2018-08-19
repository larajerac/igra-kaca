import java.util.Arrays;
import java.util.Random;

public class Mreza {

    private boolean pokrije[][];
    private final int sirina;
    private final int visina;
    private int tocke = 0;

    private Kaca kaca;
    private Vozel hrana;

    private Smer smerKace = Smer.levo;         // zacetna smer je levo 
    public boolean seSmerSpremeni = false;

    public Mreza(int sirina, int visina) {
        this.sirina = sirina;
        this.visina = visina;
        pokrije = new boolean[sirina][visina];

        initKaca();
        ustvariHrana();
    }
    private Kaca initKaca() {                                 // zaèetna kaèa ima tri clene
        kaca = new Kaca();
        for (int i = 0; i < 3; i++) {
            kaca.dodajRep(new Vozel(i + sirina / 2, visina / 2));
            pokrije[i + sirina / 2][visina / 2] = true;
        }
        return kaca;
    }

    public Vozel ustvariHrana() {
        int x,y;
        do {
            x = new Random( ).nextInt(sirina);
            y = new Random( ).nextInt(visina);
        } while (pokrije[x][y] == true);
        hrana = new Vozel(x, y);
        return hrana;
    }

    public boolean naslRunda() {                                     
        if (veljPremik(smerKace)) {
            Vozel move = kaca.move(smerKace);
            if (kaca.isPojeHrana(hrana)) {                             //èe poje hrano dodaj vozel
                kaca.dodajRep(move);
                ustvariHrana();
                System.out.println(++tocke);
            } else pokrije[move.pridobiX()][move.pridobiY()] = false;
            return true;
        } else return false;
    }
    private boolean veljPremik(Smer smer) {
        int smerX = kaca.pridobiHead().pridobiX();
        int smerY = kaca.pridobiHead().pridobiY();
        switch(smer) {
            case gor :
                smerY--;
                break;
            case desno :
                smerX++;
                break;
            case dol :
                smerY++;
                break;
            case levo :
                smerX--;
                break;
        }
        if (smerX < 0 || smerX >= sirina || smerY < 0 || smerY >= visina) return false;
        if (pokrije[smerX][smerY] == true) return false;
        pokrije[smerX][smerY] = true;
        return true;
    }

    public void spremeniSmer(Smer novaSmer) {
        if (smerKace.compatibleWith(novaSmer)) {
            smerKace = novaSmer;
            seSmerSpremeni = true;
        }
    }

    public Kaca pridobiKaca() { return kaca; }
    public Vozel pridobiHrana() { return hrana; }
    public int pridobiVisina() { return sirina; }
    public int pridobiSirina() { return visina; }
    public int pridobiTocke() { return tocke; }

}