/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

//import MenuClient;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Op�rations sur Utilisateurs");
            System.out.println("2. Op�rations sur Clients");
            System.out.println("3. Op�rations sur Fournisseurs");
            System.out.println("4. Op�rations sur Articles");
            System.out.println("0. Quitter");
            System.out.print("Choisissez la table (0-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    // � impl�menter : runUser();
                    break;
                case 2:
                    //MenuClient.run();         
                    break;
                case 3:
//                    MenuFournisseurs.run();
                    break;
                case 4:
                    MenuArticles.run();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide. Veuillez r�essayer.");
            }
        }
    }
}
