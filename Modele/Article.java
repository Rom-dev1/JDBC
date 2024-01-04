package Modele;

import java.io.Serializable;

public class Article implements Serializable {
    
    private int id;
    private int articleNumber;
    private String lastname;
    private String description;
    private Boolean state;
    
    public Article(){
    }
    
    public Article(int articleNumber, String lastname, String description, Boolean state){
        this.articleNumber = articleNumber;
        this.lastname = lastname;
        this.description = description;
        this.state = state;
    } 
    
    public Article(int id, int articleNumber, String lastname, String description, Boolean state){
        this.id = id;
        this.articleNumber = articleNumber;
        this.lastname = lastname;
        this.description = description;
        this.state = state;
    } 
     // Getter and Setter for 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for 'articleNumber'
    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    // Getter and Setter for 'lastname'
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Getter and Setter for 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for 'state'
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    
    @Override
    public String toString() {
        return "Article numéro : " + articleNumber + ", titre: " + lastname + ", description : " + description;
    }
}