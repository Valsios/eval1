package site.easy.to.build.crm.my.modelAPI;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class BudgetModel {

    Integer idBudget;
    int id_customer;
    String nom_customer;
    Long dateBudget;
    Double amount;

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public void setDateBudget(Long dateBudget) {
        this.dateBudget = dateBudget;
    }

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

    public Long getDateBudget() {
        return dateBudget;
    }

    public void setDateBudget(LocalDateTime dateBudget) {
        this.dateBudget = dateBudget.toInstant(ZoneOffset.UTC).toEpochMilli();
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
        this.setDateBudget(dateBudget);
        this.amount = amount;
    }

    public BudgetModel() {
    }
}
