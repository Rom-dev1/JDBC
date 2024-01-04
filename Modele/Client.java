package Modele;

import java.io.Serializable;


/**
 * La classe Client représente un client avec des informations telles que le numéro, le nom, le prénom,
 * l'email et l'adresse.
 * Implemente l'interface Serializable pour permettre la sérialisation des objets de cette classe.
 */

public class Client implements Serializable {

    private int id;
    private int clientNumber;
    private String lastname;
    private String firstname;
    private String email;
    private String address;

    public Client() {
    }

    
        /**
     * Constructeur pour créer un nouveau client sans spécifier l'ID.
     * @param clientNumber Numéro du client.
     * @param lastname Nom du client.
     * @param firstname Prénom du client.
     * @param email Email du client.
     * @param address Adresse du client.
     */
    public Client(int clientNumber, String lastname, String firstname, String email, String address) {
        this.clientNumber = clientNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
    }

        /**
     * Constructeur pour créer un nouveau client en spécifiant l'ID.
     * @param id Identifiant unique du client.
     * @param clientNumber Numéro du client.
     * @param lastname Nom du client.
     * @param firstname Prénom du client.
     * @param email Email du client.
     * @param address Adresse du client.
     */
    public Client(int id, int clientNumber, String lastname, String firstname, String email, String address) {
        this.id = id;
        this.clientNumber = clientNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
    }

    
    // Getters et setters pour accéder et modifier les attributs privés de la classe
    public int getId() {
        return id;
    }

    public int getClientNumber() {
        return clientNumber;
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

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
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

    public void setAddress(String address) {
        this.address = address;
    }

    
     // Méthode pour obtenir une représentation textuelle du client
    @Override
    public String toString() {
        return "Client N° " + clientNumber + " (id " + id + ") : " + lastname + " " + firstname + ", " + email + ", " + address + '.';
    }
}
