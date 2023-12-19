package org.example.Dao.DaoImpl;

import org.example.ConnectionCreator;
import org.example.Dao.DaoModels.DaoVanzari;
import org.example.Models.Vanzari;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoVanzariImpl implements DaoVanzari {
    private static final String SQL_SELECT_ALL_VANZARI = "SELECT * FROM Vanzari";
    private static final String SQL_SELECT_VANZARI_BY_ID = "SELECT * FROM Vanzari WHERE ID_Vanzare = ?";
    private static final String SQL_UPDATE_VANZARI = "UPDATE Vanzari SET ID_Jucarie = ?, Data_Vanzare = ?, "
            + "Cantitate_Vanduta = ? WHERE ID_Vanzare = ?";
    private static final String SQL_DELETE = "DELETE FROM Vanzari WHERE ID_Vanzare = ?";
    private static final String SQL_INSERT_INTO_VANZARI = "INSERT INTO Vanzari(ID_Jucarie, Data_Vanzare, " +
            "Cantitate_Vanduta) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_VANZARI_ZI = "SELECT SUM(Cantitate_Vanduta) AS NumarProduseVandute FROM Vanzari WHERE Data_Vanzare = ?";
    private static Connection connection;

    public DaoVanzariImpl() {
        connection = ConnectionCreator.createConnection();
    }

    @Override
    public List<Vanzari> findAll() {
        List<Vanzari> vanzaris = new ArrayList<>();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_VANZARI);

            while (resultSet.next()) {
                Vanzari vanzari = new Vanzari();
                vanzari.setIdJucarie(resultSet.getInt("ID_Jucarie"));
                vanzari.setDataVanzare(resultSet.getDate("Data_Vanzare"));
                vanzari.setCantitateaVanduta(resultSet.getInt("Cantitate_Vanduta"));

                vanzaris.add(vanzari);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vanzaris;
    }

    @Override
    public Vanzari findById(Integer id) {
        Vanzari vanzari = new Vanzari();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_VANZARI_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vanzari.setIdJucarie(resultSet.getInt("ID_Jucarie"));
                vanzari.setDataVanzare(resultSet.getDate("Data_Vanzare"));
                vanzari.setCantitateaVanduta(resultSet.getInt("Cantitate_Vanduta"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vanzari;
    }

    @Override
    public void update(Integer id, Vanzari entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_VANZARI)) {
            preparedStatement.setInt(1, entity.getIdJucarie());
            preparedStatement.setDate(2, entity.getDataVanzare());
            preparedStatement.setInt(3, entity.getCantitateaVanduta());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Vanzari entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_VANZARI)) {
            preparedStatement.setInt(1, entity.getIdJucarie());
            preparedStatement.setDate(2, entity.getDataVanzare());
            preparedStatement.setInt(3, entity.getCantitateaVanduta());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectProduseVanduteInZi(String data) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_VANZARI_ZI)) {
            preparedStatement.setString(1, data);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int numarProduseVandute = resultSet.getInt("NumarProduseVandute");
                System.out.println("Numarul total de jucarii vandute in data " + data + " este: " + numarProduseVandute);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
