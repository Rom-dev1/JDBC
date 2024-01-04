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
     * M√©thode principale pour ex√©cuter le menu de gestion des clients.
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
            System.out.println("5. Mettre ‡ jour un client");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (0-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            // Utilisation d'une structure switch pour g√©rer les diff√©rentes options du menu
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
                    System.out.println("Choix invalide. Veuillez r√©essayer.");
            }
        }
    }

        /**
     * M√©thode pour lire un client par ID.
     * Appel de la m√©thode readById de la classe ClientDao au sein de la m√©thode
     */
    private static void readClientById(Scanner scanner) {
        System.out.print("Saisissez l'ID du client que vous souhaitez consulter : ");
        int clientIdToRead = scanner.nextInt();
        Client clientById = ClientDao.readById(clientIdToRead);
        if (clientById != null) {
            System.out.println("Informations sur le client " + clientById);
        } else {
            System.out.println("Client non trouvÈ avec l'id " + clientIdToRead);
        }
    }

     /**
     * M√©thode pour afficher tous les clients.
     * Avec appel de la m√©thode readAll de la classe ClientDao
     */
    private static void readAllClients() {
        List<Client> allClients = ClientDao.readAll();
        for (Client client : allClients) {
            System.out.println(client);
        }
    }
    
     /**
     * M√©thode pour supprimer un client par ID.
     * Avec appel de la m√©thode delete de la classe ClientDao
     */
    private static void deleteClient(Scanner scanner) {
    System.out.print("Saisissez l'ID du client que vous souhaitez supprimer : ");
    int clientIdToDelete = scanner.nextInt();
    int result = ClientDao.delete(clientIdToDelete);
    if (result != -1) {
        System.out.println("Client supprimÈ avec succËs !");
    } else {
        System.out.println("Èchec de la suppression du client.");
    }
}
    
    
    
    /**
     * M√©thode pour mettre √† jour un client.
     * Avec appel de la m√©thode update de la classe ClientDao
     */
    private static void updateClient(Scanner scanner) {
    System.out.print("Saisissez l'ID du client que vous souhaitez mettre ‡ jour : ");
    int clientIdToUpdate = scanner.nextInt();
    scanner.nextLine(); // Pour consommer la nouvelle ligne

    // Lire les informations actuelles du client √† partir de la base de donn√©es
    Client clientToUpdate = ClientDao.readById(clientIdToUpdate);

    if (clientToUpdate != null) {
        System.out.println("Anciennes informations du client : " + clientToUpdate);

        // Bool√©en indiquant si la mise √† jour du client est termin√©e ou non
        boolean updateCompleted = false;

        while (!updateCompleted) {
            // Affichez un menu pour demander quels champs l'utilisateur souhaite mettre √† jour
            System.out.println("\nSÈlectionnez les champs ‡ mettre ‡ jour :");
            System.out.println("1. NumÈro du client");
            System.out.println("2. Nom du client");
            System.out.println("3. PrÈnom du client");
            System.out.println("4. Email du client");
            System.out.println("5. Adresse du client");
            System.out.println("0. Annuler et quitter la mise ‡ jour");

            System.out.print("Choisissez une option (0-5) : ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine();

            switch (updateChoice) {
                case 1:
                    System.out.print("Nouveau numÈro du client : ");
                    int newClientNumber = scanner.nextInt();
                    clientToUpdate.setClientNumber(newClientNumber);
                    break;
                case 2:
                    String newLastname;
                    do {
                        System.out.print("Nouveau nom du client : ");
                        newLastname = scanner.nextLine().trim();
                        if (newLastname.isEmpty()) {
                            System.out.println("Erreur : Le nom ne peut pas √™tre vide. Veuillez r√©essayer.");
                        }
                    } while (newLastname.isEmpty());
                    clientToUpdate.setLastname(newLastname);
                    break;
                case 3:
                    String newFirstname;
                    do {
                        System.out.print("Nouveau prÈnom du client : ");
                        newFirstname = scanner.nextLine().trim();
                        if (newFirstname.isEmpty()) {
                            System.out.println("Erreur : Le prÈnom ne peut pas √™tre vide. Veuillez r√©essayer.");
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
                            System.out.println("Erreur : L'email ne peut pas √™tre vide. Veuillez rÈessayer.");
                        } else if (!isValidEmail(newEmail)) {
                            System.out.println("Erreur : Le format de l'email n'est pas valide. Veuillez rÈessayer.");
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
                            System.out.println("Erreur : L'adresse ne peut pas Ítre vide. Veuillez rÈessayer.");
                        }
                    } while (newAddress.isEmpty());
                    clientToUpdate.setAddress(newAddress);
                    break;
                case 0:
                    System.out.println("Mise √† jour annulÈe.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez rÈessayer.");
                    break;
            }

            System.out.print("Voulez-vous mettre √† jour un autre champ ? (O/N) : ");
            // R√©cup√®re la r√©ponse de l'utilisateur en convertissant les espaces inutiles, met en majuscules pour une comparaison insensible √† la casse
            String continueUpdate = scanner.nextLine().trim().toUpperCase();

            // V√©rifie si l'utilisateur ne souhaite pas continuer la mise √† jour en comparant la r√©ponse √† "O" (non-insensible √† la casse)
            if (!continueUpdate.equals("O")) {
                updateCompleted = true;
            }
        }

        // Appelez la m√©thode update du ClientDao
        int result = ClientDao.update(clientToUpdate);
        if (result != -1) {
            System.out.println("Client mis ‡ jour avec succËs !");
        } else {
            System.out.println("Èchec de la mise ‡ jour du client.");
        }
    } else {
        System.out.println("Client non trouvÈ avec l'ID " + clientIdToUpdate);
    }
}

    
    
    /**
     * M√©thode pour ajouter un nouveau client.
     * Avec appel de la m√©thode add de la classe ClientDao
     */
    private static void addClient(Scanner scanner) {
    int clientNumber;
    String lastName, firstName, email, address;

    // Saisir le num√©ro du client
    System.out.print("NumÈro du client : ");
    clientNumber = scanner.nextInt();
    scanner.nextLine(); // Pour consommer la nouvelle ligne

    // Saisir le nom du client
    do {
        System.out.print("Nom du client : ");
        lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("Erreur : Le nom ne peut pas Ítre vide. Veuillez rÈessayer.");
        }
    } while (lastName.isEmpty());

    // Saisir le pr√©nom du client
    do {
        System.out.print("Pr√©nom du client : ");
        firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()) {
            System.out.println("Erreur : Le prÈnom ne peut pas Ítre vide. Veuillez rÈessayer.");
        }
    } while (firstName.isEmpty());

    // Saisir l'email du client
    do {
        System.out.print("Email du client : ");
        email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("Erreur : L'email ne peut pas Ítre vide. Veuillez r√©essayer.");
        } else if (!isValidEmail(email)) {
            System.out.println("Erreur : Le format de l'email n'est pas valide. Veuillez rÈessayer.");
        }
    } while (email.isEmpty() || !isValidEmail(email));

    // Saisir l'adresse du client
    do {
        System.out.print("Adresse du client : ");
        address = scanner.nextLine().trim();

        if (address.isEmpty()) {
            System.out.println("Erreur : L'adresse ne peut pas Ítre vide. Veuillez rÈessayer.");
        }
    } while (address.isEmpty());

    // Cr√©ation d'un nouvel objet Client avec les informations saisies
    Client newClient = new Client(clientNumber, lastName, firstName, email, address);

    // Appelez la m√©thode add du ClientDao
    int result = ClientDao.add(newClient);
    if (result != -1) {
        System.out.println("Client ajoutÈ avec succËs !");
    } else {
        System.out.println("Èchec de l'ajout du client.");
    }
}
    
    
    
    
    
    // Ajouter cette m√©thode pour v√©rifier le format de l'email
    private static boolean isValidEmail(String email) {
        // Utilisez une expression r√©guli√®re simple pour v√©rifier le format de l'email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(emailRegex);
    }
}

