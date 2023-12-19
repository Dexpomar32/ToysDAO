package org.example.Models;

public class Jucarii {
    private String numeJucarie;
    private double pret;
    private int cantitate;
    private String taraProductie;
    private int varstaMinima;

    public Jucarii(String numeJucarie, double pret, int cantitate, String taraProductie, int varstaMinima) {
        this.numeJucarie = numeJucarie;
        this.pret = pret;
        this.cantitate = cantitate;
        this.taraProductie = taraProductie;
        this.varstaMinima = varstaMinima;
    }

    public Jucarii() {}

    public String getNumeJucarie() {
        return numeJucarie;
    }

    public void setNumeJucarie(String numeJucarie) {
        this.numeJucarie = numeJucarie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getTaraProductie() {
        return taraProductie;
    }

    public void setTaraProductie(String taraProductie) {
        this.taraProductie = taraProductie;
    }

    public int getVarstaMinima() {
        return varstaMinima;
    }

    public void setVarstaMinima(int varstaMinima) {
        this.varstaMinima = varstaMinima;
    }

    @Override
    public String toString() {
        return "\n\nJucarii\n{" +
                "\n\tnumeJucarie='" + numeJucarie + '\'' +
                ", \n\tpret=" + pret +
                ", \n\tcantitate=" + cantitate +
                ", \n\ttaraProductie='" + taraProductie + '\'' +
                ", \n\tvarstaMinima=" + varstaMinima +
                "\n}";
    }
}
