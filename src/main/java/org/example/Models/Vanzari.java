package org.example.Models;

import java.sql.Date;

public class Vanzari {
    private int idJucarie;
    private Date dataVanzare;
    private int cantitateaVanduta;

    public Vanzari(int idJucarie, Date dataVanzare, int cantitateaVanduta) {
        this.idJucarie = idJucarie;
        this.dataVanzare = dataVanzare;
        this.cantitateaVanduta = cantitateaVanduta;
    }

    public Vanzari() {}

    public int getIdJucarie() {
        return idJucarie;
    }

    public void setIdJucarie(int idJucarie) {
        this.idJucarie = idJucarie;
    }

    public Date getDataVanzare() {
        return dataVanzare;
    }

    public void setDataVanzare(Date dataVanzare) {
        this.dataVanzare = dataVanzare;
    }

    public int getCantitateaVanduta() {
        return cantitateaVanduta;
    }

    public void setCantitateaVanduta(int cantitateaVanduta) {
        this.cantitateaVanduta = cantitateaVanduta;
    }

    @Override
    public String toString() {
        return "Vanzari{" +
                "idJucarie=" + idJucarie +
                ", dataVanzare=" + dataVanzare +
                ", cantitateaVanduta=" + cantitateaVanduta +
                '}';
    }
}
