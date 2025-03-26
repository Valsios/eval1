package site.easy.to.build.crm.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.my.model.Budget;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloneService {

    @Autowired
    DepenseService depenseService;

    @Autowired
    BudgetService budgetService;

    public HashMap<Ticket,Double> getAllTicketNowCustomer(Customer customer)
    {
        return depenseService.getDepenseTicketCustomerDate(customer, LocalDateTime.now());
    }
    public HashMap<Lead,Double> getAllLeadNowCustomer(Customer customer)
    {
        return depenseService.getDepenseLeadCustomerDate(customer, LocalDateTime.now());
    }
    public List<Budget> budgetCustomerNow(Customer customer)
    {
        return budgetService.getByCustomerDate(customer,LocalDateTime.now());
    }

    public String getAllTicketString(Customer customer)
    {
        HashMap<Ticket,Double> list = getAllTicketNowCustomer(customer);
        String toReturn = "";
        for (Map.Entry<Ticket,Double> map : list.entrySet())
        {
            Ticket ticket= map.getKey();
            Double valueTicket = map.getValue();
            toReturn += ticket.getStringImportant(valueTicket)+"\n";
        }
        return toReturn;
    }

    public String getAllLeadString(Customer customer)
    {
        HashMap<Lead,Double> list = getAllLeadNowCustomer(customer);
        String toReturn = "";
        for (Map.Entry<Lead,Double> map : list.entrySet())
        {
            Lead lead= map.getKey();
            Double valueTicket = map.getValue();
            toReturn += lead.getStringImportant(valueTicket)+"\n";
        }
        return toReturn;
    }

    public String allExpenseString(Customer customer )
    {
        String toReturn ="customer_email;subject_or_name;type;status;expense\n";
        toReturn += getAllTicketString(customer);
        toReturn += getAllLeadString(customer);
        return toReturn;
    }

    public String budgetString(Customer customer )
    {
        List<Budget> budgetList = budgetCustomerNow(customer);
        String toReturn = "customer_email;amount\n";
        for (Budget budget : budgetList)
        {
            toReturn += budget.getStringImportant()+"\n";
        }

        return toReturn;
    }

    public String customerString(Customer customer)
    {
        String toReturn = "customer_email;name\n";
        toReturn += customer.getStringImportant();
        return toReturn;
    }

    public void exportAll(Customer customer) throws IOException {
        String allDataExpense = allExpenseString(customer);
        String allDataBudget = budgetString(customer);
        String customerString = customerString(customer);
        String  absolutePath= "/Users/randriamalalavalisoa/Documents/DATA-EVAL";

        try (FileWriter writer = new FileWriter(absolutePath + File.separator+"copieData.txt")){
            writer.write(allDataExpense +"//DATA\n"+allDataBudget+"//DATA\n"+customerString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createTheThree(String fileName)
    {
        String  absolutePathFile= "/Users/randriamalalavalisoa/Documents/DATA-EVAL/"+fileName;
        String  absolutePath= "/Users/randriamalalavalisoa/Documents/DATA-EVAL";
        try {
            String content = Files.readString(Path.of(absolutePathFile));
            String[] listeContent = content.split("//DATA");

            String dataExpense = listeContent[0];
            String dataBudget = (listeContent[1].split("\n",2))[1];
            String dataCustomer = (listeContent[2].split("\n",2))[1];

            try (
                    FileWriter writerDataExpense = new FileWriter(absolutePath+File.separator+"copiedataExpense.csv");
                    FileWriter writerDataBudget = new FileWriter(absolutePath+File.separator+"copiedataBudget.csv");
                    FileWriter writerDataCustomer = new FileWriter(absolutePath+File.separator+"copiedataCustomer.csv");
            ){
                writerDataExpense.write(dataExpense);
                writerDataBudget.write(dataBudget);
                writerDataCustomer.write(dataCustomer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
