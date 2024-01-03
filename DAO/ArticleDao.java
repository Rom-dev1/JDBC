/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Romain
 */

import java.sql.*;
import Modele.Article;

public class ArticleDao {
    private ArticleDao() {
    }

    public static int add(Article art) {

        Connection isConnected = null;
        PreparedStatement ps = null;
        PreparedStatement psVerif = null;
        ResultSet resultSet = null;
        int request = -1;
        
        
        try {
            // Ouvrir la connexion
            isConnected = Dao.connectDatabase();

            // Préparer la requête SQL
            String sql = "INSERT INTO article VALUES (DEFAULT, ?, ?, ?, ?)";
            ps = isConnected.prepareStatement(sql);
            ps.setInt(1, art.getArticleNumber());
//            if(selectOne(art.getArticleNumber()).next()){
//                System.out.println("Désolé, un article contient déja le même numéro dans la base.");
//            } else {
                ps.setString(2, art.getLastname());
                ps.setString(3, art.getDescription());
                ps.setBoolean(4, art.getState());
                request = ps.executeUpdate();
                System.out.println("Félicitation, votre article à bien été sauvegardé !");
            //}
            
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(isConnected, ps, resultSet);
        }
        return request;
    }
    
    public static void readAll() {
        Connection isConnected = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int request = -1;
        
        try{
            isConnected = Dao.connectDatabase();
            statement = isConnected.createStatement();
            String sql = "SELECT * FROM article";
            resultSet = statement.executeQuery(sql);
            System.out.println("Liste des articles : ");
            while(resultSet.next()){
                int articleNumber = resultSet.getInt("Article_Number");
                String description = resultSet.getString("description");
                String lastname = resultSet.getString("lastname");
                Boolean state = resultSet.getBoolean("state");
                System.out.println("-------------------------------");
                System.out.println("Numéro de l'article : " + articleNumber);
                System.out.println("Titre : " + lastname );
                System.out.println("Decription : " + description );
                if(state == true ){
                    System.out.println("Etat : acheté");
                } else {
                    System.out.println("Etat : vendu");
                }
            }
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
    }
    
    public static Boolean readOne(int searchArticleNumber){
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try{ 
            if(selectOne(searchArticleNumber)){
                isConnected = Dao.connectDatabase();
                String sql = "SELECT * FROM article WHERE Article_Number=?";
                ps = isConnected.prepareStatement(sql);
                ps.setInt(1, searchArticleNumber);
                resultSet = ps.executeQuery();
                System.out.println("Votre article : ");
            }
            if(resultSet.next()){
                String description = resultSet.getString("description");
                String lastname = resultSet.getString("lastname");
                Boolean state = resultSet.getBoolean("state");
                System.out.println("Numéro de l'article : " + searchArticleNumber + ", titre : " + lastname + ", decription : " + description + ", état : " + state);
            }
             else {
                System.out.println("Désolé, aucun article avec ce numéro.");
            }
            
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        return false;
    }
    
    public static int update(String lastname, String description, Boolean state, int articleNumber){
        Connection isConnected = null;
        PreparedStatement ps = null;
        int request = -1;
        
        try{
            
                isConnected = Dao.connectDatabase();
                String sql = "UPDATE article SET lastname = ?, description = ?, state = ? WHERE Article_Number = ?";
                ps = isConnected.prepareStatement(sql);
                ps.setString(1, lastname);
                ps.setString(2, description);
                ps.setBoolean(3, state);
                ps.setInt(4, articleNumber);
                request = ps.executeUpdate();
                System.out.println(articleNumber);
            
            
        } catch (SQLException e){
            System.out.println("Erreur " + e);
        } 
        return request;
    }
    
    public static int delete(int articleNumber){
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int request = -1;
        
        try{
            isConnected = Dao.connectDatabase();
            String sql = "DELETE FROM article WHERE Article_Number=?";
            ps = isConnected.prepareStatement(sql);
            ps.setInt(1, articleNumber);
            request = ps.executeUpdate();
            System.out.println(" Votre article à été supprimé ");
            
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        
        return request;
    }
    
    // method to verify if article is in bdd
    public static Boolean selectOne(int articleNumber){
        
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean test = false;
        
        try{
            isConnected = Dao.connectDatabase();
            String verifArticleNumber = "SELECT * FROM article WHERE Article_Number = ?";
            ps = isConnected.prepareStatement(verifArticleNumber);
            ps.setInt(1, articleNumber);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                test = true;
            }
        } catch (SQLException e){
            System.out.println(e);
        }
        return test;
    }
}
