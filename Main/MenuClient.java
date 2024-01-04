/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author legse
 */

import DAO.ClientDao;
import Modele.Client;

import java.util.List;
import java.util.Scanner;

public class MenuClient {

     /**
     * Methode principale pour executer le menu de gestion des clients.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        
        // Boucle pour afficher le menu et traiter les choix de l'utilisateur
        while (true) {
            System.out.println("\nMenu Clients");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Lire un client par ID");
            System.out.println("4. Lire tous les clients");
            System.out.println("5. Mettre a jour un client");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (0-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            // Utilisation d'une structure switch pour gerer les differentes options du menu
            switch (choice) {
                case 1:
                    addClient(scanner);
                    break;
                case 2:
                    deleteClient(scanner);
                    break;
                case 3:
                    readClientById(scanner);
                    break;
                case 4:
                    readAllClients();
                    break;
                case 5:
                    updateClient(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }
    }

        /**
     * Methode pour lire un client par ID.
     * Appel de la methode readById de la classe ClientDao au sein de la methode
     */
    private static void readClientById(Scanner scanner) {
        System.out.print("Saisissez l'ID du client que vous souhaitez consulter : ");
        int clientIdToRead = scanner.nextInt();
        Client clientById = ClientDao.readById(clientIdToRead);
        if (clientById != null) {
            System.out.println("Informations sur le client " + clientById);
        } else {
            System.out.println("Client non trouve avec l'id " + clientIdToRead);
        }
    }

     /**
     * Methode pour afficher tous les clients.
     * Avec appel de la methode readAll de la classe ClientDao
     */
    private static void readAllClients() {
        List<Client> allClients = ClientDao.readAll();
        for (Client client : allClients) {
            System.out.println(client);
        }
    }
    
     /**
     * Methode pour supprimer un client par ID.
     * Avec appel de la methode delete de la classe ClientDao
     */
    private static void deleteClient(Scanner scanner) {
    System.out.print("Saisissez l'ID du client que vous souhaitez supprimer : ");
    int clientIdToDelete = scanner.nextInt();
    int result = ClientDao.delete(clientIdToDelete);
    if (result != -1) {
        System.out.println("Client supprime avec succes !");
    } else {
        System.out.println("echec de la suppression du client.");
    }
}
    
    
    
    /**
     * Methode pour mettre a jour un client.
     * Avec appel de la methode update de la classe ClientDao
     */
    private static void updateClient(Scanner scanner) {
    System.out.print("Saisissez l'ID du client que vous souhaitez mettre a jour : ");
    int clientIdToUpdate = scanner.nextInt();
    scanner.nextLine(); // Pour consommer la nouvelle ligne

    // Lire les informations actuelles du client a partir de la base de donnees
    Client clientToUpdate = ClientDao.readById(clientIdToUpdate);

    if (clientToUpdate != null) {
        System.out.println("Anciennes informations du client : " + clientToUpdate);

        // Booléen indiquant si la mise à jour du client est terminée ou non
        boolean updateCompleted = false;

        while (!updateCompleted) {
            // Affichez un menu pour demander quels champs l'utilisateur souhaite mettre a jour
            System.out.println("\nSelectionnez les champs a mettre a jour :");
            System.out.println("1. Numero du client");
            System.out.println("2. Nom du client");
            System.out.println("3. Prenom du client");
            System.out.println("4. Email du client");
            System.out.println("5. Adresse du client");
            System.out.println("0. Annuler et quitter la mise a jour");

            System.out.print("Choisissez une option (0-5) : ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.print("Nouveau numero du client : ");
                    int newClientNumber = scanner.nextInt();
                    clientToUpdate.setClientNumber(newClientNumber);
                    break;
                case 2:
                    String newLastname;
                    do {
                        System.out.print("Nouveau nom du client : ");
                        newLastname = scanner.nextLine().trim();
                        if (newLastname.isEmpty()) {
                            System.out.println("Erreur : Le nom ne peut pas etre vide. Veuillez reessayer.");
                        }
                    } while (newLastname.isEmpty());
                    clientToUpdate.setLastname(newLastname);
                    break;
                case 3:
                    String newFirstname;
                    do {
                        System.out.print("Nouveau prenom du client : ");
                        newFirstname = scanner.nextLine().trim();
                        if (newFirstname.isEmpty()) {
                            System.out.println("Erreur : Le prenom ne peut pas etre vide. Veuillez reessayer.");
                        }
                    } while (newFirstname.isEmpty());
                    clientToUpdate.setFirstname(newFirstname);
                    break;
                case 4:
                    String newEmail;
                    do {
                        System.out.print("Nouvel email du client : ");
                        newEmail = scanner.nextLine().trim();
                        if (newEmail.isEmpty()) {
                            System.out.println("Erreur : L'email ne peut pas etre vide. Veuillez reessayer.");
                        } else if (!isValidEmail(newEmail)) {
                            System.out.println("Erreur : Le format de l'email n'est pas valide. Veuillez reessayer.");
                        }
                    } while (newEmail.isEmpty() || !isValidEmail(newEmail));
                    clientToUpdate.setEmail(newEmail);
                    break;
                case 5:
                    String newAddress;
                    do {
                        System.out.print("Nouvelle adresse du client : ");
                        newAddress = scanner.nextLine().trim();
                        if (newAddress.isEmpty()) {
                            System.out.println("Erreur : L'adresse ne peut pas etre vide. Veuillez reessayer.");
                        }
                    } while (newAddress.isEmpty());
                    clientToUpdate.setAddress(newAddress);
                    break;
                case 0:
                    System.out.println("Mise a jour annulee.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
                    break;
            }

            System.out.print("Voulez-vous mettre a jour un autre champ ? (O/N) : ");
            // Recupere la reponse de l'utilisateur en convertissant les espaces inutiles, met en majuscules pour une comparaison insensible a la casse
            String continueUpdate = scanner.nextLine().trim().toUpperCase();

            // Verifie si l'utilisateur ne souhaite pas continuer la mise a jour en comparant la réponse a "O" (non-insensible a la casse)
            if (!continueUpdate.equals("O")) {
                updateCompleted = true;
            }
        }

        // Appelez la methode update du ClientDao
        int result = ClientDao.update(clientToUpdate);
        if (result != -1) {
            System.out.println("Client mis a jour avec succes !");
        } else {
            System.out.println("echec de la mise a jour du client.");
        }
    } else {
        System.out.println("Client non trouve avec l'ID " + clientIdToUpdate);
    }
}

    
    
    /**
     * Methode pour ajouter un nouveau client.
     * Avec appel de la methode add de la classe ClientDao
     */
    private static void addClient(Scanner scanner) {
    int clientNumber;
    String lastName, firstName, email, address;

    // Saisir le numéro du client
    System.out.print("Numero du client : ");
    clientNumber = scanner.nextInt();
    scanner.nextLine(); // Pour consommer la nouvelle ligne

    // Saisir le nom du client
    do {
        System.out.print("Nom du client : ");
        lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("Erreur : Le nom ne peut pas etre vide. Veuillez reessayer.");
        }
    } while (lastName.isEmpty());

    // Saisir le prenom du client
    do {
        System.out.print("Prenom du client : ");
        firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()) {
            System.out.println("Erreur : Le prenom ne peut pas etre vide. Veuillez reessayer.");
        }
    } while (firstName.isEmpty());

    // Saisir l'email du client
    do {
        System.out.print("Email du client : ");
        email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("Erreur : L'email ne peut pas etre vide. Veuillez reessayer.");
        } else if (!isValidEmail(email)) {
            System.out.println("Erreur : Le format de l'email n'est pas valide. Veuillez reessayer.");
        }
    } while (email.isEmpty() || !isValidEmail(email));

    // Saisir l'adresse du client
    do {
        System.out.print("Adresse du client : ");
        address = scanner.nextLine().trim();

        if (address.isEmpty()) {
            System.out.println("Erreur : L'adresse ne peut pas etre vide. Veuillez reessayer.");
        }
    } while (address.isEmpty());

    // Creation d'un nouvel objet Client avec les informations saisies
    Client newClient = new Client(clientNumber, lastName, firstName, email, address);

    // Appelez la methode add du ClientDao
    int result = ClientDao.add(newClient);
    if (result != -1) {
        System.out.println("Client ajoute avec succes !");
    } else {
        System.out.println("echec de l'ajout du client.");
    }
}
    
    
    
    
    
    // Ajouter cette methode pour verifier le format de l'email
    private static boolean isValidEmail(String email) {
        // Utilisez une expression reguliere simple pour verifier le format de l'email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(emailRegex);
    }
}

