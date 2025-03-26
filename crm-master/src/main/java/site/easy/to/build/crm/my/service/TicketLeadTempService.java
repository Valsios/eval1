package site.easy.to.build.crm.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.my.model.TicketLeadTemp;
import site.easy.to.build.crm.my.repository.TicketLeadTempRepository;

@Service
public class TicketLeadTempService {
    @Autowired
    TicketLeadTempRepository ticketLeadTempRepository;
    public void save(TicketLeadTemp ticketLeadTemp)
    {
        ticketLeadTempRepository.save(ticketLeadTemp);
    }
    public void deleteAll(){
        ticketLeadTempRepository.deleteAll();
    }
}
