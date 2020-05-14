package ztw.bs5.PsiPatrol.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wiadomosc", schema = "psipatrol", catalog = "")
public class Wiadomosc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wiadomosci", nullable = false)
    private int idWiadomosci;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "email_adresata", nullable = false)
    private Uzytkownik emailAdresata;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "email_nadawcy", nullable = false)
    private Uzytkownik emailNadawcy;

    @Basic
    @Column(name = "tresc", nullable = false, length = 500)
    private String tresc;

    @Basic
    @Column(name = "temat", nullable = false, length = 50)
    private String temat;

    public Wiadomosc() {
    }

    public Wiadomosc(Uzytkownik emailAdresata, Uzytkownik emailNadawcy, String temat,String tresc) {
        this.emailAdresata=emailAdresata;
        this.emailNadawcy=emailNadawcy;
        this.temat=temat;
        this.tresc=tresc;
    }


    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public int getIdWiadomosci() {
        return idWiadomosci;
    }

    public void setIdWiadomosci(int idWiadomosci) {
        this.idWiadomosci = idWiadomosci;
    }



    public Uzytkownik getEmailAdresata() {
        return emailAdresata;
    }

    public void setEmailAdresata(Uzytkownik emailAdresata) {
        this.emailAdresata = emailAdresata;
    }


    public Uzytkownik getEmailNadawcy() {
        return emailNadawcy;
    }

    public void setEmailNadawcy(Uzytkownik emailNadawcy) {
        this.emailNadawcy = emailNadawcy;
    }


    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wiadomosc wiadomosc = (Wiadomosc) o;
        return idWiadomosci == wiadomosc.idWiadomosci &&
                emailAdresata.equals(wiadomosc.emailAdresata) &&
                emailNadawcy.equals(wiadomosc.emailNadawcy) &&
                Objects.equals(tresc, wiadomosc.tresc) &&
                Objects.equals(temat, wiadomosc.temat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWiadomosci, emailAdresata, emailNadawcy, tresc, temat);
    }
}
