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
    @Column(name = "tresc_wiadomosci", nullable = false, length = 255)
    private String trescWiadomosci;


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


    public String getTrescWiadomosci() {
        return trescWiadomosci;
    }

    public void setTrescWiadomosci(String trescWiadomosci) {
        this.trescWiadomosci = trescWiadomosci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wiadomosc that = (Wiadomosc) o;
        return idWiadomosci == that.idWiadomosci &&
                Objects.equals(emailAdresata, that.emailAdresata) &&
                Objects.equals(emailNadawcy, that.emailNadawcy) &&
                Objects.equals(trescWiadomosci, that.trescWiadomosci);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWiadomosci, emailAdresata, emailNadawcy, trescWiadomosci);
    }
}
