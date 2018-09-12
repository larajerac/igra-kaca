public class Kaca {

// shrani lokacije delov telesa
private final int[] x = new int[Okno.dobiVsepix()];
private final int[] y = new int[Okno.dobiVsepix()];

// shrani smer kaèe
private boolean premikLevo = false;
private boolean premikDesno = false;
private boolean premikGor = false;
private boolean premikDol = false;

private int cleni = 0; // shrani št. èlenov kaèe (zaène s tremi)

public int KacaX(int index) {
    return x[index];
}

public int KacaY(int index) {
    return y[index];
}

public void nastKacaX(int i) {
    x[0] = i;
}

public void nastKacaY(int i) {
    y[0] = i;
}

public boolean jepremikLevo() {
    return premikLevo;
}

public void nastpremikLevo(boolean premikLevo) {
    this.premikLevo = premikLevo;
}

public boolean jepremikDesno() {
    return premikDesno;
}

public void nastpremikDesno(boolean premikDesno) {
    this.premikDesno = premikDesno;
}

public boolean jepremikGor() {
    return premikGor;
}

public void nastpremikGor(boolean premikGor) {
    this.premikGor = premikGor;
}

public boolean jepremikDol() {
    return premikDol;
}

public void nastpremikDol(boolean premikDol) {
    this.premikDol = premikDol;
}

public int dobicleni() {
    return cleni;
}

public void nastcleni(int j) {
    cleni = j;
}

public void premik() {
    for (int i = cleni; i > 0; i--) {

        // premakne èlene (èleni in kaèa se premaknejo hkrati, skupaj
        x[i] = x[(i - 1)];
        y[i] = y[(i - 1)];
    }

    // premakne kaèo levo
    if (premikLevo) {
        x[0] -= Okno.pridVelikostClena();
    }
    // desno
    if (premikDesno) {
        x[0] += Okno.pridVelikostClena();
    }
    // dol
    if (premikDol) {
        y[0] += Okno.pridVelikostClena();
    }
    // gor
    if (premikGor) {
        y[0] -= Okno.pridVelikostClena();
    }

  
    // pixel te velikosti dodamo kaèi
}
 }