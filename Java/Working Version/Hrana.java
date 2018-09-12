public class Hrana {

private Kaca kaca = new Kaca();
private int hranaX; // shrani x koor hrane
private int hranaY; // shrani y koor hrane

// doloèi pozicijo hrane
private final int nakljpozicija = 20;

public void ustvariHrana() {

    // nastavi x in y hrane na naklj pozicijo

    int lokacija = (int) (Math.random() * nakljpozicija);
    hranaX = ((lokacija * Okno.pridVelikostClena()));

    lokacija = (int) (Math.random() * nakljpozicija);
    hranaY = ((lokacija * Okno.pridVelikostClena()));

    if ((hranaX == kaca.KacaX(0)) && (hranaY == kaca.KacaY(0))) {
        ustvariHrana();
       
    }
}

public int dobihranaX() {

    return hranaX;
}

public int dobihranaY() {
    return hranaY;
}


}