package site.easy.to.build.crm.my.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;

import java.time.LocalDateTime;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "depense")
@Valid
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depense")
    private Integer idDepense;

    @ManyToOne
    @JoinColumn(name = "id_ticket", referencedColumnName = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "id_lead", referencedColumnName = "lead_id")
    private Lead lead;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    @Positive(message = "Montant positif")
    private BigDecimal amount;

    @Column(name = "date_set", nullable = false)
    private LocalDateTime dateSet;

    @Column(name = "description")
    @NotBlank(message = "Description requred")
    private String description;

    // Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(Integer idDepense) {
        this.idDepense = idDepense;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateSet() {
        return dateSet;
    }

    public void setDateSet(LocalDateTime dateSet) {
        this.dateSet = dateSet;
    }
}
