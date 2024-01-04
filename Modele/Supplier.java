package Modele;

import java.io.Serializable;

public class Supplier implements Serializable {

    private int id;
    private int supplierNumber;
    private String lastname;
    private String firstname;
    private String email;
    private String address;

    public Supplier() {
    }

    public Supplier(int supplierNumber, String lastname, String firstname, String email, String address) {
        this.supplierNumber = supplierNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
    }

    public Supplier(int id, int supplierNumber, String lastname, String firstname, String email, String address) {
        this.id = id;
        this.supplierNumber = supplierNumber;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public int getSupplierNumber() {
        return supplierNumber;
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

    public void setSupplierNumber(int supplierNumber) {
        this.supplierNumber = supplierNumber;
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

    @Override
    public String toString() {
        return "Fournisseur N° " + supplierNumber + " (id " + id + ") : " + lastname + " " + firstname + ", " + email + ", " + address + '.';
    }

}
