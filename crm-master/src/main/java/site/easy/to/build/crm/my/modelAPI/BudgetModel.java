package site.easy.to.build.crm.my.modelAPI;
import java.time.LocalDateTime;

public class BudgetModel {

    Integer idBudget;
    String nom_customer;
    LocalDateTime dateBudget;
    Double amount;

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public String getNom_customer() {
        return nom_customer;
    }

    public void setNom_customer(String nom_customer) {
        this.nom_customer = nom_customer;
    }

    public LocalDateTime getDateBudget() {
        return dateBudget;
    }

    public void setDateBudget(LocalDateTime dateBudget) {
        this.dateBudget = dateBudget;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BudgetModel(Integer idBudget, String nom_customer, LocalDateTime dateBudget, Double amount) {
        this.idBudget = idBudget;
        this.nom_customer = nom_customer;
        this.dateBudget = dateBudget;
        this.amount = amount;
    }

    public BudgetModel() {
    }
}
