package site.easy.to.build.crm.my.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket_lead_temp")
public class TicketLeadTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // Clé primaire (ajoutée car ta table n'a pas de clé)

    @Column(name = "customer_email", nullable = false, length = 255)
    private String customerEmail;

    @Column(name = "subject_or_name", nullable = false, length = 255)
    private String subjectOrName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false, length = 255)
    private String status;

    @Column(name = "expense", nullable = false, precision = 15, scale = 2)
    private BigDecimal expense;


    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    // Constructeurs
    public TicketLeadTemp() {}

    public TicketLeadTemp(String customerEmail, String subjectOrName, String type, String status, BigDecimal expense) {
        this.customerEmail = customerEmail;
        this.subjectOrName = subjectOrName;
        this.type = type;
        this.status = status;
        this.expense = expense;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubjectOrName() {
        return subjectOrName;
    }

    public void setSubjectOrName(String subjectOrName) {
        this.subjectOrName = subjectOrName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws Exception{
        if (type.compareTo("lead")!=0 && type.compareTo("ticket")!=0)
        {
            throw new Exception("Type Ivalid.");
        }
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(String expense)throws Exception{

        try {
            Double.parseDouble(expense);
        }
        catch (Exception e)
        {
            throw new Exception("Amount format invalid.");
        }
        if (Double.parseDouble(expense)<=0)
        {
            throw new Exception("Amount must be positive.");
        }
        this.expense = BigDecimal.valueOf(Double.parseDouble(expense));;

    }
}

