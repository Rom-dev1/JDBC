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
import java.util.List;
import java.util.ArrayList;

public class ArticleDao {
    private ArticleDao() {
    }
    
    /**
     * methode add pour ajouter un article en bbd
     */
    
    public static int add(Article art) {

        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int request = -1;
        
        try {
            // Ouvrir la connexion
            isConnected = Dao.connectDatabase();

            // PrÃ©parer la requÃªte SQL
            String sql = "INSERT INTO article VALUES (DEFAULT, ?, ?, ?, ?)";
            ps = isConnected.prepareStatement(sql);
            ps.setInt(1, art.getArticleNumber());
            ps.setString(2, art.getLastname());
            ps.setString(3, art.getDescription());
            ps.setBoolean(4, art.getState());
            request = ps.executeUpdate();
            System.out.println();
            System.out.println("Felicitation, votre article a  bien ete sauvegarde !");
            System.out.println();
            
        } catch (SQLException e) {
            System.out.println("ERREUR : " + e);
        } finally {
            // Fermer les ressources
            Dao.closeResources(isConnected, ps, resultSet);
        }
        return request;
    }
    
    /**
     * methode readAll pour lire tous les articles en bbd
     */
    
    public static List<Article> readAll() {
        Connection isConnected = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        
        try{
            isConnected = Dao.connectDatabase();
            statement = isConnected.createStatement();
            String sql = "SELECT * FROM article ORDER BY Article_Number ASC";
            resultSet = statement.executeQuery(sql);
            System.out.println("Liste des articles : ");
            System.out.println();
            while(resultSet.next()){
                int articleNumber = resultSet.getInt("Article_Number");
                String description = resultSet.getString("description");
                String lastname = resultSet.getString("lastname");
                Boolean state = resultSet.getBoolean("state");
                Article article = new Article(articleNumber, lastname, description, state);
                articles.add(article);
            }
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        return articles;
    }
    
    /**
     * methode readOne pour lire un article en bdd
     */
    
    public static Article readOne(int searchArticleNumber){
        
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Article article = null;
        
        try{ 
            // connection bdd
            isConnected = Dao.connectDatabase();

            String sql = "SELECT * FROM article WHERE Article_Number=?";
            ps = isConnected.prepareStatement(sql);

            // affection parametre
            ps.setInt(1, searchArticleNumber);

            // execution de la requete
            resultSet = ps.executeQuery();

            if(resultSet.next()){
                String description = resultSet.getString("description");
                String lastname = resultSet.getString("lastname");
                Boolean state = resultSet.getBoolean("state");
                article = new Article(searchArticleNumber, lastname, description, state);

            } 
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        return article;
    }
    
    /**
     * methode update pour modifier un article en bdd
     */
    
    public static int update(String lastname, String description, Boolean state, int articleNumber){

        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
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
            System.out.println();
            System.out.println("Felicitation, votre article à bien ete modifie");
            System.out.println();
        } catch (SQLException e){
            System.out.println("Erreur " + e);
        } finally {
            Dao.closeResources(isConnected, ps, resultSet);
        }
        return request;
    }
    
    /**
     * methode pour supprimer un article de la bdd
     */
    
    public static int delete(int articleNumber){
        
        Connection isConnected = null;
        PreparedStatement ps = null;
      
        int request = -1;
        
        try{
            isConnected = Dao.connectDatabase();
            String sql = "DELETE FROM article WHERE Article_Number=?";
            ps = isConnected.prepareStatement(sql);
            ps.setInt(1, articleNumber);
            request = ps.executeUpdate();
            
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        return request;
    }
    
    /**
     * methode pour verifier si presence d'un article dans la bdd 
     */
    
    public static Boolean selectOne(int articleNumber){
        
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean verif = false;
        
        try{
            isConnected = Dao.connectDatabase();
            String verifArticleNumber = "SELECT * FROM article WHERE Article_Number = ?";
            ps = isConnected.prepareStatement(verifArticleNumber);
            ps.setInt(1, articleNumber);
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                verif = true;
            }
        } catch (SQLException e){
            System.out.println(e);
        } finally {
            Dao.closeResources(isConnected, ps, resultSet);
        }
        return verif;
    }
}
