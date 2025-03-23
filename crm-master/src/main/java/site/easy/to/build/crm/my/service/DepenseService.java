package site.easy.to.build.crm.my.service;

import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.standard.expression.Each;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.my.model.Depense;
import site.easy.to.build.crm.my.modelAPI.LeadModel;
import site.easy.to.build.crm.my.modelAPI.TicketModel;
import site.easy.to.build.crm.my.repository.BudgetRepository;
import site.easy.to.build.crm.my.repository.DepenseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepenseService {
    @Autowired
    DepenseRepository depenseRepository;

    public void save(Depense depense)
    {
        depenseRepository.save(depense);
    }
    public List<Depense> getTicketByCustomerDate(Customer customer, LocalDateTime localDateTime)
    {
        return depenseRepository.getDepenseTicketCustomerDate(customer, localDateTime);
    }
    //DATA TO MY DASHBOARD C#
    public HashMap<Ticket,Double> getAllTicketDate(LocalDateTime dateTime)
    {
        List<Depense> ticketList = depenseRepository.getDepenseTicketDate(dateTime);
        HashMap<Ticket,Double> toReturn = new HashMap<Ticket,Double>();
        for (Depense depense : ticketList)
        {
            Ticket ticket = depense.getTicket();
            ticket.setDescription_depense(depense.getDescription());
            toReturn.put(ticket,depenseRepository.getLastValueTicket(ticket,dateTime).get(0).getAmount().doubleValue());
        }
        return toReturn;
    }

    public HashMap<Lead,Double> getAllLeadDate(LocalDateTime dateTime)
    {
        List<Depense> leadList = depenseRepository.getDepenseLeadDate(dateTime);
        HashMap<Lead,Double> toReturn = new HashMap<Lead,Double>();
        for (Depense depense : leadList)
        {
            Lead lead = depense.getLead();
            lead.setDescription_depense(depense.getDescription());
            toReturn.put(lead,depenseRepository.getLastValueLead(lead,dateTime).get(0).getAmount().doubleValue());
        }
        return toReturn;
    }

    public List<TicketModel> getListTicketModel(LocalDateTime dateTime)
    {
        HashMap<Ticket,Double> listeTicket = getAllTicketDate(dateTime);
        List<TicketModel> toReturn = new ArrayList<TicketModel>();
        for (Map.Entry<Ticket,Double> map : listeTicket.entrySet())
        {
            TicketModel ticketModel = new TicketModel();
            Ticket ticket = map.getKey();
            Double value = map.getValue();
            ticketModel.setTicketId(ticket.getTicketId());
            ticketModel.setAmount_depense(value);
            ticketModel.setDescription(ticketModel.getDescription());
            ticketModel.setCreatedAt(ticket.getCreatedAt());
            ticketModel.setNom_customer(ticket.getCustomer().getName());
            ticketModel.setPriority(ticket.getPriority());
            ticketModel.setStatus(ticket.getStatus());
            ticketModel.setNom_manager(ticket.getManager().getUsername());
            ticketModel.setSubject(ticket.getSubject());
            ticketModel.setNom_employee(ticket.getEmployee().getUsername());
            ticketModel.setDescription_depense(ticket.getDescription_depense());

            toReturn.add(ticketModel);
        }

        return toReturn;
    }
    public List<LeadModel> getListLeadModel(LocalDateTime dateTime)
    {
        HashMap<Lead,Double> leadList = getAllLeadDate(dateTime);
        List<LeadModel> toReturn = new ArrayList<LeadModel>();
        for (Map.Entry<Lead,Double> map : leadList.entrySet())
        {
            LeadModel leadModel = new LeadModel();
            Lead lead = map.getKey();
            Double value = map.getValue();
            leadModel.setLeadId(lead.getLeadId());
            leadModel.setName(lead.getName());
            leadModel.setAmount_depense(value);
            leadModel.setPhone(lead.getPhone());
            leadModel.setNom_customer(lead.getCustomer().getName());
            leadModel.setCreatedAt(lead.getCreatedAt());
            leadModel.setStatus(lead.getStatus());
            leadModel.setNom_employee(lead.getEmployee().getUsername());
            leadModel.setNom_manager(lead.getManager().getUsername());
            leadModel.setDescription_depense(lead.getDescription_depense());

            toReturn.add(leadModel);
        }

        return toReturn;
    }

    //END
    public HashMap<Ticket,Double> getDepenseTicketCustomerDate(Customer customer,LocalDateTime dateTime)
    {
        List<Depense> ticketList = depenseRepository.getDepenseTicketCustomerDate(customer,dateTime);
        HashMap<Ticket,Double> toReturn = new HashMap<Ticket,Double>();
        for (Depense depense : ticketList)
        {
            Ticket ticket = depense.getTicket();
            toReturn.put(ticket,depenseRepository.getLastValueTicket(ticket,dateTime).get(0).getAmount().doubleValue());
        }
        return toReturn;
    }
    public HashMap<Lead,Double> getDepenseLeadCustomerDate(Customer customer, LocalDateTime dateTime)
    {
        List<Depense> leadList = depenseRepository.getDepenseLeadCustomerDate(customer,dateTime);
        HashMap<Lead,Double> toReturn = new HashMap<Lead,Double>();
        for (Depense depense : leadList)
        {
            Lead lead = depense.getLead();
            toReturn.put(lead,depenseRepository.getLastValueLead(lead,dateTime).get(0).getAmount().doubleValue());
        }
        return toReturn;
    }

    public double sommeDepense(Customer customer,LocalDateTime dateTime)
    {
        HashMap<Ticket,Double> ticketDepense = getDepenseTicketCustomerDate(customer,dateTime);
        HashMap<Lead,Double> leadDepense = getDepenseLeadCustomerDate(customer,dateTime);

        double somme = 0;
        for (Map.Entry<Ticket, Double> map : ticketDepense.entrySet())
        {
            somme += map.getValue();
        }
        for (Map.Entry<Lead, Double> map : leadDepense.entrySet())
        {
            somme += map.getValue();
        }

        return somme;
    }

    @Transactional
    public void deleteByLead(Lead lead)
    {
        depenseRepository.deleteByLead(lead);
    }

    @Transactional
    public void deleteByTicket(Ticket ticket)
    {
        depenseRepository.deleteByTicket(ticket);
    }

}
