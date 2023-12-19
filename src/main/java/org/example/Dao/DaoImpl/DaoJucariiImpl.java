package org.example.Dao.DaoImpl;

import org.example.ConnectionCreator;
import org.example.Dao.DaoModels.DaoJucarii;
import org.example.Models.Jucarii;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoJucariiImpl implements DaoJucarii {
    private static final String SQL_SELECT_ALL_JUCARII = "SELECT * FROM Jucarii";
    private static final String SQL_SELECT_JUCARII_BY_ID = "SELECT * FROM Jucarii WHERE ID_Jucarie = ?";
    private static final String SQL_UPDATE_JUCARII = "UPDATE Jucarii SET Nume_Jucarie = ?, Pret = ?, "
            + "Cantitate = ?, Tara_Productie = ?, Varsta_Minima = ? WHERE ID_Jucarie = ?";
    private static final String SQL_DELETE = "DELETE FROM Jucarii WHERE ID_Jucarie = ?";
    private static final String SQL_INSERT_INTO_JUCARII = "INSERT INTO Jucarii(Nume_Jucarie, Pret, " +
            "Cantitate, Tara_Productie, Varsta_Minima) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_MOVE_TOYS_WITH_ZERO_QUANTITY = "CALL EliminaSiMutaJucariiZeroCantitate()";
    private static final String SQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY = "SELECT Nume_Jucarie, Pret, Cantitate, " +
            "Tara_Productie, Varsta_Minima " +
            "FROM Jucarii " +
            "WHERE Pret = (SELECT MAX(Pret) FROM Jucarii) " +
            "OR Pret = (SELECT MIN(Pret) FROM Jucarii)";
    private static final String SQL_SELECT_TOYS_AVG_DUPA_TARA = "SELECT AVG(Pret) AS PretMediu FROM Jucarii WHERE Tara_Productie = ?";
    private static final String SQL_INSERT_JUCARIE_MONDOVA = "CALL CopiazaJucariiMoldova()";
    private static final String SQL_SELECT_TOYS_WITH_FILTERS = "SELECT * FROM Jucarii WHERE Pret <= ? " +
            "AND Varsta_Minima >= ? AND Varsta_Minima <= ?";
    private static final String SQL_SELECT_CELE_MAI_POPULARE_PRODUSE = "CALL ObtinereCeleMaiPopulareProduse()";
    private static Connection connection;
    private static final Integer MAX_SIZE = 2;

    public DaoJucariiImpl() {
        connection = ConnectionCreator.createConnection();
    }

    @Override
    public List<Jucarii> findAll() {
        List<Jucarii> toys = new ArrayList<>();
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_JUCARII);

            while (resultSet.next()) {
                Jucarii jucarii = new Jucarii();
                jucarii.setNumeJucarie(resultSet.getString("Nume_Jucarie"));
                jucarii.setPret(resultSet.getDouble("Pret"));
                jucarii.setCantitate(resultSet.getInt("Cantitate"));
                jucarii.setTaraProductie(resultSet.getString("Tara_Productie"));
                jucarii.setVarstaMinima(resultSet.getInt("Varsta_Minima"));

                toys.add(jucarii);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toys;
    }

    @Override
    public Jucarii findById(Integer id) {
        Jucarii jucarii = new Jucarii();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_JUCARII_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                jucarii.setNumeJucarie(resultSet.getString("Nume_Jucarie"));
                jucarii.setPret(resultSet.getDouble("Pret"));
                jucarii.setCantitate(resultSet.getInt("Cantitate"));
                jucarii.setTaraProductie(resultSet.getString("Tara_Productie"));
                jucarii.setVarstaMinima(resultSet.getInt("Varsta_Minima"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jucarii;
    }

    @Override
    public void update(Integer id, Jucarii entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_JUCARII)) {
            preparedStatement.setString(1, entity.getNumeJucarie());
            preparedStatement.setDouble(2, entity.getPret());
            preparedStatement.setInt(3, entity.getCantitate());
            preparedStatement.setString(4, entity.getTaraProductie());
            preparedStatement.setInt(5, entity.getVarstaMinima());
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
    public void insert(Jucarii entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_JUCARII)) {
            preparedStatement.setString(1, entity.getNumeJucarie());
            preparedStatement.setDouble(2, entity.getPret());
            preparedStatement.setInt(3, entity.getCantitate());
            preparedStatement.setString(4, entity.getTaraProductie());
            preparedStatement.setInt(5, entity.getVarstaMinima());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excludeJucariiCuCantitateZero() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_MOVE_TOYS_WITH_ZERO_QUANTITY)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afisareJucariiScumpeSiIeftine() {
        List<Jucarii> toys = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_EXPENSIVE_AND_CHEAPEST_TOY);

            while (resultSet.next()) {
                Jucarii jucarii = new Jucarii();
                jucarii.setNumeJucarie(resultSet.getString("Nume_Jucarie"));
                jucarii.setPret(resultSet.getDouble("Pret"));
                jucarii.setCantitate(resultSet.getInt("Cantitate"));
                jucarii.setTaraProductie(resultSet.getString("Tara_Productie"));
                jucarii.setVarstaMinima(resultSet.getInt("Varsta_Minima"));

                toys.add(jucarii);
            }

            if (toys.size() >= MAX_SIZE) {
                System.out.println("Cea mai ieftina: " + toys.get(0));
                System.out.println("Cea mai scumpa: " + toys.get(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectToysAVGTara(String tara) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_TOYS_AVG_DUPA_TARA);
            preparedStatement.setString(1, tara);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double pretMediu = resultSet.getDouble("PretMediu");
                System.out.println("Pretul mediu al jucariilor produse in " + tara + " este: " + pretMediu);
            } else {
                System.out.println("Nu exista jucarii produse in " + tara);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertJucarieMoldova() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_JUCARIE_MONDOVA);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectToysWithFilters(double x, int n1, int n2) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_TOYS_WITH_FILTERS)) {
            preparedStatement.setDouble(1, x);
            preparedStatement.setInt(2, n1);
            preparedStatement.setInt(3, n2);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idJucarie = resultSet.getInt("ID_Jucarie");
                String numeJucarie = resultSet.getString("Nume_Jucarie");
                double pret = resultSet.getDouble("Pret");
                int cantitate = resultSet.getInt("Cantitate");
                String taraProductie = resultSet.getString("Tara_Productie");
                int varstaMinima = resultSet.getInt("Varsta_Minima");

                System.out.println("ID: " + idJucarie);
                System.out.println("Nume Jucarie: " + numeJucarie);
                System.out.println("Pret: " + pret);
                System.out.println("Cantitate: " + cantitate);
                System.out.println("Tara de Productie: " + taraProductie);
                System.out.println("Varsta Minima: " + varstaMinima);
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void selectCeleMaiPopulareProduse() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CELE_MAI_POPULARE_PRODUSE)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String numeJucarie = resultSet.getString("Nume_Jucarie");
                int cantitateTotala = resultSet.getInt("CantitateTotala");
                System.out.println("Nume Jucarie: " + numeJucarie + ", Cantitate Totala: " + cantitateTotala);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}