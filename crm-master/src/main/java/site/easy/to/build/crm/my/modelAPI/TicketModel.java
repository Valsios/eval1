package site.easy.to.build.crm.my.modelAPI;

import java.time.LocalDateTime;

public class TicketModel {

   int ticketId;
   String subject;
   String description;
   String status;
   String priority;
   String nom_manager;
   String nom_employee;
   String nom_customer;
   LocalDateTime createdAt;
   Double amount_depense;
   String description_depense;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public TicketModel(int ticketId, String subject, String description, String status, String priority, String nom_manager, String nom_employee, String nom_customer, LocalDateTime createdAt, Double amount_depense, String description_depense) {
        this.ticketId = ticketId;
        this.subject = subject;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.nom_manager = nom_manager;
        this.nom_employee = nom_employee;
        this.nom_customer = nom_customer;
        this.createdAt = createdAt;
        this.amount_depense = amount_depense;
        this.description_depense = description_depense;
    }

    public TicketModel() {
    }
}
