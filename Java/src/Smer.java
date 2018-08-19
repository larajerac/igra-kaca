public enum Smer {
    levo, desno, gor, dol;

    public boolean compatibleWith(Smer novaSmer) {
        if (this.equals(levo) || this.equals(desno)) {
            return gor.equals(novaSmer) || dol.equals(novaSmer); 
        } else {
            return levo.equals(novaSmer) || desno.equals(novaSmer);
        }
    }
}
