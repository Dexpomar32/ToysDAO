package org.example.Dao.DaoImpl;

import org.example.ConnectionCreator;
import org.example.Dao.DaoModels.DaoPapusile;
import org.example.Models.Papusile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPapusileImpl implements DaoPapusile {
    private static final String SQL_SELECT_ALL_PAPUSILE = "SELECT * FROM Papusile";
    private static final String SQL_SELECT_PAPUSILE_BY_ID = "SELECT * FROM Papusile WHERE ID_Papusa = ?";
    private static final String SQL_UPDATE_PAPUSILE = "UPDATE Papusile SET ID_Jucarie = ?, Material = ?, "
            + "Inaltime = ? WHERE ID_Papusa = ?";
    private static final String SQL_DELETE = "DELETE FROM Papusile WHERE ID_Papusa = ?";
    private static final String SQL_INSERT_INTO_PAPUSILE = "INSERT INTO Papusile(ID_Jucarie, Material, " +
            "Inaltime) VALUES (?, ?, ?)";
    private static final String SQL_PAPUSI_ASC = "SELECT * FROM Papusile INNER JOIN Jucarii ON " +
            "Papusile.ID_Jucarie = Jucarii.ID_Jucarie ORDER BY Jucarii.Pret ASC";
    private static Connection connection;

    public DaoPapusileImpl() {
        connection = ConnectionCreator.createConnection();
    }

    @Override
    public List<Papusile> findAll() {
        List<Papusile> papusiles = new ArrayList<>();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_PAPUSILE);

            while (resultSet.next()) {
                Papusile papusile = new Papusile();
                papusile.setIdJucarie(resultSet.getInt("ID_Jucarie"));
                papusile.setMaterial(resultSet.getString("Material"));
                papusile.setInaltime(resultSet.getDouble("Inaltime"));

                papusiles.add(papusile);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return papusiles;
    }

    @Override
    public Papusile findById(Integer id) {
        Papusile papusile = new Papusile();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_PAPUSILE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                papusile.setIdJucarie(resultSet.getInt("ID_Jucarie"));
                papusile.setMaterial(resultSet.getString("Material"));
                papusile.setInaltime(resultSet.getDouble("Inaltime"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return papusile;
    }

    @Override
    public void update(Integer id, Papusile entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PAPUSILE)) {
            preparedStatement.setInt(1, entity.getIdJucarie());
            preparedStatement.setString(2, entity.getMaterial());
            preparedStatement.setDouble(3, entity.getInaltime());
            preparedStatement.setInt(6, id);

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
    public void insert(Papusile entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_PAPUSILE)) {
            preparedStatement.setInt(1, entity.getIdJucarie());
            preparedStatement.setString(2, entity.getMaterial());
            preparedStatement.setDouble(3, entity.getInaltime());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectPapusiAsc() {
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_PAPUSI_ASC);

            while (resultSet.next()) {
                String numeJucarie = resultSet.getString("Nume_Jucarie");
                double pret = resultSet.getDouble("Pret");
                String material = resultSet.getString("Material");
                double inaltime = resultSet.getDouble("Inaltime");

                System.out.println("Nume Jucarie: " + numeJucarie);
                System.out.println("Pret: " + pret);
                System.out.println("Material: " + material);
                System.out.println("Inaltime: " + inaltime);
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
