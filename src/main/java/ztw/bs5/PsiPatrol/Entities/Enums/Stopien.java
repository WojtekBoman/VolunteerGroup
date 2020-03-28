package ztw.bs5.PsiPatrol.Entities.Enums;

public enum Stopien {
    AMATOR("Amator"),
    ZAWODOWIEC("Zawodowiec"),
    KLASA_SWIATOWA("KlasaSwiatowa");

    private String value;

    Stopien(String value){this.value = value;}

    public String getValue() {return value;}
}
