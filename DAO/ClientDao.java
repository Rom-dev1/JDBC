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
     * Ajoute un nouveau client a la base de donnees.
     */
    public static int add(Client client) {
        // Initialisation des variables de connexion a la base de donnees et des resultats de requete
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        // Variable pour stocker le resultat de l'operation (par defaut, -1 indique un echec)
        int ret = -1;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Preparer la requete SQL
            String sql = "INSERT INTO client VALUES (DEFAULT,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, client.getClientNumber());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setString(3, client.getFirstname());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress());

            // ExÃ©cuter la requete
            ret = preparedStatement.executeUpdate();
        // Gerer les erreurs SQL en affichant un message d'erreur
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, resultSet);
        }
        return ret;
    }
    
     /*
     * Supprime un client de la base de donnees en utilisant son identifiant.
     */
    public static int delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Preparer la requete SQL
            String sql = "DELETE FROM client WHERE Client_ID = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Definir les valeurs des parametres a partir des methodes getter
            preparedStatement.setInt(1, id);

            // Executer la requete
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, null);
        }

        // Verifier le nombre de lignes affectees pour determiner le succes de la suppression
        if (rowsAffected > 0) {
            // La suppression a reussi, retourne un code de succes (par exemple, 1)
            return 1;
        } else {
            // Aucune ligne supprimee, donc le client n'existait probablement pas
            return -1;
        }
    }
    
     /*
     * Récupere un client de la base de donnees en utilisant son identifiant.
     */

    public static Client readById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Client client = null;

    try {
        // Ouvrir la connexion
        connection = Dao.connectDatabase();

        // Preparer la requete SQL
        String sql = "SELECT * FROM client WHERE Client_ID = ?";
        preparedStatement = connection.prepareStatement(sql);

        // Définir le parametre
        preparedStatement.setInt(1, id);

        // Exécuter la requete
        resultSet = preparedStatement.executeQuery();
        
        // Verifier si le resultat de la requete contient une ligne (un enregistrement)
        if (resultSet.next()) {
            // Extraire les valeurs des colonnes du resultat de la requete
            int clientId = resultSet.getInt("Client_ID");
            int clientNumber = resultSet.getInt("client_Number");
            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            // Creer une nouvelle instance de Client avec les valeurs extraites
            client = new Client(clientId, clientNumber, lastname, firstname, email, address);
        }
    // Gerer les erreurs SQL en affichant un message d'erreur
    } catch (SQLException e) {
        System.out.println("ERREUR : " + e);
    } finally {
        // Fermer les ressources
        Dao.closeResources(connection, preparedStatement, resultSet);
    }

    return client;
}
    
     /*
     * Récupere tous les clients de la base de donnees.
     */

    public static List<Client> readAll() {
        
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Client> clients = new ArrayList<>();

    try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Preparer la requete SQL
            String sql = "SELECT * FROM client";
            preparedStatement = connection.prepareStatement(sql);

            // Executer la requete
            resultSet = preparedStatement.executeQuery();

            // Iteration a travers les resultats de la requete
        while (resultSet.next()) {
            // Récuperation des valeurs de chaque colonne pour un client
            int clientId = resultSet.getInt("Client_ID");
            int clientNumber = resultSet.getInt("client_Number");
            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            // Creation d'un objet Client avec les informations recuperees
            Client client = new Client(clientId, clientNumber, lastname, firstname, email, address);
            // Ajout du client a la liste
            clients.add(client);
        }
    // Gerer les erreurs SQL en affichant un message d'erreur
    } catch (SQLException e) {
        System.out.println("ERREUR : " + e);
    }

    return clients;
}
    

     /*
     * Met a jour les informations d'un client dans la base de donnees.
     */
    public static int update(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int ret = -1;

        try {
            // Ouvrir la connexion
            connection = Dao.connectDatabase();

            // Preparer la requete SQL
            String sql = "UPDATE client SET client_Number=?, lastname=?, firstname=?, email=?, address=? WHERE Client_ID=?";
            preparedStatement = connection.prepareStatement(sql);

            // Definir les valeurs des parametres a partir des methodes getter
            preparedStatement.setInt(1, client.getClientNumber());
            preparedStatement.setString(2, client.getLastname());
            preparedStatement.setString(3, client.getFirstname());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress());
            preparedStatement.setInt(6, client.getId());

            // Executer la requete
            ret = preparedStatement.executeUpdate();
        // Gerer les erreurs SQL en affichant un message d'erreur
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(connection, preparedStatement, resultSet);
        }
        return ret;
}
}