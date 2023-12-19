package org.example.Dao.DaoModels;

import org.example.Dao.DaoBase;
import org.example.Models.Jucarii;

public interface DaoJucarii extends DaoBase<Integer, Jucarii> {
    void excludeJucariiCuCantitateZero();
    void afisareJucariiScumpeSiIeftine();
    void selectToysAVGTara(String entity);
    void insertJucarieMoldova();
    void selectToysWithFilters(double x, int n1, int n2);
    void selectCeleMaiPopulareProduse();
}
