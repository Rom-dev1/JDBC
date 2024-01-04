/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import DAO.SupplierDao;
import Modele.Supplier;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author annas
 */
public class MenuSupplier {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;
        Supplier supplier;
        int id, supplierNumber, choix, res;
        String lastName, firstName, mail, address;

        while (!quitter) {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choix) {
                case 1:
                    // Lire la table complète

                    List<Supplier> suppliers = SupplierDao.readAll();
                    if (suppliers == null) {
                        System.out.println("Erreur interne");
                    } else if (suppliers.isEmpty()) {
                        System.out.println("La lise des fournisseurs est vide");
                    } else {
                        System.out.println("La list des fournisseurs est :");
                        for (Supplier sup : suppliers) {
                            System.out.println(sup);
                        }
                    }
                    break;

                case 2:
                    // Lire un enregistrement selon l'id

                    System.out.println("Entrez l'ID du fournisseur");
                    id = scanner.nextInt();
                    supplier = SupplierDao.readById(id);
                    System.out.println();
                    if (supplier != null) {
                        System.out.println("Fournisseur trouvé : " + supplier);
                    } else {
                        System.out.println("Aucun fournisseur trouvé avec l'ID" + id);
                    }
                    break;

                case 3:
                    // Ajouter un enregistrement

                    System.out.println("Entrez le numéro unique de fournisseur");
                    supplierNumber = scanner.nextInt();
                    scanner.nextLine();

                    do {
                        System.out.println("Entrez le nom");
                        lastName = scanner.nextLine();
                    } while (lastName.trim().isEmpty());
                    do {
                        System.out.println("Entrez le prénom");
                        firstName = scanner.nextLine();
                    } while (firstName.trim().isEmpty());

                    do {
                        System.out.println("Entrez l'adresse mail");
                        mail = scanner.nextLine();
                    } while (!isValidEmail(mail));

                    do {
                        System.out.println("Entrez l'adresse");
                        address = scanner.nextLine();
                    } while (address.trim().isEmpty());

                    supplier = new Supplier(supplierNumber, lastName, firstName, mail, address);
                    // appel à une méthode de la classe correspondante  SupplierDao.add()
                    res = SupplierDao.add(supplier);

                    System.out.println();
                    if (res == 1) {
                        System.out.println("Ajout effectué");
                    } else {
                        System.out.println("Une erreure s'est produite");
                    }
                    break;

                case 4:
                    // Modifier un enregistrement
                    // appel à la méthode  update de la classe supplierDao.update())

                    System.out.println("Entrez l'ID du fournisseur");
                    id = scanner.nextInt();
                    supplier = SupplierDao.readById(id);
                    System.out.println();
                    if (supplier != null) {
                        System.out.println("Entrez le numéro unique de fournisseur (0 pour conserver " + supplier.getSupplierNumber() + ")");
                        supplierNumber = scanner.nextInt();
                        scanner.nextLine();
                        if (supplierNumber != 0) {
                            supplier.setSupplierNumber(supplierNumber);
                        }
                        System.out.println("Entrez le nom (touch entrée pour conserver \"" + supplier.getLastname() + "\")");
                        lastName = scanner.nextLine();
                        if (!lastName.isEmpty()) {
                            supplier.setLastname(lastName);
                        }
                        System.out.println("Entrez le prénom (touch entrée pour conserver \"" + supplier.getFirstname() + "\")");
                        firstName = scanner.nextLine();
                        if (!firstName.isEmpty()) {
                            supplier.setFirstname(firstName);
                        }
                        System.out.println("Entrez l'adresse mail (touch entrée pour conserver \"" + supplier.getEmail() + "\")");
                        mail = scanner.nextLine();
                        while (!mail.isEmpty() && !isValidEmail(mail)) {
                            System.out.println("Adresse e-mail invalide. Veuillez entrer une adresse e-mail valide (appuyez sur Entrée pour conserver \"" + supplier.getEmail() + "\")");
                            mail = scanner.nextLine();
                        }

                        if (!mail.isEmpty()) {
                            supplier.setEmail(mail);
                        }
                        System.out.println("Entrez l'adresse (touch entrée pour conserver \"" + supplier.getAddress() + "\")");
                        address = scanner.nextLine();
                        if (!address.isEmpty()) {
                            supplier.setAddress(address);
                        }

                        // appel à une méthode de la classe correspondante  SupplierDao.update()
                        res = SupplierDao.update(supplier);

                        System.out.println();
                        if (res == 1) {
                            System.out.println("Modification effectué");
                        } else {
                            System.out.println("Une erreure s'est produite");
                        }
                    } else {
                        System.out.println("Aucun fournisseur trouvé avec l'ID" + id);
                    }
                    break;

                case 5:
                    // Supprimer un enregistrement
                    // appel à la methode  delete de la classe supplierDao.delete())

                    System.out.println("Entrez l'id du fournisseur que vous voulez supprimer");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    res = SupplierDao.delete(id);
                    System.out.println();
                    if (res == 1) {
                        System.out.println("Supression effectué");
                    } else if (res == 0) {
                        System.out.println("Le fournisssuer avec l'id " + id + " n'existe pas");
                    } else {
                        System.out.println("Une erreure s'est produite");
                    }
                    break;

                case 6:
                    // Retour au menu principal
                    quitter = true;
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
                    break;
            }
        }
    }

    private static void afficherMenu() {
        System.out.println();
        System.out.println("Menu Gestion de la table Fournisseurs :");
        System.out.println("1. Lire la table complète");
        System.out.println("2. Lire un enregistrement selon l'id");
        System.out.println("3. Ajouter un fournisseur");
        System.out.println("4. Modifier un enregistrement");
        System.out.println("5. Supprimer un enregistrement");
        System.out.println("6. Retour au menu principal");
        System.out.print("Choisissez une option : ");
    }

//Méthode pour vérifier le format de l'email
    private static boolean isValidEmail(String email) {
        // expression régulière simple pour vérifier le format de l'email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(emailRegex);
    }
}
