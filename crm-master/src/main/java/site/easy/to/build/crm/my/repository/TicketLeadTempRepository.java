package site.easy.to.build.crm.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.my.model.TicketLeadTemp;

import java.util.List;

@Repository
public interface TicketLeadTempRepository extends JpaRepository<TicketLeadTemp,Integer> {

    @Query("SELECT t FROM TicketLeadTemp t WHERE t.type LIKE 'lead' ORDER BY t.id ASC")
    public List<TicketLeadTemp> getAllLead();

    @Query("SELECT t FROM TicketLeadTemp t WHERE t.type LIKE 'ticket' order by t.id ASC")
    public List<TicketLeadTemp> getAllTicket();
}
