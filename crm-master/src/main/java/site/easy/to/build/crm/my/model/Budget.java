package site.easy.to.build.crm.my.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import site.easy.to.build.crm.entity.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_budget")
    private Integer idBudget;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "date_budget", nullable = false)
    @NotNull(message = "La date de budget est obligatoire.")
    private LocalDateTime dateBudget;

    @Column(name = "amount", precision = 10, scale = 2)
    @NotNull(message = "Amount required.")
    @Positive(message = "Amount must be positive.")
    private BigDecimal amount;

    // Getters and Setters
    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateBudget() {
        return dateBudget;
    }

    public void setDateBudget(LocalDateTime dateBudget) {
        this.dateBudget = dateBudget;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Budget() {
    }
}

