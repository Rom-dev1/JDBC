package Modele;

public class User {
    // Attributs de la classe
    private int userId;         // Identifiant de l'utilisateur
    private int userNumber;     // Numéro de l'utilisateur
    private String login;       // Nom d'utilisateur
    private String password;    // Mot de passe
    private String lastname;    // Nom de familleé
    private String firstname;   // Prénom
    private String email;       // Adresse email

    // Constructeur pour initialiser un utilisateur
    public User(int userId, int userNumber, String login, String password, String lastname, String firstname, String email) {
        this.userId = userId;
        this.userNumber = userNumber;
        this.login = login;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
    }

    // Méthodes getter pour récupérer les valeurs des attributs
    public int getUserId() {
        return userId;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    // Méthodes setter pour mettre à jour les valeurs des attributs
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Méthode toString pour obtenir une représentation sous forme de chaîne de caractères de l'objet User
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userNumber=" + userNumber + ", login=" + login + ", password=" + password + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email + '}';
    }
}
