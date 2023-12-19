package org.example;

import org.example.Dao.DaoImpl.DaoJucariiImpl;
import org.example.Dao.DaoImpl.DaoPapusileImpl;
import org.example.Dao.DaoImpl.DaoVanzariImpl;
import org.example.Dao.DaoModels.DaoJucarii;
import org.example.Dao.DaoModels.DaoPapusile;
import org.example.Dao.DaoModels.DaoVanzari;
import org.example.Dao.Exceptions.DaoException;
import org.example.Models.Jucarii;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        DaoJucarii daoJucarii = new DaoJucariiImpl();
        DaoPapusile daoPapusile = new DaoPapusileImpl();
        DaoVanzari daoVanzari = new DaoVanzariImpl();
        Jucarii jucariiUpdate = new Jucarii("update", 12.12, 20, "China", 15);
        Jucarii jucarii = new Jucarii("testname", 45.52, 15, "Moldova", 10);

//        System.out.println(daoJucarii.findAll());
//        System.out.println(daoJucarii.findById(5));
//        daoJucarii.insert(jucarii);
//        daoJucarii.update(17, jucariiUpdate);
//        daoJucarii.delete(17);
//        daoJucarii.excludeJucariiCuCantitateZero();
//        daoJucarii.afisareJucariiScumpeSiIeftine();
//        daoPapusile.selectPapusiAsc();
//        daoJucarii.selectToysAVGTara("China");
//        daoJucarii.insertJucarieMoldova();
//        daoJucarii.selectToysWithFilters(50.0, 4, 10);
//        daoVanzari.selectProduseVanduteInZi("2023-10-01");
//        daoJucarii.selectCeleMaiPopulareProduse();

        connection.close();
    }
}