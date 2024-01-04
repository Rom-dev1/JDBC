package DAO;

import Modele.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe gère les opérations liées à la table _user dans la base de données.
 */
public class UserDao {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    // Requêtes SQL
    private static final String SELECT_ALL_USERS = "SELECT * FROM _user";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM _user WHERE user_ID=?";
    private static final String INSERT_USER = "INSERT INTO _user (user_Number, login, password, lastname, firstname, email) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE _user SET user_Number=?, login=?, password=?, lastname=?, firstname=?, email=? WHERE user_ID=?";
    private static final String DELETE_USER = "DELETE FROM _user WHERE user_ID=?";

    /**
     * Récupère la liste de tous les utilisateurs dans la base de données.
     *
     * @return Liste d'utilisateurs
     */
    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de tous les utilisateurs : " + e.getMessage());
        }

        return userList;
    }

    /**
     * Récupère un utilisateur à partir de l'identifiant spécifié.
     *
     * @param userId Identifiant de l'utilisateur
     * @return Utilisateur correspondant à l'identifiant
     */
    public static User getUserById(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return extractUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'utilisateur par ID : " + e.getMessage());
        }

        return null;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param user Utilisateur à ajouter
     */
    public static void addUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, user.getUserNumber());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getFirstname());
            preparedStatement.setString(6, user.getEmail());

            preparedStatement.executeUpdate();

            // Récupérer l'ID généré pour l'utilisateur ajouté
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                user.setUserId(generatedId);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout d'un utilisateur : " + e.getMessage());
        }
    }

    /**
     * Met à jour les informations de l'utilisateur dans la base de données.
     *
     * @param user Utilisateur à mettre à jour
     */
    public static void updateUser(User user) {
        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            preparedStatement.setInt(1, user.getUserNumber());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getFirstname());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, user.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour d'un utilisateur : " + e.getMessage());
        }
    }

    /**
     * Supprime l'utilisateur de la base de données en utilisant son identifiant.
     *
     * @param userId Identifiant de l'utilisateur à supprimer
     */
    public static void deleteUser(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression d'un utilisateur : " + e.getMessage());
        }
    }

    // Méthode utilitaire pour extraire un utilisateur d'un ResultSet
    private static User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_ID");
        int userNumber = resultSet.getInt("user_Number");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String lastName = resultSet.getString("lastname");
        String firstName = resultSet.getString("firstname");
        String email = resultSet.getString("email");

        return new User(userId, userNumber, login, password, lastName, firstName, email);
    }
}
