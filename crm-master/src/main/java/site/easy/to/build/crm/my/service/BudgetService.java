package site.easy.to.build.crm.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.my.model.Budget;
import site.easy.to.build.crm.my.modelAPI.BudgetModel;
import site.easy.to.build.crm.my.modelAPI.LeadModel;
import site.easy.to.build.crm.my.repository.BudgetRepository;
import site.easy.to.build.crm.my.repository.SeuilBudgetRepository;

import java.text.Bidi;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    SeuilBudgetService seuilBudgetService;

    @Autowired
    DepenseService depenseService;

    public void save(Budget budget)
    {
        budgetRepository.save(budget);
    }

    //DATA FOR BUDGET API

    public List<BudgetModel> getListBudgetModel(LocalDateTime dateTime)
    {
        List<Budget> budgetList = getByDate(dateTime);
        List<BudgetModel> toReturn = new ArrayList<>();
        for (Budget budget : budgetList)
        {
            BudgetModel budgetModel = new BudgetModel();
            budgetModel.setIdBudget(budget.getIdBudget());
            budgetModel.setDateBudget(budget.getDateBudget());
            budgetModel.setAmount(budget.getAmount().doubleValue());
            budgetModel.setNom_customer(budget.getCustomer().getName());
            budgetModel.setId_customer(budget.getCustomer().getCustomerId());
            toReturn.add(budgetModel);
        }
        toReturn.sort(Comparator.comparing(BudgetModel::getIdBudget));
        return toReturn;
    }

    //END
    public List<Budget> getByCustomerDate(Customer customer, LocalDateTime localDateTime)
    {
        return budgetRepository.budgetCustomerDate(customer,localDateTime);
    }
    public List<Budget> getByDate( LocalDateTime localDateTime)
    {
        return budgetRepository.budgetDate(localDateTime);
    }
    public double sommeBudget(Customer customer,LocalDateTime dateTime)
    {
        double somme = 0;
        List<Budget> budgetList = getByCustomerDate(customer,dateTime);
        for (Budget budget : budgetList)
        {
            somme += budget.getAmount().doubleValue();
        }
        return somme;
    }

    public double check_pourcentage(double volaDepense,Customer customer,LocalDateTime dateTime)
    {
        double sommeDepense = depenseService.sommeDepense(customer,dateTime)+volaDepense;
        double sommeBudget = sommeBudget(customer,dateTime);
        double seuil = seuilBudgetService.getSeuilBudget().getValue();
        System.out.println("somme depense : "+sommeDepense);
        System.out.println("somme budget : "+sommeBudget);
        double pourcentage = (sommeDepense*100)/sommeBudget;
        if (pourcentage >= seuil)
        {
            return pourcentage;
        }
        return 0;
    }
    public double check_mihotra(double volaDepense,Customer customer,LocalDateTime dateTime)
    {
        double sommeDepense = depenseService.sommeDepense(customer,dateTime);
        double sommeBudget = sommeBudget(customer,dateTime);
        double budget_actuel = sommeBudget - sommeDepense;
        if (budget_actuel< volaDepense)
        {
            return volaDepense - budget_actuel;
        }
        return 0.00;
    }
}
