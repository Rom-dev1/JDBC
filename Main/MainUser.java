package Main;

import DAO.UserDao;
import Modele.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainUser {

    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        boolean quitter = false;

        while (!quitter) {
            System.out.println("=== Menu Utilisateur ===");
            System.out.println("1. Afficher mes informations");
            System.out.println("2. Mettre à jour mes informations");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

            switch (choix) {
                case 1:
                    afficherMesInformations();
                    break;
                case 2:
                    mettreAJourMesInformations();
                    break;
                case 3:
                    quitter = true;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }

        System.out.println("Fin du menu utilisateur.");
    }

    private static void afficherMesInformations() {
        System.out.print("Entrez l'ID de l'utilisateur : ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

        User currentUser = UserDao.getUserById(userId);
        if (currentUser != null) {
            System.out.println("Mes informations : " + currentUser);
        } else {
            System.out.println("Utilisateur introuvable.");
        }
    }

    private static void mettreAJourMesInformations() {
        System.out.print("Entrez l'ID de l'utilisateur à mettre à jour : ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()

        User currentUser = UserDao.getUserById(userId);

        if (currentUser != null) {
            try {
                System.out.print("Nouveau user number : ");
                int newNumber = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nouveau login : ");
                String newLogin = scanner.nextLine();

                System.out.print("Nouveau mot de passe : ");
                String newPassword = scanner.nextLine();

                System.out.print("Nouveau nom : ");
                String newLastname = scanner.nextLine();

                System.out.print("Nouveau prénom : ");
                String newFirstname = scanner.nextLine();

                System.out.print("Nouvel email : ");
                String newEmail = scanner.nextLine();

                // Créer l'objet User mis à jour
                User updatedUser = new User(userId, newNumber, newLogin, newPassword, newLastname, newFirstname, newEmail);

                // Appeler la méthode update de UserDao
                UserDao.updateUser(updatedUser);

                System.out.println("Informations mises à jour avec succès !");
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer des valeurs valides.");
                scanner.nextLine(); // Vider le tampon d'entrée pour éviter une boucle infinie
            }
        } else {
            System.out.println("Utilisateur introuvable.");
        }
    }
}
