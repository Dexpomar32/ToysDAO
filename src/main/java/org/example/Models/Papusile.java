package org.example.Models;

public class Papusile {
    private int idJucarie;
    private String material;
    private double inaltime;

    public Papusile(int idJucarie, String material, double inaltime) {
        this.idJucarie = idJucarie;
        this.material = material;
        this.inaltime = inaltime;
    }

    public Papusile() {}

    public int getIdJucarie() {
        return idJucarie;
    }

    public void setIdJucarie(int idJucarie) {
        this.idJucarie = idJucarie;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getInaltime() {
        return inaltime;
    }

    public void setInaltime(double inaltime) {
        this.inaltime = inaltime;
    }

    @Override
    public String toString() {
        return "Papusile{" +
                "idJucarie=" + idJucarie +
                ", material='" + material + '\'' +
                ", inaltime=" + inaltime +
                '}';
    }
}
