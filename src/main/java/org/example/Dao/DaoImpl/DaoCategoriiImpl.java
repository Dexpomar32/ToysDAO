package org.example.Dao.DaoImpl;

import org.example.ConnectionCreator;
import org.example.Dao.DaoModels.DaoCategorii;
import org.example.Models.Categorii;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoriiImpl implements DaoCategorii {
    private static final String SQL_SELECT_ALL_CATEGORII = "SELECT * FROM Categorii";
    private static final String SQL_SELECT_CATEGORII_BY_ID = "SELECT * FROM Categorii WHERE ID_Categorie = ?";
    private static final String SQL_UPDATE_CATEGORII = "UPDATE Categorii SET Nume_Categorie = ? WHERE ID_Categorie = ?";
    private static final String SQL_DELETE = "DELETE FROM Categorii WHERE ID_Categorie = ?";
    private static final String SQL_INSERT_INTO_CATEGORII = "INSERT INTO Jucarii(Nume_Categorie) VALUES (?)";
    private static Connection connection;

    public DaoCategoriiImpl() {
        connection = ConnectionCreator.createConnection();
    }

    @Override
    public List<Categorii> findAll() {
        List<Categorii> categories = new ArrayList<>();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CATEGORII);

            while (resultSet.next()) {
                Categorii categorii = new Categorii();
                categorii.setNumeCategorie(resultSet.getString("Nume_Categorie"));

                categories.add(categorii);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Categorii findById(Integer id) {
        Categorii categorii = new Categorii();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORII_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categorii.setNumeCategorie(resultSet.getString("Nume_Categorie"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorii;
    }

    @Override
    public void update(Integer id, Categorii entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CATEGORII)) {
            preparedStatement.setString(1, entity.getNumeCategorie());
            preparedStatement.setInt(2, id);

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
    public void insert(Categorii entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CATEGORII)) {
            preparedStatement.setString(1, entity.getNumeCategorie());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
