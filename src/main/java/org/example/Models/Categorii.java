package org.example.Models;

public class Categorii {
    private String numeCategorie;

    public Categorii(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    public Categorii() {}

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }

    @Override
    public String toString() {
        return "Categorii{" +
                "numeCategorie='" + numeCategorie + '\'' +
                '}';
    }
}