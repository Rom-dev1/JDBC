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
    
    public static void readAll() {
        Connection isConnected = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int request = -1;
        
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
                System.out.println("Numero de l'article : " + articleNumber);
                System.out.println("Titre : " + lastname );
                System.out.println("Decription : " + description );
                returnState(state);
                System.out.println("-------------------------------");
            }
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
    }
    
    /**
     * methode readOne pour lire un article en bdd
     */
    
    public static Boolean readOne(int searchArticleNumber){
        
        Connection isConnected = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try{ 
            if(selectOne(searchArticleNumber)){
                // connection bdd
                isConnected = Dao.connectDatabase();
                
                String sql = "SELECT * FROM article WHERE Article_Number=?";
                ps = isConnected.prepareStatement(sql);
                
                // affection parametre
                ps.setInt(1, searchArticleNumber);
                
                // execution de la requete
                resultSet = ps.executeQuery();
                System.out.println("Vous avez choisi l'article numero : " + searchArticleNumber);
                if(resultSet.next()){
                    String description = resultSet.getString("description");
                    String lastname = resultSet.getString("lastname");
                    Boolean state = resultSet.getBoolean("state");
                    System.out.println("------------------------");
                    System.out.println("Numero de l'article : " + searchArticleNumber);
                    System.out.println("titre : " + lastname );
                    System.out.println("decription : " + description);
                    returnState(state);
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("Desole, aucun article ne coresspond à votre recherche");
            }
        } catch(SQLException e){
            System.out.println("ERREUR : " + e);
        } 
        return false;
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
            System.out.println("Felecitation, votre article a  ete supprime ");
            
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
    
    // Methode pour l'affichage etat
    private static void returnState(boolean state){
        if(state == true ){
            System.out.println("Etat : achete");
        } else {
            System.out.println("Etat : vendu");
        }
    }
}
