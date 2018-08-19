import java.util.LinkedList;

public class Kaca {

    private LinkedList<Vozel> telo = new LinkedList<Vozel>();

    public boolean isPojeHrana(Vozel hrana) {
        Vozel smer = telo.getFirst();
        return Math.abs(smer.pridobiX() - hrana.pridobiX()) + Math.abs(smer.pridobiY() - hrana.pridobiY()) == 0;
    }

    public Vozel move(Smer smer) {
        Vozel vozel = null;
        int smerX = this.telo.getFirst().pridobiX();
        int smerY = this.telo.getFirst().pridobiY();
        switch(smer) {
            case gor :
                vozel = new Vozel(smerX, smerY - 1);
                break;
            case desno :
                vozel = new Vozel(smerX + 1, smerY);
                break;
            case dol :
                vozel = new Vozel(smerX, smerY + 1);
                break;
            case levo :
                vozel = new Vozel(smerX - 1, smerY);
                break;
        }
        this.telo.addFirst(vozel);
        return telo.removeLast();
    }

    public Vozel pridobiHead() {
        return telo.getFirst();
    }

    public Vozel dodajRep(Vozel area) {
        this.telo.addLast(area);
        return area;
    }

    public LinkedList<Vozel> pridobiTelo() {
        return telo;
    }
}
