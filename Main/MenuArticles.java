/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DAO.ArticleDao;
import Modele.Article;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author Romain
 */
public class MenuArticles {
    
    /**
     * Affichage du menu concernant les articles 
     */
    
    public static void run(){
        
    Scanner scanner = new Scanner(System.in);

        boolean again = true;

        while(again){
            System.out.println("Menu:");
            System.out.println();
            System.out.println("1. Inserer un article");
            System.out.println("2. Lire tous les articles");
            System.out.println("3. Lire un article");
            System.out.println("4. Modifier un article");
            System.out.println("5. Supprimer un article");
            System.out.println("0. Menu principal");
            
            // verification si saisie est un chiffre
            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addMenu();
                        break;
                    case 2:
                        readAllMenu();
                        break;
                    case 3:
                        readOneMenu();
                        break;
                    case 4:
                        displayUpdateMenu();
                        break;
                    case 5:
                        deleteMenu();
                        break;
                    case 0:
                        System.out.println("Menu Principal");
                        again = false;
                        break;
                    default:
                        System.out.println();
                        System.out.println("Choix invalide. Veuillez reessayer.");
                        System.out.println();
                } 
            } else {
                System.out.println();
                System.out.println("Merci de saisir un chiffre coresspondant au choix du menu");  
                scanner.next();
            }
        }
    }
    
    /**
     * methode addMenu, affichage du menu ajout
     */
    private static void addMenu() {
        
        Scanner scanner = new Scanner(System.in);
        boolean state = false;
	boolean verifAnswer = false;
        String stateAnswer = null;
        boolean verifNumber = false;
        int articleNumber = 0;

        while(!verifNumber){
            System.out.println("Numero de l'article :");
            if(scanner.hasNextInt()){
                articleNumber = scanner.nextInt();
                // verification dans bdd si un article possedant le meme numero est déja présent
                if(ArticleDao.readOne(articleNumber) != null){
                    System.out.println();
                    System.out.println("Desole, un article a deja ce numero, merci de saisir un nouveau numero d'article..");
                    System.out.println();
                } else {
                    verifNumber = true;
                }
            } else {
                System.out.println("Merci de saisir un nombre : ");
                scanner.next();
            }
        }
        
        // verification du nombre de caractères minimum requis
        System.out.println("Titre de l'article : ");
        String lastname = scanner.next();
        while(verifLength(lastname)){
            
            System.out.println("Titre de l'article : ");
            lastname = scanner.next();
        }
        
        System.out.println("Description : ");
        String description = scanner.next();
        while(verifLength(description)){
            
            System.out.println("Description : ");
            description = scanner.next();
        }
        
        // verification de saisie pour l'etat
        while(!verifAnswer){
         
            System.out.println("Article achete ? (oui/non) ");
            stateAnswer = scanner.next().toLowerCase();

            if(stateAnswer.equals("oui")){
                state = true;
                verifAnswer = true;
            } else if (stateAnswer.equals("non")){
                state = false;
                verifAnswer = true;
            } else {
                System.out.println("Erreur de saisie, saisir \"oui\" ou \"non\".");
            } 
        }
        // cration de l'article et ajout de l'article en bdd
        Article art = new Article(articleNumber, lastname.trim(), description.trim(), state);
        ArticleDao.add(art);
    }
    
    private static void readAllMenu(){
        List<Article> articles = ArticleDao.readAll();
        for(Article article : articles){
            System.out.println(article);
            if(article.getState() == true ){
                System.out.println("Etat : achete");
            } else {
                System.out.println("Etat : vendu");
            }
            System.out.println("----------------");
        }
    }
    
    /**
     * 
     * Methode update et affichage des champs a modifier
     */
    
    private static void updateMenu(int searchArticleNumber){
        
        Scanner scanner = new Scanner(System.in);
        boolean verifAnswer = false;
        String stateAnswer = null;
        boolean state = false;
        String lastname = null;
        String description = null;
        
        System.out.println("Titre de l'article : ");
        lastname = scanner.next();
        while(verifLength(lastname)){
            System.out.println("Titre de l'article : ");
            lastname = scanner.next();
        }
     
        System.out.println("Description : ");
        description = scanner.next();
        while(verifLength(description)){
            System.out.println("Description : ");
            description = scanner.next();
        }

        while(!verifAnswer){
         
            System.out.println("Article achete ? (oui/non) ");
            stateAnswer = scanner.next().toLowerCase();

            if(stateAnswer.equals("oui")){
                state = true;
                verifAnswer = true;
            } else if (stateAnswer.equals("non")){
                state = false;
                verifAnswer = true;
            } else {
                System.out.println("Erreur de saisie, saisir \"oui\" ou \"non\".");
            } 
        }

        ArticleDao.update(lastname.trim(), description.trim(), state, searchArticleNumber);
    }
    
    /**
     * methode displayUpdateMenu et verification si presence de l'article à modifier present en bdd
     */
    
    private static void displayUpdateMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean verifNumber = false;
        int searchArticleNumber = 0;
        System.out.println("Quel est le numero de l'article que vous souhaitez modifier ? ");
        while(!verifNumber){
            if(scanner.hasNextInt()){
            searchArticleNumber = scanner.nextInt();
                if(ArticleDao.readOne(searchArticleNumber) == null){
                    System.out.println();
                    System.out.println("Desole, aucun article ne coresspond a votre recherche");
                    System.out.println();
                    verifNumber = true;
                } else {
                    updateMenu(searchArticleNumber);
                    System.out.println();
                    System.out.println("Felecitation, article modifie avec succes");
                    System.out.println();
                    verifNumber = true;
                }
            } else {
                System.out.println("Erreur de saisie, merci de saisir un numero d'article :");
                scanner.next();
            }
        }
    }
    
    /**
     * methode readOneMenu et verification si choix de l'article à lire present en bdd
     */
    
    private static void readOneMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean displayState = true;
        int choiceDisplayArticle = 0;
        
        System.out.println("Quel numero article souhaitez vous consulter ? ");
        if(scanner.hasNextInt()){
            choiceDisplayArticle = scanner.nextInt();
            if(ArticleDao.readOne(choiceDisplayArticle) == null){
                System.out.println();
                System.out.println("Desole, aucun article ne coresspond a votre recherche");
                System.out.println();
            } else {
                System.out.println(ArticleDao.readOne(choiceDisplayArticle));
                displayState = ArticleDao.readOne(choiceDisplayArticle).getState();
                if(displayState == true){
                    System.out.println("etat = achete");
                } else {
                    System.out.println("etat = vendu");
                
                }
            }
        } else {
            System.out.println("Erreur de saisie, merci de rééssayer.");
            scanner.next();
        }
    }
    
    /**
     * methode deleteMenu et verification si choix de l'article present en bdd
     */
    
    private static void deleteMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean verifNumber = false;
        System.out.println("Quel numero article souhaitez vous supprimer ? ");
        if(scanner.hasNextInt()){
            int choiceDeleteArticle = scanner.nextInt();
            if(ArticleDao.readOne(choiceDeleteArticle) == null){
            System.out.println("Desole, aucun article ne coresspond a votre recherche");
            verifNumber = true;
            } else {
               ArticleDao.delete(choiceDeleteArticle);
               System.out.println();
               System.out.println("Felecitation, votre article a  ete supprime ");
               System.out.println();
            }
        } else {
            System.out.println("Erreur de saisie, merci de rééssayer avec un numero d'article.");
            scanner.next();
        }
    }
    
    /**
     * methode verification des saisies 
     */
    
    private static Boolean verifLength(String str){
        if(str.length() < 2 ){
            System.out.println("Erreur de saisie, minimun 2 caracteres.");
            return true;
        } else {
            return false;
        }
        
    }
}

