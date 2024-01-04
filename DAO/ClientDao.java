/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Modele.Client;

public class ClientDao {

    private ClientDao() {
    }

     /*
     * Ajoute un nouveau client à la base de données.
     */
    public static int add(Client client) {
        // Initialisation des variables de connexion à la base de données et des résultats de requête
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        // Variable pour stocker le résultat de l'opération (par défaut, -1 indique un échec)
        int ret = -1;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Préparer la requête SQL
            String sql = "INSERT INTO client VALUES (DEFAULT,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, client.getClientNumber());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setString(3, client.getFirstname());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress());

            // Exécuter la requête
            ret = preparedStatement.executeUpdate();
        // Gérer les erreurs SQL en affichant un message d'erreur
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, resultSet);
        }
        return ret;
    }
    
     /*
     * Supprime un client de la base de données en utilisant son identifiant.
     */
    public static int delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Préparer la requête SQL
            String sql = "DELETE FROM client WHERE Client_ID = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Définir les valeurs des paramètres à partir des méthodes getter
            preparedStatement.setInt(1, id);

            // Exécuter la requête
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, null);
        }

        // Vérifier le nombre de lignes affectées pour déterminer le succès de la suppression
        if (rowsAffected > 0) {
            // La suppression a réussi, retourne un code de succès (par exemple, 1)
            return 1;
        } else {
            // Aucune ligne supprimée, donc le client n'existait probablement pas
            return -1;
        }
    }
    
     /*
     * Récupère un client de la base de données en utilisant son identifiant.
     */

    public static Client readById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Client client = null;

    try {
        // Ouvrir la connexion
        connection = Dao.connectDatabase();

        // Préparer la requête SQL
        String sql = "SELECT * FROM client WHERE Client_ID = ?";
        preparedStatement = connection.prepareStatement(sql);

        // Définir le paramètre
        preparedStatement.setInt(1, id);

        // Exécuter la requête
        resultSet = preparedStatement.executeQuery();
        
        // Vérifier si le résultat de la requête contient une ligne (un enregistrement)
        if (resultSet.next()) {
            // Extraire les valeurs des colonnes du résultat de la requête
            int clientId = resultSet.getInt("Client_ID");
            int clientNumber = resultSet.getInt("client_Number");
            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            // Créer une nouvelle instance de Client avec les valeurs extraites
            client = new Client(clientId, clientNumber, lastname, firstname, email, address);
        }
    // Gérer les erreurs SQL en affichant un message d'erreur
    } catch (SQLException e) {
        System.out.println("ERREUR : " + e);
    } finally {
        // Fermer les ressources
        Dao.closeResources(connection, preparedStatement, resultSet);
    }

    return client;
}
    
     /*
     * Récupère tous les clients de la base de données.
     */

    public static List<Client> readAll() {
        
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Client> clients = new ArrayList<>();

    try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Préparer la requête SQL
            String sql = "SELECT * FROM client";
            preparedStatement = connection.prepareStatement(sql);

            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();

            // Itération à travers les résultats de la requête
        while (resultSet.next()) {
            // Récupération des valeurs de chaque colonne pour un client
            int clientId = resultSet.getInt("Client_ID");
            int clientNumber = resultSet.getInt("client_Number");
            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            // Création d'un objet Client avec les informations récupérées
            Client client = new Client(clientId, clientNumber, lastname, firstname, email, address);
            // Ajout du client à la liste
            clients.add(client);
        }
    // Gérer les erreurs SQL en affichant un message d'erreur
    } catch (SQLException e) {
        System.out.println("ERREUR : " + e);
    }

    return clients;
}
    

     /*
     * Met à jour les informations d'un client dans la base de données.
     */
    public static int update(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = -1;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Préparer la requête SQL
            String sql = "UPDATE client SET client_Number=?, lastname=?, firstname=?, email=?, address=? WHERE Client_ID=?";
            preparedStatement = connection.prepareStatement(sql);

            // Définir les valeurs des paramètres à partir des méthodes getter
            preparedStatement.setInt(1, client.getClientNumber());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setString(3, client.getFirstname());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress());
            preparedStatement.setInt(6, client.getId());

            // Exécuter la requête
            ret = preparedStatement.executeUpdate();
        // Gérer les erreurs SQL en affichant un message d'erreur
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, resultSet);
        }
        return ret;
}
}