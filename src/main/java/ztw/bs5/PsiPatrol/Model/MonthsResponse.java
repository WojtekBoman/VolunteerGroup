package ztw.bs5.PsiPatrol.Model;

public class MonthsResponse {

    private int styczen;
    private int luty;
    private int marzec;
    private int kwiecien;
    private int maj;
    private int czerwiec;
    private int lipiec;
    private int sierpien;
    private int wrzesien;
    private int pazdziernik;
    private int listopad;
    private int grudzien;

    public MonthsResponse(int styczen, int luty, int marzec, int kwiecien,
                          int maj, int czerwiec, int lipiec, int sierpien,
                          int wrzesien, int pazdziernik, int listopad, int grudzien) {

        this.styczen = styczen;
        this.luty = luty;
        this.marzec = marzec;
        this.kwiecien = kwiecien;
        this.maj = maj;
        this.czerwiec = czerwiec;
        this.lipiec = lipiec;
        this.sierpien = sierpien;
        this.wrzesien = wrzesien;
        this.pazdziernik = pazdziernik;
        this.listopad = listopad;
        this.grudzien = grudzien;
    }

    public MonthsResponse() {
        this.styczen = 0;
        this.luty = 0;
        this.marzec = 0;
        this.kwiecien = 0;
        this.maj = 0;
        this.czerwiec = 0;
        this.lipiec = 0;
        this.sierpien = 0;
        this.wrzesien = 0;
        this.pazdziernik = 0;
        this.listopad = 0;
        this.grudzien = 0;
    }

    public int getStyczen() {
        return styczen;
    }

    public void setStyczen(int styczen) {
        this.styczen = styczen;
    }

    public int getLuty() {
        return luty;
    }

    public void setLuty(int luty) {
        this.luty = luty;
    }

    public int getMarzec() {
        return marzec;
    }

    public void setMarzec(int marzec) {
        this.marzec = marzec;
    }

    public int getKwiecien() {
        return kwiecien;
    }

    public void setKwiecien(int kwiecien) {
        this.kwiecien = kwiecien;
    }

    public int getMaj() {
        return maj;
    }

    public void setMaj(int maj) {
        this.maj = maj;
    }

    public int getCzerwiec() {
        return czerwiec;
    }

    public void setCzerwiec(int czerwiec) {
        this.czerwiec = czerwiec;
    }

    public int getLipiec() {
        return lipiec;
    }

    public void setLipiec(int lipiec) {
        this.lipiec = lipiec;
    }

    public int getSierpien() {
        return sierpien;
    }

    public void setSierpien(int sierpien) {
        this.sierpien = sierpien;
    }

    public int getWrzesien() {
        return wrzesien;
    }

    public void setWrzesien(int wrzesien) {
        this.wrzesien = wrzesien;
    }

    public int getPazdziernik() {
        return pazdziernik;
    }

    public void setPazdziernik(int pazdziernik) {
        this.pazdziernik = pazdziernik;
    }

    public int getListopad() {
        return listopad;
    }

    public void setListopad(int listopad) {
        this.listopad = listopad;
    }

    public int getGrudzien() {
        return grudzien;
    }

    public void setGrudzien(int grudzien) {
        this.grudzien = grudzien;
    }
}
