package site.easy.to.build.crm.my.service;

import com.opencsv.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.my.model.Budget;
import site.easy.to.build.crm.my.model.Depense;
import site.easy.to.build.crm.my.model.TicketLeadTemp;
import site.easy.to.build.crm.my.repository.TicketLeadTempRepository;
import site.easy.to.build.crm.my.util.FormatUtil;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Service
public class ImportService {
    @Autowired
    CustomerService customerService;

    @Autowired
    TicketLeadTempService ticketLeadTempService;

    @Autowired
    DataSource dataSource;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DepenseService depenseService;

    @Autowired
    TicketService ticketService;

    @Autowired
    LeadService leadService;

    @Autowired
    BudgetService budgetService;

    public void clearTemp()
    {
        ticketLeadTempService.deleteAll();
    }

    //public assembly all import

    @Transactional(rollbackFor = Exception.class)
    public void importAll(String budgetFile,String customerFile,String ticketFile,User manager)throws Exception
    {

            importCsvCustomer(customerFile,manager);
            importBudget(budgetFile);
            importCsvTicketLeadTemporary(ticketFile);
            importCsvTicket("ticket_lead_temp",manager);
            importCsvLead("ticket_lead_temp",manager);

    }

    //insert budget
    public void importBudget(String csvFile)throws Exception
    {
        String errorMessage ="";
        try {
            // Définir un parser avec le séparateur ';'
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();

            // Ignorer la première ligne (en-têtes)
            allData.remove(0);

            int i = 1;
            boolean isError = false;
            for (String[] line : allData) {
                try {

                    Budget budget = new Budget();
                    Customer customer =customerService.findByEmail(line[0]);
                    if (customer==null)
                    {
                        throw new Exception("Customer not found.");
                    }
                    budget.setAmount(FormatUtil.getFormaNumber(line[1]));
                    budget.setCustomer(customer);
                    budget.setDateBudget(LocalDateTime.now());
                    budgetService.save(budget);


                } catch (Exception e) {
                    isError = true;
                    errorMessage = "Ligne "+i +" :"+ Arrays.toString(line) +" MESSAGE : "+e.getMessage();
                    System.out.println(errorMessage);
                    break;
                }
                i++;
            }
            if (isError == true)
            {

                throw new Exception(errorMessage);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    //insert lead
    public void importCsvTicket(String tableName, User manager)throws Exception
    {
        try {
            List<TicketLeadTemp> ticketLeadTempList = ticketLeadTempService.ticketLeadTempRepository.getAllTicket();

            Ticket ticket;
            Depense depense;
            Customer customer;
            for (TicketLeadTemp temp : ticketLeadTempList) {
                ticket = new Ticket();
                customer = customerService.findByEmail(temp.getCustomerEmail());
                ticket.setCustomer(customer);
                ticket.setStatus(temp.getStatus());
                ticket.setSubject(temp.getSubjectOrName());
                ticket.setCreatedAt(LocalDateTime.now());
                ticket.setManager(manager);
                ticket.setEmployee(manager);
                ticket.setPriority("low");
                ticket.setDescription_depense(temp.getSubjectOrName());
                ticket.setAmount_depense(temp.getExpense());

                depense = new Depense();
                depense.setTicket(ticket);
                depense.setDescription(temp.getSubjectOrName());
                depense.setAmount(temp.getExpense());
                depense.setDateSet(LocalDateTime.now());

                ticketService.save(ticket);
                depenseService.save(depense);

            }
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    //insert ticket
    public void importCsvLead(String tableName,User manager)throws Exception
    {
        try {
            List<TicketLeadTemp> ticketLeadTempList = ticketLeadTempService.ticketLeadTempRepository.getAllLead();

            Lead lead;
            Depense depense;
            Customer customer;
            for (TicketLeadTemp temp : ticketLeadTempList) {
                lead = new Lead();
                customer = customerService.findByEmail(temp.getCustomerEmail());
                lead.setCustomer(customer);
                lead.setStatus(temp.getStatus());
                lead.setEmployee(manager);
                lead.setManager(manager);
                lead.setGoogleDrive(false);
                lead.setPhone("2563");
                lead.setName(temp.getCustomerEmail());
                lead.setCreatedAt(LocalDateTime.now());
                lead.setDescription_depense(temp.getSubjectOrName());
                lead.setAmount_depense(temp.getExpense());

                depense = new Depense();
                depense.setLead(lead);
                depense.setDescription(temp.getSubjectOrName());
                depense.setAmount(temp.getExpense());
                depense.setDateSet(LocalDateTime.now());

                leadService.save(lead);
                depenseService.save(depense);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }


    //insert temporary
    public void importCsvTicketLeadTemporary(String csvFile) throws Exception {
        String errorMessage ="";
        try {
            // Définir un parser avec le séparateur ';'
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();

            // Ignorer la première ligne (en-têtes)
            allData.remove(0);

            int i = 1;
            boolean isError = false;
            for (String[] line : allData) {
                try {

                    TicketLeadTemp ticketLeadTemp = new TicketLeadTemp();
                    if (customerService.findByEmail(line[0].trim())==null)
                    {
                        throw new Exception("Customer not found.");
                    }
                    ticketLeadTemp.setCustomerEmail(line[0].trim());
                    ticketLeadTemp.setExpense(FormatUtil.getFormaNumber(line[4]));
                    ticketLeadTemp.setType(line[2].toLowerCase().trim());

                    //ticketLeadTemp.setStatus(line[3].trim().toLowerCase());
                    if (ticketLeadTemp.getType().compareTo("ticket")==0)
                    {
                        ticketLeadTemp.setStatus("open");
                    }
                    else
                    {
                        ticketLeadTemp.setStatus("success");
                    }
                    ticketLeadTemp.setSubjectOrName(line[1]);

                    FormatUtil.check_status(ticketLeadTemp.getType(),ticketLeadTemp.getStatus());
                    ticketLeadTempService.save(ticketLeadTemp);


                } catch (Exception e) {
                    isError = true;
                    errorMessage = "Ligne "+i +" :"+ Arrays.toString(line) +" MESSAGE : "+e.getMessage();
                    System.out.println(errorMessage);
                    break;
                }
                i++;
            }
            if (isError == true)
            {
                throw new Exception(errorMessage);
            }

        } catch (Exception e) {
           e.printStackTrace();
           throw new Exception(e.getMessage());
        }
    }


    //save customer
    public void importCsvCustomer(String csvFile,User manager)throws Exception {
        try {
            // Définir un parser avec le séparateur ';'
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            // Construire le CSVReader avec ce parser
            CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                    .withCSVParser(parser)
                    .build();

            List<String[]> allData = reader.readAll(); // Lire toutes les lignes
            reader.close();

            // Ignorer la première ligne (en-têtes)
            allData.remove(0);

            for (String[] line : allData) {
                Customer customer = new Customer();
                customer.setName(line[1]);
                customer.setEmail(line[0].trim());
                customer.setUser(manager);
                customer.setCountry("Mada");
                customer.setPhone("0321100319");

                customerService.save(customer);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
