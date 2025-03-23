package site.easy.to.build.crm.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.my.model.Depense;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface DepenseRepository extends JpaRepository<Depense,Integer> {

    @Modifying
    @Query("DELETE FROM Depense d WHERE d.lead = :lead")
    void deleteByLead(@Param("lead") Lead lead);

    @Modifying
    @Query("DELETE FROM Depense d WHERE d.ticket = :ticket")
    void deleteByTicket(@Param("ticket") Ticket ticket);

    @Query("SELECT d FROM Depense d WHERE d.lead IS NOT NULL AND d.lead.createdAt <= :dateTime")
    List<Depense> getDepenseLeadDate(@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT d FROM Depense d WHERE d.ticket IS NOT NULL AND d.ticket.createdAt <= :dateTime")
    List<Depense> getDepenseTicketDate(@Param("dateTime") LocalDateTime dateTime);


    @Query("SELECT d FROM Depense d WHERE d.lead IS NOT NULL AND d.lead.customer = :customer AND d.lead.createdAt <= :dateTime")
    List<Depense> getDepenseLeadCustomerDate(@Param("customer") Customer customer,@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT d FROM Depense d WHERE d.ticket IS NOT NULL AND d.ticket.customer = :customer AND d.ticket.createdAt <= :dateTime")
    List<Depense> getDepenseTicketCustomerDate(@Param("customer") Customer customer,@Param("dateTime") LocalDateTime dateTime);


    @Query(value = "SELECT d FROM Depense d WHERE d.ticket = :ticket AND d.dateSet <= :localDateTime ORDER BY d.dateSet DESC")
    List<Depense> getLastValueTicket(@Param("ticket") Ticket ticket, @Param("localDateTime") LocalDateTime localDateTime);


    @Query(value = "SELECT d FROM Depense d WHERE d.lead = :lead AND d.dateSet <= :localDateTime ORDER BY d.dateSet DESC")
    List<Depense> getLastValueLead(@Param("lead") Lead lead, @Param("localDateTime") LocalDateTime localDateTime);
}

