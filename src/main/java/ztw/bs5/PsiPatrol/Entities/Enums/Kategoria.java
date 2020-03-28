package ztw.bs5.PsiPatrol.Entities.Enums;

public enum Kategoria {
    AMATOR("Sprzatanie"),
    INNE("Inne");


    private String value;

    Kategoria(String value){this.value = value;}

    public String getValue() {return value;}
}
