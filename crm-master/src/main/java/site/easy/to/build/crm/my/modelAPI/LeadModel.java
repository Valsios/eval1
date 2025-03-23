package site.easy.to.build.crm.my.modelAPI;

import java.time.LocalDateTime;


public class LeadModel {
     int leadId;
     String name;
     String status;
     String phone;
     String nom_manager;
     String nom_employee;
     String nom_customer;
     LocalDateTime createdAt;
     Double amount_depense;
     String description_depense;

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNom_manager() {
        return nom_manager;
    }

    public void setNom_manager(String nom_manager) {
        this.nom_manager = nom_manager;
    }

    public String getNom_employee() {
        return nom_employee;
    }

    public void setNom_employee(String nom_employee) {
        this.nom_employee = nom_employee;
    }

    public String getNom_customer() {
        return nom_customer;
    }

    public void setNom_customer(String nom_customer) {
        this.nom_customer = nom_customer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getAmount_depense() {
        return amount_depense;
    }

    public void setAmount_depense(Double amount_depense) {
        this.amount_depense = amount_depense;
    }

    public String getDescription_depense() {
        return description_depense;
    }

    public void setDescription_depense(String description_depense) {
        this.description_depense = description_depense;
    }

    public LeadModel(int leadId, String name, String status, String phone, String nom_manager, String nom_employee, String nom_customer, LocalDateTime createdAt, Double amount_depense, String description_depense) {
        this.leadId = leadId;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.nom_manager = nom_manager;
        this.nom_employee = nom_employee;
        this.nom_customer = nom_customer;
        this.createdAt = createdAt;
        this.amount_depense = amount_depense;
        this.description_depense = description_depense;
    }

    public LeadModel() {
    }
}
