package site.easy.to.build.crm.my.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seuil_budget")
public class SeuilBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seuil")
    private Integer idSeuil;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "date_set", nullable = false)
    private LocalDateTime dateSet;

    // Getters and Setters
    public Integer getIdSeuil() {
        return idSeuil;
    }

    public void setIdSeuil(Integer idSeuil) {
        this.idSeuil = idSeuil;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDateSet() {
        return dateSet;
    }

    public void setDateSet(LocalDateTime dateSet) {
        this.dateSet = dateSet;
    }
}

